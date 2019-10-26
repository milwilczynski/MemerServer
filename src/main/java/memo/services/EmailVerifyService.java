package memo.services;

import memo.dao.InterfacesDao.DaoConnectionInterface;
import memo.entities.UserRegisterEntities;
import memo.security.email.DecodeVerifyToken;
import memo.security.email.GenerateVerifyEmailToken;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailVerifyService {
    private DaoConnectionInterface dao;

    public EmailVerifyService(@Qualifier("postgres") DaoConnectionInterface dao) {
        this.dao = dao;
    }

    private final String user = "zajac5569@gmail.com"; //TUTAJ IDZIE LOGIN
    private final String password = "!Kicaj123"; //TUTAJ IDZIE HASLO :)
    public void sendMail(UserRegisterEntities userRegisterEntities) {
        GenerateVerifyEmailToken generateVerifyEmailToken = new GenerateVerifyEmailToken();
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user,password);
            }
        });

        try {
            String token = generateVerifyEmailToken.generateEmailVerifyToken(userRegisterEntities);
            Message message = prepareMessage(session,user,userRegisterEntities.getEmail(),token);
            Transport.send(message);
            System.out.println("MAIL SEND!");
        }
        catch (MessagingException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public String verifyEmail(String token){
        DecodeVerifyToken decodeVerifyToken = new DecodeVerifyToken();
        String email = decodeVerifyToken.validToken(token);
        if(email.length() > 0){
            dao.activeAccount(email);
        }
        return "";
    }
    private Message prepareMessage(Session session,String user,String recepient,String token) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Verify your account in memy"); //TYTUL MAILA
            message.setText("http://localhost:8080/verify?token="+token); //TRESC
            return message;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
