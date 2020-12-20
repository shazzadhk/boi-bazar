package com.example.online_bookstore.Dao;

import com.example.online_bookstore.Model.Orders;
import com.example.online_bookstore.Model.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao extends CrudRepository<Orders,Integer>{

    @Query("select p from Orders p where p.users = ?1")
    List<Orders> findByUsers(Users users);

}
