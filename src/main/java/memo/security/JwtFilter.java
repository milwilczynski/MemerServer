package memo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtFilter implements javax.servlet.Filter {
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String header = httpServletRequest.getHeader("authorization");
        try{
            if(httpServletRequest == null || !header.startsWith("Bearer ")){
                throw new ServletException("Kapusta");
            }
            else{
                String token = header.substring(7);
                try{
                    Claims claims = Jwts.parser()
                            .setSigningKey(JwtConfig.getSecret())
                            .parseClaimsJws(token)
                            .getBody();
                    servletRequest.setAttribute("claims",claims);
                    filterChain.doFilter(servletRequest,servletResponse);
                }
                catch (Exception e){
                    System.out.println("Blad w jwt filter, odczytywanie tokenu");
                }
            }
        }
        catch (NullPointerException e){
            System.out.println("Brak dostępu!");
        }
    }
}
