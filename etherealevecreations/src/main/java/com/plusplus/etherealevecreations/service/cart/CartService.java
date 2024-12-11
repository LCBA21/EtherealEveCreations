package com.plusplus.etherealevecreations.service.cart;

import com.plusplus.etherealevecreations.entity.Cart;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public interface CartService {


    static Cart getCartByUserId(Long userId) {
        return null;
    }

    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);

    Cart createCart();

    void deleteCart(Long cartId);
}
