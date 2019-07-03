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
    public List<User> findUserByIdAndPsd(User user) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserIdEqualTo(user.getUserId());
        criteria.andPsdEqualTo(user.getPsd());
        return userMapper.selectByExample(userExample);
    }

    @Override
    public User findUserById(String id) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserIdEqualTo(id);
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> findUserByContent(String content)
    {
        UserExample userExample=new UserExample();
        userExample.or().andCityEqualTo(content);
        userExample.or().andGenderEqualTo(content);
        userExample.or().andMailEqualTo(content);
        userExample.or().andNickLike("%" + content + "%");
        userExample.or().andTelEqualTo(content);
        userExample.or().andUserIdLike("%" + content + "%");
        userExample.or().andUserNameLike("%" + content + "%");
        userExample.or().andGenderEqualTo(content);
        userExample.or().andPsdLike("%" + content + "%");
        if(isInt(content))
        {
            userExample.or().andPermEqualTo(Integer.valueOf(content));
        }
        return userMapper.selectByExample(userExample);
    }
    @Override
    public List<User> findUser(){
        return userMapper.selectByExample(new UserExample());
    }

    @Override
    public void saveUser(User user) {
        userMapper.insertSelective(user);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }
    public boolean isInt(String s)
    {
        for(int i=0;i<s.length();i++)
        {
            if(!Character.isDigit(s.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }
}
