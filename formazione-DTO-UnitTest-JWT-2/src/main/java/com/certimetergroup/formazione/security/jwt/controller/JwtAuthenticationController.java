package com.certimetergroup.formazione.security.jwt.controller;

import com.certimetergroup.formazione.enumeration.ResponseErrorEnum;
import com.certimetergroup.formazione.exception.FailureException;
import com.certimetergroup.formazione.security.jwt.request.JwtRequest;
import com.certimetergroup.formazione.security.jwt.response.JwtResponse;
import com.certimetergroup.formazione.security.jwt.service.JwtTokenService;
import com.certimetergroup.formazione.security.jwt.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private JwtUserDetailsService userDetailsService;



    @PostMapping("/public/authentication")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(jwtRequest.getUsername());
        final String token = jwtTokenService.generateToken(userDetails);

        return ResponseEntity.ok(
            new JwtResponse(token)
        );
    }


    /*****************************************************************************************************************/
    /*****************************************************************************************************************/
    /*****************************************************************************************************************/


    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new FailureException(HttpStatus.UNAUTHORIZED, ResponseErrorEnum.ERR_500, "USER_DISABLED");
        } catch (BadCredentialsException e) {
            throw new FailureException(HttpStatus.UNAUTHORIZED, ResponseErrorEnum.ERR_4, "USER_DISABLED");
        }
    }

}
