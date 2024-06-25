package kipper.login_auth_api.exceptions;

public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException() {
        super("Invalid Password");
    }

    public InvalidPasswordException(String message) {
        super(message);
    }
}
