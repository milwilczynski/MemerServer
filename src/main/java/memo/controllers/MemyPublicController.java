package memo.controllers;

import memo.entities.UserEntities;
import memo.entities.UserRegisterEntities;
import memo.services.ImageService;
import memo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

//Publiczne api
@RestController
public class MemyPublicController {
    private UserService userService;

    @Autowired
    public MemyPublicController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/login")
    public String getToken(@RequestBody UserEntities userEntities){
        return userService.userExist(userEntities);
    }
    @PostMapping(value = "/register")
    public ArrayList<Integer> registerUser(@RequestBody UserRegisterEntities userRegisterEntities){
        return userService.createUser(userRegisterEntities);
    }
    //REQUEST NA ODZYSKIWANIE HASLA ZEBY ODZYSKAC HASLO TRZEBA BEDZIE PODAC MAILA, bedzie generowany jwt token i to bedzie url na maila i z jwt bedzie odczytywany user name
}
