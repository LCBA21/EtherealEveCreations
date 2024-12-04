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
        Cart cart = getCart(id);
        cartItemRepository.deleteByCartId(id);  // Clears all items from the cart
        cart.getItems().clear();  // Clears the in-memory items list
        cart.setTotalAmount(BigDecimal.ZERO);  // Resets the total amount to 0
    }


    @Override
    public BigDecimal getTotalPrice(Long id) {
        Cart cart=getCart(id);

        return cart.getTotalAmount();
    }

    @Override
    public Cart createCart() {
        // Create a new instance of Cart with default values
        Cart cart = new Cart();
        // Save the new cart instance to the database
        return cartRepository.save(cart);
    }

    @Override
    public void deleteCart(Long cartId) {
        // Check if the cart exists before deleting
        if (!cartRepository.existsById(cartId)) {
            throw new ResourceNotFoundException("Cart not found with ID: " + cartId);
        }
        cartRepository.deleteById(cartId);
    }




}
