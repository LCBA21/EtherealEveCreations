package com.plusplus.etherealevecreations.service.order;


import com.plusplus.etherealevecreations.dto.OrderDTO;
import com.plusplus.etherealevecreations.entity.Order;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    OrderDTO placeOrder(Long userId);
    Order getOrder(Long orderId);
}
