package kipper.login_auth_api.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class RestErrorMessage {

    private Integer statusCode;
    private HttpStatus httpMessage;
    private String messsage;
}
