package com.example.online_bookstore.Service;

import com.example.online_bookstore.Dao.UserDao;
import com.example.online_bookstore.Model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService{

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Users user = userDao.findByUserName(s);
        if (user == null){
            throw  new UsernameNotFoundException("User not found");
        }

        return new User(user.getUserName(),user.getPassword(),user.getAuthorities());
    }
}
