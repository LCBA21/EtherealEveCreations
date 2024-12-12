package com.plusplus.etherealevecreations.controller;



import com.plusplus.etherealevecreations.dto.OrderDTO;
import com.plusplus.etherealevecreations.entity.Order;
import com.plusplus.etherealevecreations.exceptions.ResourceNotFoundException;
import com.plusplus.etherealevecreations.response.ApiResponse;
import com.plusplus.etherealevecreations.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // Endpoint to place an order
    @PostMapping("/placeOrder/{userId}")
    public ResponseEntity<ApiResponse<OrderDTO>> createOrder(@PathVariable Long userId) {
        try {
            OrderDTO order = orderService.placeOrder(userId);
            return ResponseEntity.ok(new ApiResponse( "Order successfully placed", order));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponse( e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse( "An error occurred while placing the order", null));
        }
    }

    // Endpoint to retrieve an order by ID
    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse<OrderDTO>> getOrder(@PathVariable Long orderId) {
        try {
            OrderDTO order = orderService.getOrder(orderId);
            return ResponseEntity.ok(new ApiResponse(
                    "Order retrieved successfully", order));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse( "Order not found: " + e.getMessage(), null));
        }
    }
}
