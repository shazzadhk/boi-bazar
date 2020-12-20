package com.example.online_bookstore.Service;

import com.example.online_bookstore.Dao.CartItemDao;
import com.example.online_bookstore.Dao.ShoppingCartDao;
import com.example.online_bookstore.Model.CartItem;
import com.example.online_bookstore.Model.ShoppingCart;
import com.example.online_bookstore.Model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImp implements ShoppingCartService {

    @Autowired
    private CartItemDao cartItemDao;

    @Autowired
    private ShoppingCartDao shoppingCartDao;


    @Override
    public void clearShoppingCart(ShoppingCart shoppingCart,Users users) {

        List<CartItem> cartItemList = cartItemDao.findByShoppingCart(shoppingCart);

        for (CartItem cartItem : cartItemList) {
            cartItem.setShoppingCart(null);
            cartItemDao.save(cartItem);
        }

        shoppingCart.setGrandTotal(0.0);

        shoppingCart.setUsers(users);

        shoppingCartDao.save(shoppingCart);
    }
}
