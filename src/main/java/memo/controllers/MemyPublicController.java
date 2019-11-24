package memo.controllers;

import memo.entities.UserEntities;
import memo.entities.UserRegisterEntities;
import memo.exceptions.WrongDataException;
import memo.services.EmailVerifyService;
import memo.services.ImageService;
import memo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

//Publiczne api
@RestController
public class MemyPublicController {
    private UserService userService;
    private EmailVerifyService emailVerifyService;

    @Autowired
    public MemyPublicController(UserService userService,EmailVerifyService emailVerifyService) {
        this.userService = userService;
        this.emailVerifyService = emailVerifyService;
    }

    @PostMapping(value = "/login")
    public String getToken(@RequestBody UserEntities userEntities)throws WrongDataException{
        String token = userService.userExist(userEntities);
        if(token != null && !token.equals("") && token.length() > 5){
            return token;
        }
        else {
            throw new WrongDataException();
        }
    }
    @PostMapping(value = "/register")
    public ArrayList<Integer> registerUser(@RequestBody UserRegisterEntities userRegisterEntities){
        ArrayList<Integer> errorArray = userService.createUser(userRegisterEntities);
        if(errorArray.isEmpty()){
            emailVerifyService.sendMail(userRegisterEntities);
        }
        return errorArray;
    }
    @GetMapping(value = "/verify")
    public void verifyAccount(@RequestParam String token){
        emailVerifyService.verifyEmail(token);
    }
    //REQUEST NA ODZYSKIWANIE HASLA ZEBY ODZYSKAC HASLO TRZEBA BEDZIE PODAC MAILA, bedzie generowany jwt token i to bedzie url na maila i z jwt bedzie odczytywany user name
}
