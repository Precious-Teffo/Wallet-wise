
@Service
public class UserLoginService {

    public UserLoginResponse login(String username, String password) {
        // Authenticate the user
        if (username.equals("john") && password.equals("password123")) {
            return new UserLoginResponse(true, "Logged in successfully");
        } else {
            return new UserLoginResponse(false, "Invalid username or password");
        }
    }

    boolean authenticateUser(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}