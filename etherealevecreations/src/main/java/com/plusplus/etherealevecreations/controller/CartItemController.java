package com.plusplus.etherealevecreations.controller;


import com.plusplus.etherealevecreations.entity.CartItem;
import com.plusplus.etherealevecreations.repository.CartItemRepository;
import com.plusplus.etherealevecreations.repository.CartRepository;
import com.plusplus.etherealevecreations.service.cart.CartService;
import com.plusplus.etherealevecreations.service.cartitem.CartItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart-items")
@Slf4j
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping("/{cartId}/add")
    public ResponseEntity<String> addItemToCart(
            @PathVariable Long cartId,
            @RequestParam Long productId,
            @RequestParam int quantity) {
        cartItemService.addItemToCart(cartId, productId, quantity);
        log.info("Request received: cartId={}, productId={}, quantity={}", cartId, productId, quantity);
        return ResponseEntity.ok("Item added to cart successfully.");
    }

    @DeleteMapping("/{cartId}/remove")
    public ResponseEntity<String> removeItemFromCart(
            @PathVariable Long cartId,
            @RequestParam Long productId) {
        cartItemService.removeItemFromCart(cartId, productId);
        return ResponseEntity.ok("Item removed from cart successfully.");
    }

    @PatchMapping("/{cartId}/update")
    public ResponseEntity<String> updateItemQuantity(
            @PathVariable Long cartId,
            @RequestParam Long productId,
            @RequestParam int quantity) {
        cartItemService.updateItemQuantity(cartId, productId, quantity);
        return ResponseEntity.ok("Item quantity updated successfully.");
    }

    @GetMapping("/{cartId}/item")
    public ResponseEntity<CartItem> getCartItem(
            @PathVariable Long cartId,
            @RequestParam Long productId) {
        CartItem cartItem = cartItemService.getCartItem(cartId, productId);
        return ResponseEntity.ok(cartItem);
    }
}
