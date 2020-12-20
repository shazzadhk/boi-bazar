package com.example.online_bookstore.Model;

import javax.persistence.*;

@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int quantity;
    private double subTotal;

    @ManyToOne()
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders orders;

    @OneToOne
    private Books books;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Books getBooks() {
        return books;
    }

    public void setBooks(Books books) {
        this.books = books;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }
}
