package kipper.login_auth_api.controller;

import kipper.login_auth_api.domain.user.User;
import kipper.login_auth_api.dto.LoginRequestDTO;
import kipper.login_auth_api.dto.RegisterRequestDTO;
import kipper.login_auth_api.dto.ResponseDTO;
import kipper.login_auth_api.exceptions.InvalidEmailException;
import kipper.login_auth_api.exceptions.InvalidPasswordException;
import kipper.login_auth_api.infra.security.TokenService;
import kipper.login_auth_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
     private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body) {
        User user = repository.findByEmail(body.email()).orElseThrow(InvalidEmailException::new);

        // onde as senhas sao comparadas
        if (passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
        }
        throw new InvalidPasswordException();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body) {
        Optional<User> user = repository.findByEmail(body.email());
        if (user.isEmpty()) {
            User newUser = new User();

            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setName(body.name());

            repository.save(newUser);

            // onde é gerado um novo token para o novo Usuario
            String token = tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }

}
