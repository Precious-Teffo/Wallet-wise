
import java.util.HashMap;
import java.util.Map;


public class UserRegistrationService {
    private Map<String, User> users=new HashMap<>();
    
    public void registerUser(User user){
     users.put(user.getUsername(),user);
    }
    
    public User getUser(String username){
       return users.get(username);
    }
}
