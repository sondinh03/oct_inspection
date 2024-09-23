package com.octl3.api.service.impl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.octl3.api.commons.exception.OctException;
import com.octl3.api.dto.request.AuthenticationRequest;
import com.octl3.api.dto.request.IntrospectRequest;
import com.octl3.api.dto.request.LogoutRequest;
import com.octl3.api.dto.request.RefreshRequest;
import com.octl3.api.dto.response.AuthenticationResponse;
import com.octl3.api.dto.response.IntrospectResponse;
import com.octl3.api.entity.InvalidatedToken;
import com.octl3.api.entity.User;
import com.octl3.api.repository.InvalidatedTokenRepository;
import com.octl3.api.repository.UserRepository;
import com.octl3.api.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

import static com.octl3.api.commons.exception.ErrorMessages.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    UserRepository userRepo;
    InvalidatedTokenRepository invalidatedTokenRepo;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    @NonFinal
    @Value("${jwt.valid-duration}")
    protected long VALID_DURATION;

    @NonFinal
    @Value("${jwt.refreshable-duration}")
    protected long REFRESHABLE_DURATION;

    @Override
    public AuthenticationResponse authenticated(AuthenticationRequest request) {
        User user = userRepo.findByUsername(request.getUsername())
            .orElseThrow(() -> new OctException(NOT_FOUND));

        var passwordEncoder = new BCryptPasswordEncoder(10);

        var authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!authenticated) {
            throw new OctException(UNAUTHENTICATED);
        }

        var token = generateToken(user);
        return AuthenticationResponse.builder().token(token).authenticated(true).build();
    }

    @Override
    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();
        boolean isValid;

        try {
            verifyToken(token, false);
            isValid = true;
        } catch (OctException e) {
            isValid = false;
        }

        return IntrospectResponse.builder().valid(isValid).build();
    }

    @Override
    public void logout(LogoutRequest request) throws ParseException, JOSEException {
        try {
            var signedToken = verifyToken(request.getToken(), true);

            var jwtID = signedToken.getJWTClaimsSet().getJWTID();
            var expiryTime = signedToken.getJWTClaimsSet().getExpirationTime();

            var invalidatedToken =
                    InvalidatedToken.builder().id(jwtID).expiryTime(expiryTime).build();

            invalidatedTokenRepo.save(invalidatedToken);
            log.info("Đã lưu");
        } catch (OctException e) {
            log.info("Token already expired.", e);
        }
    }

    @Override
    public AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException {
        var signJWT = verifyToken(request.getToken(), true);
        var claimSet = signJWT.getJWTClaimsSet();
        var jit = claimSet.getJWTID();

        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .id(jit)
                .expiryTime(claimSet.getExpirationTime())
                .build();

        try {
            invalidatedTokenRepo.save(invalidatedToken);
        } catch (Exception e) {
            log.error("Failed to save invalidated token with ID: {}", jit, e);
            throw new OctException(SAVE_DATABASE_ERROR);
        }

        var user = userRepo.findByUsername(claimSet.getSubject())
                .orElseThrow(() -> new OctException(UNAUTHENTICATED));

        return AuthenticationResponse.builder()
                .token(generateToken(user))
                .authenticated(true)
                .build();
    }

    private String generateToken(User user) {
        var header = new JWSHeader(JWSAlgorithm.HS512);

        var claimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("sondinh")
                .issueTime(new Date())
                .expirationTime(Date.from(Instant.now().plus(VALID_DURATION, ChronoUnit.SECONDS)))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(user))
                .build();

        var payload = new Payload(claimsSet.toJSONObject());
        var jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new IllegalArgumentException("Cannot create token.");
        }
    }

    private SignedJWT verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException {

        var signedJWT = SignedJWT.parse(token);
        var claimSet = signedJWT.getJWTClaimsSet();

        Date expiryTime = (isRefresh)
                ? Date.from(claimSet.getIssueTime().toInstant().plus(REFRESHABLE_DURATION, ChronoUnit.SECONDS))
                : claimSet.getExpirationTime();

        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        boolean isTokenValid = signedJWT.verify(verifier);

        boolean isTokenExpired = !expiryTime.after(new Date());
        boolean isTokenInvalidated = invalidatedTokenRepo.existsById(claimSet.getJWTID());
        if (!isTokenValid || isTokenExpired || isTokenInvalidated) throw new OctException(UNAUTHENTICATED);

        return signedJWT;
    }

    private String buildScope(User user) {
        var role = user.getRole();
        return "ROLE_" + role;
    }
}
