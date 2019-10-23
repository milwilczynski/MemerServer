package memo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//Prywatne api
@RestController
public class MemyController {
    @GetMapping(value = "/losowe")
    public String hello(){
        return "hello";
    }
}
