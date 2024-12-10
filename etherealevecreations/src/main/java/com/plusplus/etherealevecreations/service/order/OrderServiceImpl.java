package com.plusplus.etherealevecreations.service.order;


import com.plusplus.etherealevecreations.entity.*;
import com.plusplus.etherealevecreations.exceptions.ResourceNotFoundException;
import com.plusplus.etherealevecreations.num.OrderStatus;
import com.plusplus.etherealevecreations.repository.CartRepository;
import com.plusplus.etherealevecreations.repository.OrderRepository;
import com.plusplus.etherealevecreations.repository.ProductRepository;
import com.plusplus.etherealevecreations.repository.UserRepository;
import com.plusplus.etherealevecreations.service.cart.CartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final UserRepository userRepository; // Assuming a user repository exists
    private final ProductRepository productRepository; // Assuming a product repository exists
    private final CartRepository cartRepository;

    private final CartService cartService;


    @Override
    public Order placeOrder(Long userId) {
        // Step 1: Validate user existence
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        // Step 2: Validate user's cart and ensure it belongs to the user
        Cart cart = cartRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for user id: " + userId));

        // Step 4: Convert CartItems to OrderItems and calculate total amount
        final BigDecimal[] totalAmount = {BigDecimal.ZERO};

        Set<OrderItem> orderItems = cart.getItems().stream()
                .map(cartItem -> {
                    if (!cartItem.getCart().getId().equals(cart.getId())) {
                        throw new IllegalArgumentException("Cart item does not belong to the user's cart.");
                    }

                    OrderItem orderItem = new OrderItem();
                    orderItem.setProduct(cartItem.getProduct());
                    orderItem.setQuantity(cartItem.getQuantity());
                    orderItem.setPrice(cartItem.getUnitprice());
                    orderItem.setOrder(null); // Will be set later when linked to Order

                    // Calculate item total and add to order total
                    BigDecimal itemTotal = cartItem.getUnitprice().multiply(BigDecimal.valueOf(cartItem.getQuantity()));
                    totalAmount[0] = totalAmount[0].add(itemTotal);

                    return orderItem;
                }).collect(Collectors.toSet());

        // Step 5: Create and populate Order object
        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setUser(user);
        order.setOrderItemSet(orderItems);
        System.err.println(orderItems);
        order.setTotalAmount(totalAmount[0]); // Set the calculated total amount

        // Link each OrderItem to the Order
        orderItems.forEach(item -> item.setOrder(order));

        // Step 6: Save Order and clean up cart
        Order savedOrder = orderRepository.save(order);

        // Clear user's cart after order placement
        cart.getItems().clear();
        cart.setTotalAmount(BigDecimal.ZERO);
        cartRepository.save(cart);

        return savedOrder;
    }

    @Override
    public Order getOrder(Long orderId) {

        return orderRepository.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order not Found"));

    }




}
