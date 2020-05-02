package com.certimetergroup.formazione.security.jwt.response;

public class JwtResponse {

    private final String jwttoken;



    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }



    public String getToken() {
        return this.jwttoken;
    }



    @Override
    public String toString() {
        return "JwtResponse{" +
                "jwttoken='" + jwttoken + '\'' +
                '}';
    }
}
