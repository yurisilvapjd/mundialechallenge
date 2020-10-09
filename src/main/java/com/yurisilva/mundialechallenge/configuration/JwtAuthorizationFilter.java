package com.yurisilva.mundialechallenge.configuration;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    public JwtAuthorizationFilter(AuthenticationManager auth) {
        super(auth);
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filter) throws IOException, ServletException {
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        String header = request.getHeader(SecurityConstant.TOKEN_HEADER);
        if (StringUtils.isEmpty(header) || !header.startsWith(SecurityConstant.TOKEN_PREFIX)) {
            filter.doFilter(request, response);
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filter.doFilter(request, response);
    }

    public UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstant.TOKEN_HEADER);
        if (!StringUtils.isEmpty(token)) {
            try {
                byte[] signingKey = SecurityConstant.JWT_SECRET.getBytes();

                Jws<Claims> parsedToken = Jwts
                        .parser()
                        .setSigningKey(signingKey)
                        .parseClaimsJws(token.replace("Bearer ", ""));

                String username = parsedToken.getBody().get("username", String.class);
                ArrayList authorities = parsedToken.getBody().get("authorities", ArrayList.class);
                System.out.println("Authorities: ");
                ArrayList<SimpleGrantedAuthority> auth = new ArrayList<>();
                for (Object obj : authorities) {
                    System.out.println(obj.toString() + " |");
                    auth.add(new SimpleGrantedAuthority(obj.toString()));
                }

                if (!StringUtils.isEmpty(username)) {
                    return new UsernamePasswordAuthenticationToken(username, null, auth);
                }
            } catch (ExpiredJwtException e) {
                System.out.println("Request to parse expired JWT : " + token + " failed : " + e.getMessage());
            } catch (UnsupportedJwtException e) {
                System.out.println("Request to parse unsupported JWT : " + token + " failed : " + e.getMessage());
            } catch (MalformedJwtException e) {
                System.out.println("Request to parse invalid JWT : " + token + " failed : " + e.getMessage());
            } catch (SignatureException e) {
                System.out.println("Request to parse JWT with invalid signature : " + token + " failed : " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Request to parse empty or null JWT : " + token + " failed : " + e.getMessage());
            }
        }

        return null;
    }
}
