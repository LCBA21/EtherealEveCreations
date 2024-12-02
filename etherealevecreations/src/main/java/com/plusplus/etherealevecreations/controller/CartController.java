package com.plusplus.etherealevecreations.controller;


import com.plusplus.etherealevecreations.repository.CartRepository;
import com.plusplus.etherealevecreations.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartRepository cartRepository;
    private final CartService cartService;




}
