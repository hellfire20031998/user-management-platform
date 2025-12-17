package com.hellFire.PaymentService.controllers;

import com.hellFire.PaymentService.services.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class StripeController {

    @Autowired
    private IPaymentService paymentService;

    @GetMapping("/strip/{orderId}")
    public ResponseEntity<String> getPaymentLink(@PathVariable Long orderId) {
        return new ResponseEntity<>(paymentService.generatePaymentLink(orderId), HttpStatus.OK);
    }
}
