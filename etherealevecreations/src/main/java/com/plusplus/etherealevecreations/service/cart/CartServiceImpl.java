package com.plusplus.etherealevecreations.service.cart;

import com.plusplus.etherealevecreations.entity.Cart;
import com.plusplus.etherealevecreations.entity.CartItem;
import com.plusplus.etherealevecreations.exceptions.ResourceNotFoundException;
import com.plusplus.etherealevecreations.repository.CartItemRepository;
import com.plusplus.etherealevecreations.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartServiceImpl  implements CartService{

    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;



    @Override
    public Cart getCart(Long id) {
    Cart cart=cartRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Cart not Found"));
    BigDecimal totalAmount=cart.getTotalAmount();
    cart.setTotalAmount(totalAmount);
    return  cartRepository.save(cart);

    }

    @Override
    public void clearCart(Long id) {
        Cart cart=getCart(id);
        cartItemRepository.deleteByCartId(id);
        cart.getItems().clear();
        cartRepository.deleteById(id);

    }

    @Override
    public BigDecimal getTotalPrice(Long id) {
        Cart cart=getCart(id);

        return cart.getTotalAmount();
    }
}
