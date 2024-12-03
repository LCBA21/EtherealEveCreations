package com.plusplus.etherealevecreations.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StripeResponse {

    private String status;
    private String message;
    private String sessionId;
    private String sessionUrl;


}
