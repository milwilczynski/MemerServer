package memo.services;


import memo.dao.InterfacesDao.DaoConnectionInterface;
import memo.entities.UserEntities;
import memo.entities.UserRegisterEntities;
import memo.security.GenerateJwt;
import memo.services.filter.RegularExpressions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    private DaoConnectionInterface dao;
    @Autowired
    public UserService(@Qualifier("postgres") DaoConnectionInterface dao) {
        this.dao = dao;
    }

    public String userExist(UserEntities userEntities){
        String token = null;
        if(dao.checkUser(userEntities) == 1){
            GenerateJwt generateJwt = new GenerateJwt();
            token = generateJwt.generateToken();
            return token;
        }
        return token;
    }
    public ArrayList<Integer> createUser(UserRegisterEntities userRegisterEntities){
        //errorArray jest tylko dla osoby obsugujacej front end zeby sobie ladnie zrobila komunikaty co poszlo nie tak
        RegularExpressions regularExpressions = new RegularExpressions();
        ArrayList<Integer> errorArray = regularExpressions.checkTheCorrectness(userRegisterEntities);

        if(errorArray.isEmpty()){
            errorArray = dao.insertUser(userRegisterEntities);
            return errorArray;
        }
        return errorArray;
    }
}
