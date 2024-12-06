package com.plusplus.etherealevecreations.service.user;

import com.plusplus.etherealevecreations.entity.Cart;
import com.plusplus.etherealevecreations.entity.Product;
import com.plusplus.etherealevecreations.entity.User;
import org.springframework.stereotype.Service;


@Service
public interface UserService {
    User registerUser(String firstName, String lastName, String email, String password, String phoneNumber);
    User getUser(Long userId);
    User updateUserDetails(Long userId, User updatedUser);
    Cart getUserCart(Long userId);
    void addToCart(Long userId, Long productId, int quantity);
    void removeFromCart(Long userId, Long cartItemId);
    Product getProductById(Long id);


}
