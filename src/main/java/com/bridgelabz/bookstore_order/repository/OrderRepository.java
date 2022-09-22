package com.bridgelabz.bookstore_order.repository;

import com.bridgelabz.bookstore_order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long>{
    @Query(value = "SELECT * FROM ORDER WHERE user_id=:userId", nativeQuery = true)
    List<Order> getOrderByUserId(long userId);
}
