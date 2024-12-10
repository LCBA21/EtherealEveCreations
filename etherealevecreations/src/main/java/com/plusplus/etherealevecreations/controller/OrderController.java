package com.plusplus.etherealevecreations.controller;



import com.plusplus.etherealevecreations.entity.Order;
import com.plusplus.etherealevecreations.exceptions.ResourceNotFoundException;
import com.plusplus.etherealevecreations.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {


    private final OrderService orderService;


    // Endpoint to place an order
    @PostMapping("/placeOrder/{userId}")
    public ResponseEntity<Order> placeOrder(@PathVariable Long userId) {
        try {
            Order order = orderService.placeOrder(userId);
            return new ResponseEntity<>(order, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint to retrieve an order by ID
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable Long orderId) {
        try {
            Order order = orderService.getOrder(orderId);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }




}
