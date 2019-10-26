package memo.security.email;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import memo.security.JwtConfig;

public class DecodeVerifyToken {
    //metoda bedzie wybierac email z tokenu i wysylac go do bazy ktora zaktualizuje pole verify
    public String validToken(String token){
        String email = "";
        try{
            Claims claims = Jwts.parser()
                    .setSigningKey(JwtConfig.getSecret())
                    .parseClaimsJws(token)
                    .getBody();
            email = (String) claims.get("email");
        }
        catch (Exception e){
            System.out.println("Error while parsing jwt token, in DecodeVerifyToken");
        }
        return email;
    }
}
