package com.plusplus.etherealevecreations.service.user;

import com.plusplus.etherealevecreations.entity.Cart;
import com.plusplus.etherealevecreations.entity.CartItem;
import com.plusplus.etherealevecreations.entity.Product;
import com.plusplus.etherealevecreations.entity.User;
import com.plusplus.etherealevecreations.exceptions.ProductNotFoundException;
import com.plusplus.etherealevecreations.exceptions.ResourceNotFoundException;
import com.plusplus.etherealevecreations.exceptions.UserNotFoundException;
import com.plusplus.etherealevecreations.repository.ProductRepository;
import com.plusplus.etherealevecreations.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ProductRepository productRepository;



//Implement Exception handling for users with the same email address(should not be possible)
    @Override
    public User registerUser(String firstName, String lastName, String email, String password, String phoneNumber) {
        User user=new User(firstName, lastName,email,password,phoneNumber);
        return userRepository.save(user);
    }

    @Override
    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Product not found"));
    }


//implement exception handling for updating a user that doesnt exist
    @Override
    public User updateUserDetails(Long userId, User updatedUser) {
        User existingUser = getUser(userId);
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
        return userRepository.save(existingUser);
    }

    @Override
    public Cart getUserCart(Long userId) {
        User user = getUser(userId);
        return user.getCart();
    }

    @Override
    public void addToCart(Long userId, Long productId, int quantity) {
        User user = getUser(userId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));

        Cart cart = user.getCart();
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setUnitprice(product.getPrice());
        cartItem.setTotalPrice();

        cart.addItem(cartItem);
        userRepository.save(user);
    }

    //implement Exception handling for if cartitem does not exist
    @Override
    public void removeFromCart(Long userId, Long cartItemId) {
        // Retrieve the user by userId, ensuring the user exists
        User user = getUser(userId);

        // Get the cart associated with the user
        Cart cart = user.getCart();

        // Remove the cart item based on the provided cartItemId
        cart.getItems().removeIf(item -> item.getId().equals(cartItemId));

        // Dynamically update the total amount in the cart after removal
        // Calculate the new total by summing up the price of the remaining items

        BigDecimal updatedTotalAmount=cart.getItems().stream()
                .map(CartItem::getTotalprice)// Get the total price of each ite
                        .reduce(BigDecimal.ZERO,BigDecimal::add); // Sum up the total prices

        // Set the updated total amount to the cart
        cart.setTotalAmount(updatedTotalAmount);

        // Save the updated user to persist changes in the cart
        userRepository.save(user);
    }



}