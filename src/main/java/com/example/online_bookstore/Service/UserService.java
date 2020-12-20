package com.example.online_bookstore.Service;

import com.example.online_bookstore.Dao.UserDao;
import com.example.online_bookstore.Model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public Users findUsers(Principal principal){

        return userDao.findByUserName(principal.getName());
    }
}
