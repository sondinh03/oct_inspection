package com.octl3.api.service;

import com.nimbusds.jose.JOSEException;
import com.octl3.api.dto.request.AuthenticationRequest;
import com.octl3.api.dto.request.IntrospectRequest;
import com.octl3.api.dto.request.LogoutRequest;
import com.octl3.api.dto.request.RefreshRequest;
import com.octl3.api.dto.response.AuthenticationResponse;
import com.octl3.api.dto.response.IntrospectResponse;

import java.text.ParseException;


public interface AuthenticationService {
    AuthenticationResponse authenticated(AuthenticationRequest request);

    IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;

    void logout(LogoutRequest request) throws ParseException, JOSEException;

    AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException;
}
