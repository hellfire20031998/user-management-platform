package com.hellFire.PaymentService.paymentGateway;


import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class RazorPaymentGateway implements IPaymentGateway {
    @Override
    public String generatePaymentLink() {
        return "Razorpay Payment link";
    }
}
