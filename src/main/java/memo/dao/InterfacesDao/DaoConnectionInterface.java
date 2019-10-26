package memo.dao.InterfacesDao;

import memo.entities.UserEntities;
import memo.entities.UserRegisterEntities;

import java.util.ArrayList;

public interface DaoConnectionInterface {
    int checkUser(UserEntities userEntities);
    ArrayList<Integer> insertUser(UserRegisterEntities userRegisterEntities);
}
