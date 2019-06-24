package com.example.demo.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.domain.User;
@Service
public interface UserService {
    List<User> findUser();
    void saveUser(User user);
}
