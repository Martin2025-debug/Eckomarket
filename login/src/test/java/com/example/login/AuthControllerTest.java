import com.example.login.controller.AuthController;
import com.example.login.model.User;
import com.example.login.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @Test
    void registerReturnsOk() throws Exception {
        User user = new User("alice", "secret");
        String json = "{\"username\":\"alice\",\"password\":\"secret\"}";

        doNothing().when(authService).register(user);

        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("User registered"));

        verify(authService).register(any(User.class));
    }

    @Test
    void loginSuccess() throws Exception {
        when(authService.authenticate("alice", "secret")).thenReturn(true);

        String json = "{\"username\":\"alice\",\"password\":\"secret\"}";

        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Login successful"));
    }

    @Test
    void loginFailure() throws Exception {
        when(authService.authenticate("alice", "wrong")).thenReturn(false);

        String json = "{\"username\":\"alice\",\"password\":\"wrong\"}";

        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Invalid credentials"));
    }
}