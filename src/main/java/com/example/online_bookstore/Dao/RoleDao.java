package com.example.online_bookstore.Dao;

import com.example.online_bookstore.Model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleDao extends CrudRepository<Role,Integer> {
}
