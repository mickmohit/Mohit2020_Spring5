package com.example.demo.exception;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Orders;
import com.example.demo.model.OrderItem;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long>{

}
