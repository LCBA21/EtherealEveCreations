package com.plusplus.etherealevecreations.controller;


import com.plusplus.etherealevecreations.dto.ProductRequest;
import com.plusplus.etherealevecreations.dto.StripeResponse;
import com.plusplus.etherealevecreations.service.stripe.StripeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/PaymentGateway")
public class ProductCheckoutController {

    private final StripeService stripeService;

    @PostMapping("/checkout")
    public ResponseEntity<StripeResponse> checkoutProducts(@RequestBody ProductRequest productRequest){
        StripeResponse stripeResponse=stripeService.checkoutProduct(productRequest);
        return ResponseEntity.
                status(HttpStatus.OK)
                .body(stripeResponse);
    }
}
