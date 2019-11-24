package memo.services;

import memo.dao.InterfacesDao.DaoConnectionInterface;
import memo.entities.ImageEntities;
import memo.exceptions.NoPictureException;
import memo.exceptions.TitleInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
public class ImageService {
    private DaoConnectionInterface dao;
    @Autowired
    public ImageService(@Qualifier("postgres") DaoConnectionInterface dao) {
        this.dao = dao;
    }
    public ImageEntities getRandomPictureFromDb(){
        ImageEntities img = dao.getRandomImage();
        if(img != null){
            return img;
        }
        else{
            //tutaj bedzie throw wyjatek
        }
        return null;
    }
    public ImageEntities getPictureByTitle(String title) throws TitleInputException, NoPictureException {
        if(title.length() == 0){
            throw new TitleInputException();
        }
        ImageEntities img = dao.findPictureByTitle(title);
        return img;
    }

    public ArrayList<ImageEntities> getPictures(String page) {
        if(!Double.isNaN(Double.parseDouble(page))){
            int intPage = Integer.parseInt(page);
            ArrayList<ImageEntities> images = dao.getPictures(intPage);
            return images;
        }
        return null;
    }
}
