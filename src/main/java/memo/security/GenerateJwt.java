package memo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;


public class GenerateJwt {
    public String generateToken(){
        long currentTime = System.currentTimeMillis();
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(JwtConfig.getSecret());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        return Jwts.builder()
                .setSubject("Login") // tutaj będzie login użytkownika
                .claim("roles","user")
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(currentTime + JwtConfig.getExpirationTime()))
                .signWith(signatureAlgorithm, signingKey)
                .compact();
    }
}
