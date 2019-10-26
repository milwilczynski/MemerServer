package memo.dao;

import memo.dao.InterfacesDao.DaoUserFilter;
import memo.entities.UserRegisterEntities;
import memo.enums.EnumRegisterError;

import java.sql.*;
import java.util.ArrayList;

public class DaoFilter implements DaoUserFilter {
    @Override
    public ArrayList<Integer> checkIfUserExist(Connection connection, ArrayList<Integer> errorArray, String login) {
        try{
            String query = "SELECT * FROM memy.users WHERE login = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                errorArray.add(EnumRegisterError.DATABASE_USER_EXIST_ERROR.getErrorCode());
            }
        }
        catch (SQLException e){
            errorArray.add(EnumRegisterError.DATABASE_SQL_EXCEPTION_ERROR.getErrorCode());
        }
        catch (Exception e){
            errorArray.add(EnumRegisterError.DATABASE_EXCEPTION_ERROR.getErrorCode());
        }
        return errorArray;
    }

    @Override
    public ArrayList<Integer> checkIfEmailIsTaken(Connection connection, ArrayList<Integer> errorArray, String email) {
        try{
            String query = "SELECT * FROM memy.users WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                errorArray.add(EnumRegisterError.DATABASE_EMAIL_IS_TAKEN_EROR.getErrorCode());
            }
        }
        catch (SQLException e){
            errorArray.add(EnumRegisterError.DATABASE_SQL_EXCEPTION_ERROR.getErrorCode());
        }
        catch (Exception e){
            errorArray.add(EnumRegisterError.DATABASE_EXCEPTION_ERROR.getErrorCode());
        }
        return errorArray;
    }
    public ArrayList<Integer> checkEmailAndLogin(Connection connection, ArrayList<Integer> errorArray, UserRegisterEntities userRegisterEntities){
        errorArray = checkIfEmailIsTaken(connection,errorArray,userRegisterEntities.getEmail());
        errorArray = checkIfUserExist(connection,errorArray,userRegisterEntities.getLogin());
        return  errorArray;
    }
}
