package com.aritra.UserService.Config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUti {

    SecretKey key=Keys.hmacShaKeyFor(JwtSecurityContext.JWT_KEY.getBytes());

   public String generateJwtToken(Authentication authentication){

    String jwt= Jwts.builder()
             .setIssuer("Aritra")
             .setIssuedAt(new Date())
             .setExpiration(new Date(new Date().getTime()+864000000)) // 1 day
             .claim("email", authentication.getName())
             .signWith(key)
             .compact();

             return jwt;

   }

   public String getEmailFromJwt(String jwt){
    jwt= jwt.substring(7);
    Claims claims= Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt)
                 .getBody();

    String email=String.valueOf(claims.get("email"));
    return email;
   }
    
}
