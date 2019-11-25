package memo.dao;

import memo.dao.InterfacesDao.DaoConnectionInterface;
import memo.entities.ImageEntities;
import memo.entities.UserEntities;
import memo.entities.UserRegisterEntities;
import memo.enums.EnumRegisterError;
import memo.exceptions.NoPictureException;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("postgres")
public class DaoPostgres implements DaoConnectionInterface {
    private final String url = "jdbc:postgresql://195.150.230.210:5434/";
    private final String user = "2019_zajac_patryk";
    private final String passowrd = "kicaj123";

    @Override
    public int checkUser(UserEntities userEntities) {
        try(Connection connection = createConnection()){
            String query = "SELECT * FROM memy.users WHERE login = ? AND password = crypt(?,password);";
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
    public ArrayList<Integer> insertUser(UserRegisterEntities userRegisterEntities) {
        ArrayList<Integer> errorArray = new ArrayList<>();
        DaoFilter daoFilter = new DaoFilter();
        try(Connection connection = createConnection()){
            daoFilter.checkEmailAndLogin(connection,errorArray,userRegisterEntities);
            if(errorArray.size() == 0){
                String query = "INSERT INTO memy.users (login,password,email,verify) VALUES(?,crypt(?,gen_salt('md5')),?,?);";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1,userRegisterEntities.getLogin());
                preparedStatement.setString(2,userRegisterEntities.getPassword());
                preparedStatement.setString(3,userRegisterEntities.getEmail());
                preparedStatement.setBoolean(4,false);
                preparedStatement.executeUpdate();
            }
        }
        catch (SQLException e){
            errorArray.add(EnumRegisterError.DATABASE_SQL_EXCEPTION_ERROR.getErrorCode());
        }
        return errorArray;
    }

    @Override
    public void activeAccount(String email) {
        DaoFilter daoFilter = new DaoFilter();
        try(Connection connection = createConnection()){
            if(daoFilter.checkIfExmailExist(connection,email)){
                String query = "UPDATE memy.users SET verify = true WHERE email = ?;";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1,email);
                preparedStatement.executeUpdate();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public ImageEntities getRandomImage() {
        try(Connection connection = createConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM memy.images ORDER BY random() LIMIT 1;");
            resultSet.next();
            ImageEntities img = ImageEntities.builder()
                    .imageTitle(resultSet.getString("image_title"))
                    .imageName(resultSet.getString("image_name"))
                    .imageScore(resultSet.getInt("image_score"))
                    .build();
            return img;
        }
        catch (SQLException e){
            System.out.println("DaoPosgres - getRandomImage");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ImageEntities findPictureByTitle(String title)throws NoPictureException {
        try(Connection connection = createConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM memy.images WHERE image_title = '"+title+"'");
            if(!resultSet.next()){
                throw new NoPictureException("/getPictureByTitle");
            }
            else{
                ImageEntities img = ImageEntities.builder()
                        .imageTitle(resultSet.getString("image_title"))
                        .imageName(resultSet.getString("image_name"))
                        .imageScore(resultSet.getInt("image_score"))
                        .build();
                return img;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<ImageEntities> getPictures(int intPage) {
        try(Connection connection = createConnection()){
            ArrayList<ImageEntities> images = new ArrayList<>();
            String sql = "SELECT * FROM memy.images order by id DESC OFFSET ? LIMIT 10;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,intPage * 10);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                ImageEntities img = ImageEntities.builder()
                        .imageTitle(resultSet.getString("image_title"))
                        .imageName(resultSet.getString("image_name"))
                        .imageScore(resultSet.getInt("image_score"))
                        .build();
                images.add(img);
            }
            return images;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean checkIfIncreasePossible(String login, String name) {
        try(Connection connection = createConnection()){
            String sql = "SELECT * FROM memy.score WHERE (SELECT id FROM memy.users WHERE login = ?) = user_id AND (SELECT id FROM memy.images WHERE image_name = ?) = image_id;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,login);
            preparedStatement.setString(2,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                preparedStatement.close();
                resultSet.close();
                return false;
            }
            else{
                String sqlInsert =
                        "INSERT INTO memy.score(user_id,image_id)\n" +
                        "VALUES((SELECT id FROM memy.users WHERE login = ?),(SELECT id FROM memy.images WHERE image_name = ?));";
                PreparedStatement ps = connection.prepareStatement(sqlInsert);
                ps.setString(1,login);
                ps.setString(2,name);
                ps.executeUpdate();
                ps = connection.prepareStatement("UPDATE memy.images SET image_score = (SELECT image_score FROM memy.images WHERE image_name = ?)+1 WHERE image_name = ?");
                ps.setString(1,name);
                ps.setString(2,name);
                ps.executeUpdate();
                return true;
            }
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
