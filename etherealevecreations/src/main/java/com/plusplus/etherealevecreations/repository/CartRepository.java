package com.plusplus.etherealevecreations.repository;

import com.plusplus.etherealevecreations.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

}
