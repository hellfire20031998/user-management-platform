package com.hellFire.PaymentService.services;

public interface IPaymentService {
    String generatePaymentLink(Long orderId);
}
