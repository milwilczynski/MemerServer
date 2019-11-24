package memo.controllers;

import memo.entities.ImageEntities;
import memo.entities.IncreaseEntities;
import memo.exceptions.NoPictureException;
import memo.exceptions.TitleInputException;
import memo.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    @GetMapping(value ="/getPictures")
    public List<ImageEntities> getPictures(@RequestParam String page){
        ArrayList<ImageEntities> images = imageService.getPictures(page);
        return images;
    }
    @PostMapping(value = "/increaseScore")
    public boolean increaseScore(@RequestBody IncreaseEntities increaseEntities){
        boolean decision = imageService.increase(increaseEntities);
        return decision;
    }
}
