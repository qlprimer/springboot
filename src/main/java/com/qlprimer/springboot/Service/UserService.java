package com.qlprimer.springboot.Service;

import com.qlprimer.springboot.Entity.User;

import java.util.List;

public interface UserService {

    List<User> selectALL();
}
