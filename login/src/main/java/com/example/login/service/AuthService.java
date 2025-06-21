package main.java.com.example.login.service;

import com.example.login.model.User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AuthService {

    private final Map<String, String> users = new ConcurrentHashMap<>();

    public void register(User user) {
        users.put(user.getUsername(), user.getPassword());
    }

    public boolean authenticate(String username, String password) {
        return password != null && password.equals(users.get(username));
    }
}