import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLoginController {

    private final UserLoginService loginService;

    @Autowired
    public UserLoginController(UserLoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest loginRequest) {
        UserLoginResponse loginResponse = loginService.login(loginRequest.getUsername(), loginRequest.getPassword());
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }
}
