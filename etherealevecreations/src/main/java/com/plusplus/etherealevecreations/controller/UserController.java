package com.plusplus.etherealevecreations.controller;


import com.plusplus.etherealevecreations.entity.Cart;
import com.plusplus.etherealevecreations.entity.User;
import com.plusplus.etherealevecreations.repository.UserRepository;
import com.plusplus.etherealevecreations.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;
    private final UserRepository userRepository;

    private final JavaMailSender mailSender;





    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.registerUser(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getPhoneNumber()
        ), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}/cart")
    public ResponseEntity<Cart> getUserCart(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserCart(userId));
    }

    @PostMapping("/{userId}/cart/add")
    public ResponseEntity<String> addToCart(
            @PathVariable Long userId,
            @RequestParam Long productId,
            @RequestParam int quantity) {
        userService.addToCart(userId, productId, quantity);
        return ResponseEntity.ok("Item added to cart.");
    }

    @DeleteMapping("/{userId}/cart/remove/{cartItemId}")
    public ResponseEntity<String> removeFromCart(
            @PathVariable Long userId,
            @PathVariable Long cartItemId) {
        userService.removeFromCart(userId, cartItemId);
        return ResponseEntity.ok("Item removed from cart.");
    }


}
