package com.plusplus.etherealevecreations.service.cartitem;


import com.plusplus.etherealevecreations.entity.CartItem;
import org.springframework.stereotype.Service;

@Service
public interface CartItemService {

    void addItemToCart(Long cartId,Long productId,int quantity);
    void removeItemFromCart(Long cartId,Long productId);
    void updateItemQuantity(Long cartId,Long productId,int quantity);
    CartItem getCartItem(Long cartId, Long productId);
}
