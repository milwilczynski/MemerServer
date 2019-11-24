package memo.dao.InterfacesDao;

import memo.entities.ImageEntities;
import memo.entities.UserEntities;
import memo.entities.UserRegisterEntities;

import java.util.ArrayList;
import java.util.List;

public interface DaoConnectionInterface {
    int checkUser(UserEntities userEntities);
    ArrayList<Integer> insertUser(UserRegisterEntities userRegisterEntities);
    void activeAccount(String email);
    ImageEntities getRandomImage();

}
