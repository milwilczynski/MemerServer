package memo.services.filter;

import memo.entities.UserRegisterEntities;
import memo.enums.EnumRegisterError;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class RegularExpressions {
    private ArrayList<Integer> errorsArray;

    public RegularExpressions() {
        this.errorsArray = new ArrayList<>();
    }
    //Metoda zwraca tablie intow kazdy int odpowiada jakiemus bledu, opis bledow w dokumentacji
    public ArrayList<Integer> checkTheCorrectness(UserRegisterEntities userRegisterEntities){
        Pattern login = Pattern.compile("[a-zA-Z1-9]{5,12}"); // LOGIN
        Pattern password = Pattern.compile("([!#$%a-zA-Z1-9]){8,40}");//Haslo
        Pattern email = Pattern.compile("([a-zA-Z1-9]+\\@{1}){1}+([a-zA-z]+\\.){1}+[a-zA-Z]{2,33}"); //Mail
        if(!login.matcher(userRegisterEntities.getLogin()).matches()){
            errorsArray.add(EnumRegisterError.PATTERN_LOGIN_ERROR.getErrorCode());
        }
        if(!password.matcher(userRegisterEntities.getPassword()).matches()){
            errorsArray.add(EnumRegisterError.PATTERN_PASSWORD_ERROR.getErrorCode());
        }
        if(!email.matcher(userRegisterEntities.getEmail()).matches()){
            errorsArray.add(EnumRegisterError.PATTERN_EMAIL_ERROR.getErrorCode());
        }
        return errorsArray;
    }
}
