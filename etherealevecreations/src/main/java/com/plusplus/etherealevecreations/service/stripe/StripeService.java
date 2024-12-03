package com.plusplus.etherealevecreations.service.stripe;

import com.plusplus.etherealevecreations.dto.ProductRequest;
import com.plusplus.etherealevecreations.dto.StripeResponse;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

@Service
public class StripeService {


    public StripeResponse checkoutProduct(ProductRequest productRequest){
        String Strip = "";
        Strip.apiKey="apikeyhere";
        SessionCreateParams.LineItem.PriceData.ProductData productData=SessionCreateParams.LineItem;
        .setName(productRequest.getName()).build();

        SessionCreateParams.LineItem.PriceData priceData=SessionCreateParams.LineItem.builder()
                .setCurrency(productRequest.getCurrency()==null? "USD":productRequest.getCurrency())
                .setUnitAmount(productRequest.getPrice())
                .setProductData(productData)
                .build();

        SessionCreateParams build=SessionCreateParams.builder()
                .setQuantity(productRequest.getQuantity())
                .setPriceData(priceData)
                .build();


        SessionCreateParams.LineItem lineItem=SessionCreateParams.LineItem.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:8080/sucess")
                .setCancel("http://localhost:8080/cancel")
                .addLineItem(lineItem)
                .build();





    }
}
