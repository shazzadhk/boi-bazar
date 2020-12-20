package com.example.online_bookstore.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private double grandTotal;

    @OneToMany(mappedBy = "shoppingCart",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<CartItem> cartItemList = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "shoppingCart")
    private Users users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public Set<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(Set<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
