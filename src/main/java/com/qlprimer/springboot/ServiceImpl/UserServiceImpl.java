package com.qlprimer.springboot.ServiceImpl;

import com.qlprimer.springboot.Dao.UserMapper;
import com.qlprimer.springboot.Entity.User;
import com.qlprimer.springboot.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;


    @Override
    public List<User> selectALL() {
        return userMapper.selectALL();
    }
}
