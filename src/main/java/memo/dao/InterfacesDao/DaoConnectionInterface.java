package memo.dao.InterfacesDao;

import memo.entities.UserEntities;
import memo.entities.UserRegisterEntities;

public interface DaoConnectionInterface {
    int checkUser(UserEntities userEntities);

    boolean insertUser(UserRegisterEntities userRegisterEntities);
}
