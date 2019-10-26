package memo.dao.InterfacesDao;

import java.sql.Connection;
import java.util.ArrayList;

//INTERFEJS Z METODAMI DO PRZEFILTORWANIA INFORMACJIA CZY UZYTKOWNIK ISTNIEJE ITP.
public interface DaoUserFilter {
    ArrayList<Integer> checkIfUserExist(Connection connection, ArrayList<Integer> errorArray,String login);
    ArrayList<Integer> checkIfEmailIsTaken(Connection connection, ArrayList<Integer> errorArray,String email);
}
