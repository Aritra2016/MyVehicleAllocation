package com.aritra.UserService.Config;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;

import io.jsonwebtoken.security.Keys;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


// This method is used to validate the jwt token in the request header
public class JWTWebTokenValidationFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
      String jwt=request.getHeader(JwtSecurityContext.JWT_HEADER);

         if(jwt !=null){
            try{
                //extracting the word bearer from the jwt token
                jwt= jwt.substring(7);
      
                //generating the secret key from the jwt key
                SecretKey key = Keys.hmacShaKeyFor(JwtSecurityContext.JWT_KEY.getBytes());

                Claims claims=Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

                String username= String.valueOf(claims.get("email"));
      String authorities =(String) claims.get("authorities");

      //Conerted the authorities string to a list of GrantedAuthority
          List<GrantedAuthority> auths =AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

          Authentication auth= new UsernamePasswordAuthenticationToken(username, null, auths);

          SecurityContextHolder.getContext().setAuthentication(auth);


            }catch(Exception e){
                throw new BadCredentialsException("Invalid user with this jwt token ");
            }

         }

       filterChain.doFilter(request,response);
    }



    public String populateAuthorities(Collection<? extends org.springframework.security.core.GrantedAuthority> authorities) {
        return authorities.stream()
                .map(org.springframework.security.core.GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }

   


    

}
