package com.qlprimer.springboot.Dao;

import com.qlprimer.springboot.Entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectALL();
}