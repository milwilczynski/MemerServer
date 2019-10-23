package memo.controllers;

import memo.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//Prywatne api
@RestController
public class MemyController {
    private ImageService imageService;

    @Autowired
    public MemyController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(value = "/losowe")
    public String hello(){
        return imageService.getRandomPicture();
    }
}
