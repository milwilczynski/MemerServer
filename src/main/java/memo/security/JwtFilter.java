package memo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter implements javax.servlet.Filter {
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String header = httpServletRequest.getHeader("authorization");
        try{
            if(httpServletRequest == null || !header.startsWith("Bearer ")){
                throw new ServletException("JwtFitler request is null or header didnt start with Bearer");
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
                    httpServletResponse.setStatus(403);
                    System.out.println("Error while parsing jwt token");
                }
            }
        }
        catch (NullPointerException e){
            httpServletResponse.setStatus(403);
            System.out.println("Acces denied!");
        }
    }
}
