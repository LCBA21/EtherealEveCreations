package com.plusplus.etherealevecreations.service.order;


import com.plusplus.etherealevecreations.entity.Order;
import com.plusplus.etherealevecreations.exceptions.ResourceNotFoundException;
import com.plusplus.etherealevecreations.repository.OrderRepository;
import com.plusplus.etherealevecreations.repository.ProductRepository;
import com.plusplus.etherealevecreations.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final UserRepository userRepository; // Assuming a user repository exists
    private final ProductRepository productRepository; // Assuming a product repository exists


    @Override
    public Order placeOrder(Long userId) {
        return null;
    }

    @Override
    public Order getOrder(Long orderId) {

        return orderRepository.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order not Found"));

    }
}
