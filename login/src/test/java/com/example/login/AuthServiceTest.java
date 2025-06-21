import com.example.login.model.User;
import com.example.login.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {

    private AuthService authService;

    @BeforeEach
    void setUp() {
        authService = new AuthService();
    }

    @Test
    void registerAndAuthenticateSuccess() {
        User user = new User("alice", "secret");
        authService.register(user);
        assertTrue(authService.authenticate("alice", "secret"));
    }

    @Test
    void authenticateFailsWithWrongPassword() {
        User user = new User("bob", "password");
        authService.register(user);
        assertFalse(authService.authenticate("bob", "wrong"));
    }
}