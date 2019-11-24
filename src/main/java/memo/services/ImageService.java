package memo.services;

import memo.dao.InterfacesDao.DaoConnectionInterface;
import memo.entities.ImageEntities;
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
}
