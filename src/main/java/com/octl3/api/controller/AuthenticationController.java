package com.octl3.api.controller;

import com.nimbusds.jose.JOSEException;
import com.octl3.api.commons.OctResponse;
import com.octl3.api.dto.request.AuthenticationRequest;
import com.octl3.api.dto.request.IntrospectRequest;
import com.octl3.api.dto.request.LogoutRequest;
import com.octl3.api.dto.request.RefreshRequest;
import com.octl3.api.dto.response.AuthenticationResponse;
import com.octl3.api.dto.response.IntrospectResponse;
import com.octl3.api.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

import static com.octl3.api.commons.OctResponse.build;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/token")
    OctResponse<AuthenticationResponse> authenticated(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse result = authenticationService.authenticated(request);
        return build(result);
    }

    @PostMapping("/introspect")
    OctResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return build(result);
    }

    @PostMapping("/logout")
    OctResponse<String> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request);
        return build("Logout success");
    }

    @PostMapping("/refresh")
    OctResponse<AuthenticationResponse> refreshToken(@RequestBody RefreshRequest request) throws ParseException, JOSEException {
        var response = authenticationService.refreshToken(request);
        return OctResponse.build(response);
    }
}
