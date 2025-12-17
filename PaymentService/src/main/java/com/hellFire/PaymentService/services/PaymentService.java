package com.hellFire.PaymentService.services;


import com.hellFire.PaymentService.paymentGateway.IPaymentGateway;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private IPaymentGateway paymentGateway;

    public PaymentService(IPaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public String generatePaymentLink(Long orderId) {
        return paymentGateway.generatePaymentLink();
    }
}
