package com.example.online_bookstore.Dao;

import com.example.online_bookstore.Model.CartItem;
import com.example.online_bookstore.Model.ShoppingCart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemDao extends CrudRepository<CartItem,Integer> {

    @Query("select p from CartItem p where p.shoppingCart =?1")
    List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
}
