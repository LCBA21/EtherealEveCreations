package com.plusplus.etherealevecreations.repository;

import com.plusplus.etherealevecreations.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
