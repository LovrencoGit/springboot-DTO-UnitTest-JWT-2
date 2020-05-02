package com.certimetergroup.formazione.security.jwt.entrypoint;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.certimetergroup.formazione.enumeration.ResponseErrorEnum;
import com.certimetergroup.formazione.response.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{

    Logger log = LoggerFactory.getLogger(this.getClass());


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        log.error("ENTRY POINT => " + HttpServletResponse.SC_UNAUTHORIZED);

        response.setStatus( HttpStatus.UNAUTHORIZED.value() );
        response.setContentType( MediaType.APPLICATION_JSON_VALUE );
        response.getOutputStream().println( new ObjectMapper().writeValueAsString(
        	new Response(ResponseErrorEnum.ERR_4)
        ));
    }

}
