package memo.controllers;

import memo.entities.UserEntities;
import memo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
}
