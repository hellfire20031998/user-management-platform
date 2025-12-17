package com.hellFire.PaymentService.paymentGateway;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
public class StripePaymentGateway implements IPaymentGateway {
    @Override
    public String generatePaymentLink() {
        return "Stripe payment link";
    }
}
