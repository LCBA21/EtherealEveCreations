package com.plusplus.etherealevecreations.repository;

import com.plusplus.etherealevecreations.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    void deleteByCartId(Long id);
}
