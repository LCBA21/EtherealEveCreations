package com.plusplus.etherealevecreations.service.order;


import com.plusplus.etherealevecreations.dto.OrderDTO;
import com.plusplus.etherealevecreations.entity.*;
import com.plusplus.etherealevecreations.exceptions.ResourceNotFoundException;
import com.plusplus.etherealevecreations.num.OrderStatus;
import com.plusplus.etherealevecreations.repository.CartRepository;
import com.plusplus.etherealevecreations.repository.OrderRepository;
import com.plusplus.etherealevecreations.repository.ProductRepository;
import com.plusplus.etherealevecreations.repository.UserRepository;
import com.plusplus.etherealevecreations.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartService cartService;

    @Override
    public OrderDTO placeOrder(Long userId) {
        // Fetch cart for the user
        Cart cart = CartService.getCartByUserId(userId);

        // Create and save order
        Order order = createOrder(cart);
        List<OrderItem> orderItemList = createOrderItems(order, cart);
        order.setOrderItemSet(new HashSet<>(orderItemList));
        order.setTotalAmount(calculateTotalAmount(orderItemList));
        Order savedOrder = orderRepository.save(order);

        // Clear the cart
        cartService.clearCart(cart.getId());

        // Convert to DTO and return
        return mapToOrderDTO(savedOrder);
    }


    private Order createOrder(Cart cart) {
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDate.now());
        return order;
    }

    private List<OrderItem> createOrderItems(Order order, Cart cart) {
        return cart.getItems().stream().map(cartItem -> {
            Product product = cartItem.getProduct();
            if (product.getQuantity() < cartItem.getQuantity()) {
                throw new IllegalArgumentException("Insufficient stock for product: " + product.getName());
            }
            product.setQuantity(product.getQuantity() - cartItem.getQuantity());
            productRepository.save(product);
            return new OrderItem(
                    order,
                    product,
                    cartItem.getQuantity(),
                    cartItem.getUnitprice()
            );
        }).toList();
    }

    private BigDecimal calculateTotalAmount(List<OrderItem> orderItemList) {
        return orderItemList.stream()
                .map(item -> item.getPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public Order getOrder(Long orderId) {
        // Fetch the order
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not Found"));

        // Convert to DTO and return
        return mapToOrderDTO(order);
    }

//    private OrderDTO mapToOrderDTO(Order order) {
//        // Map Order to OrderDTO
//        OrderDTO orderDTO = new OrderDTO();
//        orderDTO.setId(order.getId());
//        orderDTO.setUserId(order.getUser().getId());
//        orderDTO.setOrderDate(order.getOrderDate().atStartOfDay());
//        orderDTO.setTotalAmount(order.getTotalAmount());
//        orderDTO.setStatus(order.getOrderStatus().toString());
//
//        // Map OrderItems to OrderItemDTOs
//        List<OrderItemDTO> orderItems = order.getOrderItemSet().stream()
//                .map(item -> new OrderItemDTO(
//                        item.getProduct().getId(),
//                        item.getProduct().getName(),
//                        item.getQuantity(),
//                        item.getPrice()
//                ))
//                .toList();
//        orderDTO.setItems(orderItems);
//
//        return orderDTO;
//    }


}
