package com.example.online_bookstore.Model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotEmpty(message = "Username field should not be empty")
    private String userName;
    @NotNull
    @Size(min = 6,max = 8,message = "Password must be between 6 and 8 characters")
    private String password;
    private String firstName;
    private String lastName;
    @NotEmpty(message = "Email field should not be empty")
    private String email;
    @Size(min = 10,max = 11,message = "Phone Number should be 11 digit")
    private String phone;
    private boolean enabled = true;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user",fetch = FetchType.EAGER)
    private List<Role> roles;

    @OneToOne(cascade = CascadeType.ALL)
    private ShoppingCart shoppingCart;

//    @OneToMany(mappedBy = "users",cascade = CascadeType.ALL)
//    private List<Orders> orderList;

    public Collection<? extends GrantedAuthority> getAuthorities(){
        Set<GrantedAuthority> authorities = new HashSet<>();
        roles.forEach(r -> authorities.add(new SimpleGrantedAuthority(r.getRoleName())));
        return authorities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

//    public List<Orders> getOrderList() {
//        return orderList;
//    }
//
//    public void setOrderList(List<Orders> orderList) {
//        this.orderList = orderList;
//    }
}
