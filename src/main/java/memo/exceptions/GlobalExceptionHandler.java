package memo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({SQLException.class})
    public ResponseEntity<Object> sqlError(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("SQL error");
    }
    @ExceptionHandler({TitleInputException.class})
    public ResponseEntity<Object> titleError(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Wrong title");
    }
    @ExceptionHandler({NoPictureException.class})
    public ResponseEntity<Object> noPictureException(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No picture in db");
    }
    @ExceptionHandler({WrongDataException.class})
    public ResponseEntity<Object> wrongDataLogInError(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Wrong password or login");
    }
}
