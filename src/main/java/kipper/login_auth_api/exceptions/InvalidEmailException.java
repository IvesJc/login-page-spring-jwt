package kipper.login_auth_api.exceptions;

public class InvalidEmailException extends RuntimeException{
    public InvalidEmailException() {
        super("Invalid email");
    }

    public InvalidEmailException(String message) {
        super(message);
    }
}
