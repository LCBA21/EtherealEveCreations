package com.plusplus.etherealevecreations.repository;


import com.plusplus.etherealevecreations.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
