package memo.controllers;

import memo.entities.ImageEntities;
import memo.exceptions.NoPictureException;
import memo.exceptions.TitleInputException;
import memo.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;

//Prywatne api
@RestController
public class MemyController {
    private ImageService imageService;

    @Autowired
    public MemyController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(value = "/random")
    public ImageEntities getRandomPicture(){
        ImageEntities img = imageService.getRandomPictureFromDb();
        return img;
    }
    @PostMapping(value ="/getPictureByTitle")
    public ImageEntities getPictureByTitle(@RequestParam String title) throws TitleInputException, NoPictureException {
        ImageEntities img = imageService.getPictureByTitle(title);
        return img;
    }
}
