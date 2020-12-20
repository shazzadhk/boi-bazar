package com.example.online_bookstore.Dao;

import com.example.online_bookstore.Model.ShoppingCart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartDao extends CrudRepository<ShoppingCart,Integer> {
}
