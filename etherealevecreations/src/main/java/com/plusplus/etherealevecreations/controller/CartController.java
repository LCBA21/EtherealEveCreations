package com.plusplus.etherealevecreations.controller;


import com.plusplus.etherealevecreations.entity.Cart;
import com.plusplus.etherealevecreations.repository.CartRepository;
import com.plusplus.etherealevecreations.service.cart.CartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    @GetMapping("getCart/{cartId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long cartId) {
        Cart cart = cartService.getCart(cartId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("CreateCart")
    public ResponseEntity<Cart> createCart() {
        Cart cart = cartService.createCart(); // Assuming `createCart` is implemented in `CartService`.
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @DeleteMapping("DeleteCart/{cartId}")
    public ResponseEntity<String> deleteCart(@PathVariable Long cartId) {
        cartService.deleteCart(cartId); // Assuming `deleteCart` is implemented in `CartService`.
        return ResponseEntity.ok("Cart deleted successfully.");
    }

    @Transactional
    @PutMapping("clearCart/{cartId}")
    public ResponseEntity<String> clearCart(@PathVariable Long cartId) {
        cartService.clearCart(cartId);
        return ResponseEntity.ok("Cart cleared successfully.");
    }

}

