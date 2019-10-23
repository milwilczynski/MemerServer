package memo.services;


import memo.dao.InterfacesDao.DaoConnectionInterface;
import memo.entities.UserEntities;
import memo.security.GenerateJwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
}
