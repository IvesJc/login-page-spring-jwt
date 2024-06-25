package kipper.login_auth_api.infra.handler;

import kipper.login_auth_api.exceptions.InvalidEmailException;
import kipper.login_auth_api.exceptions.InvalidPasswordException;
import kipper.login_auth_api.infra.RestErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidEmailException.class)
    private ResponseEntity<RestErrorMessage> emailNotFoundHandler(InvalidEmailException e){
        RestErrorMessage errorMessage = new RestErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND,
                e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
    @ExceptionHandler(InvalidPasswordException.class)
    private ResponseEntity<RestErrorMessage> wrongPasswordHandler(InvalidPasswordException e){
        RestErrorMessage errorMessage = new RestErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND,
                e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }


}
