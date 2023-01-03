package com.oquintero.blog.payload;

public class JWTAuthResponseDTO {
    private String accessToken;
    private String tokenType = "Bearer";

    public JWTAuthResponseDTO(String accessToken){
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
