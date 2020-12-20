package com.example.online_bookstore.Model;

import javax.persistence.*;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roleId;
    private String roleName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Users user;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
