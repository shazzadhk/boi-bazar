package com.example.online_bookstore.Service;


import com.example.online_bookstore.Model.Orders;
import com.example.online_bookstore.Model.ShippingAddress;
import com.example.online_bookstore.Model.ShoppingCart;
import com.example.online_bookstore.Model.Users;

public interface OrderService {

    Orders createOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress, Users users);

}
