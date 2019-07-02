package com.example.demo.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.domain.User;

@Service
public interface UserService {
    List<User> findUserByIdAndPsd(User user);
    User findUserById(String id);
    List<User> findUser();
    List<User> findUserByContent(String content);
    void updateUser(User user);
    void saveUser(User user);
}