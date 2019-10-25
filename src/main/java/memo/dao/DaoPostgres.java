package memo.dao;

import memo.dao.InterfacesDao.DaoConnectionInterface;
import memo.entities.UserEntities;
import memo.entities.UserRegisterEntities;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository("postgres")
public class DaoPostgres implements DaoConnectionInterface {
    private final String url = "jdbc:postgresql://195.150.230.210:5434/";
    private final String user = "2019_zajac_patryk";
    private final String passowrd = "kicaj123";

    @Override
    public int checkUser(UserEntities userEntities) {
        try(Connection connection = createConnection()){
            String query = "SELECT * FROM memy.users WHERE login = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, userEntities.getLogin());
            preparedStatement.setString(2, userEntities.getPassword());

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return 1;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean insertUser(UserRegisterEntities userRegisterEntities) {
        try(Connection connection = createConnection()){
            //LOGIKA ZWIAZANA Z REJESTRACJA
            return true;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    private Connection createConnection() throws SQLException{
        return DriverManager.getConnection(url,user,passowrd);
    }

}
