package com.example.online_bookstore.Dao;

import com.example.online_bookstore.Model.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDao extends CrudRepository<Users,Integer> {

    @Query("select p from Users p where p.userName = ?1")
    Users findByUserName(String s);
}
