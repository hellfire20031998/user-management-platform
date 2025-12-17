package com.hellFire.PaymentService.paymentGateway;


import org.springframework.stereotype.Service;

@Service
public class RazorPaymentGateway implements IPaymentGateway {
    @Override
    public String generatePaymentLink() {
        return "";
    }
}
