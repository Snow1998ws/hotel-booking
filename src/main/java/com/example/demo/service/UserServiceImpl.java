package com.example.demo.service;
import java.util.List;
import com.example.demo.domain.User;
import com.example.demo.domain.UserExample;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findUser() {
        return userMapper.selectByExample(new UserExample());
    }

    @Override
    public void saveUser(User user) {
        userMapper.insertSelective(user);
    }
}
