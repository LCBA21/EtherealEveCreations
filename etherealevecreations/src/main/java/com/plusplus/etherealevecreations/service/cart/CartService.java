package com.plusplus.etherealevecreations.service.cart;

import com.plusplus.etherealevecreations.entity.Cart;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public interface CartService {

    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);
}
