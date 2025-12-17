package com.hellFire.PaymentService.paymentGateway;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class StripePaymentGateway implements IPaymentGateway {
    @Override
    public String generatePaymentLink() {
        return "";
    }
}
