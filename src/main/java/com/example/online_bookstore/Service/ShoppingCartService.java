package com.example.online_bookstore.Service;


import com.example.online_bookstore.Model.ShoppingCart;
import com.example.online_bookstore.Model.Users;

public interface ShoppingCartService {

    void clearShoppingCart(ShoppingCart shoppingCart, Users users);
}
