import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserRegistrationServlet extends HttpServlet {

    private UserRegistrationService userRegistrationService;

    public UserRegistrationServlet() {
        this.userRegistrationService = new UserRegistrationService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String accountType = request.getParameter("accountType");

        User user = new User(username, email, password, accountType);
        userRegistrationService.registerUser(user);

        response.getWriter().println("User registered successfully");
        response.setStatus(HttpServletResponse.SC_CREATED);
    }
}

