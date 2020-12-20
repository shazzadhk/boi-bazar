package com.example.online_bookstore.Service;

import com.example.online_bookstore.Dao.CartItemDao;
import com.example.online_bookstore.Dao.OrderDao;
import com.example.online_bookstore.Dao.ShippingAddressDao;
import com.example.online_bookstore.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    private CartItemDao cartItemDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ShippingAddressDao shippingAddressDao;

    @Override
    public Orders createOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress, Users users) {

        Orders order = new Orders();
        order.setOrderDate(Calendar.getInstance().getTime());
        order.setOrderStatus("created");
        order.setOrderTotal(shoppingCart.getGrandTotal());
        order.setShippingAddress(shippingAddress);

        List<CartItem> cartItemList = cartItemDao.findByShoppingCart(shoppingCart);

        for (CartItem cartItem: cartItemList) {
            Books book = cartItem.getBooks();
            cartItem.setOrders(order);
            book.setInStockNumber(book.getInStockNumber() - cartItem.getQuantity());
        }

        order.setCartItemList(cartItemList);
        order.setUsers(users);
        orderDao.save(order);

        shippingAddress.setOrders(order);
        shippingAddressDao.save(shippingAddress);

        return order;
    }

}
