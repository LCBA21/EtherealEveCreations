package com.plusplus.etherealevecreations.controller;


import com.plusplus.etherealevecreations.repository.CartItemRepository;
import com.plusplus.etherealevecreations.repository.CartRepository;
import com.plusplus.etherealevecreations.service.cart.CartService;
import com.plusplus.etherealevecreations.service.cartitem.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CartItemController {


    private final CartItemRepository cartItemRepository;
    private final CartItemService cartItemService;







}
