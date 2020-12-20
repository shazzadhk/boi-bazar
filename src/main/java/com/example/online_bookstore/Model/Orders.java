package com.example.online_bookstore.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Date orderDate;
    private String orderStatus;
    private double orderTotal;

    @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL)
    private List<CartItem> cartItemList;

    @OneToOne(cascade = CascadeType.ALL)
    private ShippingAddress shippingAddress;

    @ManyToOne
    private Users users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
