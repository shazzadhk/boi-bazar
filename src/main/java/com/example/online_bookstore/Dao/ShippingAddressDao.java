package com.example.online_bookstore.Dao;

import com.example.online_bookstore.Model.ShippingAddress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingAddressDao extends CrudRepository<ShippingAddress,Integer> {
}
