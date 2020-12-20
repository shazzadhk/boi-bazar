package com.example.online_bookstore.Model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class ShippingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotEmpty(message = "Shipping Address Street should not be empty")
    private String shippingAddressStreet;
    @NotEmpty(message = "Shipping Address City should not be empty")
    private String shippingAddressCity;
    @NotEmpty(message = "Shipping Address Country should not be empty")
    private String shippingAddressCountry;
    @NotEmpty(message = "Shipping Phone number should not be empty")
    private String shippingAddressPhone;

    @OneToOne
    private Orders orders;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShippingAddressStreet() {
        return shippingAddressStreet;
    }

    public void setShippingAddressStreet(String shippingAddressStreet) {
        this.shippingAddressStreet = shippingAddressStreet;
    }

    public String getShippingAddressCity() {
        return shippingAddressCity;
    }

    public void setShippingAddressCity(String shippingAddressCity) {
        this.shippingAddressCity = shippingAddressCity;
    }

    public String getShippingAddressCountry() {
        return shippingAddressCountry;
    }

    public void setShippingAddressCountry(String shippingAddressCountry) {
        this.shippingAddressCountry = shippingAddressCountry;
    }

    public String getShippingAddressPhone() {
        return shippingAddressPhone;
    }

    public void setShippingAddressPhone(String shippingAddressPhone) {
        this.shippingAddressPhone = shippingAddressPhone;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }
}
