package com.oquintero.blog.security;

import com.oquintero.blog.exception.BlogAPIException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationInMs;

    /**
     * This method generate the token
     *
     * @param authentication
     *
     * @return
     */
    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date currenDate = new Date();
        Date expireDate = new Date(currenDate.getTime()+jwtExpirationInMs);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currenDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

        return token;
    }

    /**
     * This method returns the username from a token.
     *
     * @param token
     *
     * @return
     */
    public String getUsernameFromJWT(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }


    /**
     * This method validate the token.
     *
     * @param token
     * @return
     */
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(jwtSecret)
                    .parseClaimsJws(token);
        }catch (SignatureException ex){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT Signature");
        }catch (MalformedJwtException ex){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT Token");
        }catch (ExpiredJwtException ex){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT Token");
        }catch (UnsupportedJwtException ex){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Unsupported JWT Token");
        }catch (IllegalArgumentException ex){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "JWT claims string is empty");
        }

        return true;
    }
}
