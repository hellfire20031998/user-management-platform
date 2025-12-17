package com.hellFire.PaymentService.paymentGateway;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class StripePaymentGateway implements IPaymentGateway {

    @Value("${stripe-secret-key}")
    private String stripeKey;

    @Override
    public String generatePaymentLink() {

        Stripe.apiKey = stripeKey;

        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl("https://example.com/success")
                        .setCancelUrl("https://example.com/cancel")
                        .addLineItem(
                                SessionCreateParams.LineItem.builder()
                                        .setQuantity(1L)
                                        .setPriceData(
                                                SessionCreateParams.LineItem.PriceData.builder()
                                                        .setCurrency("inr")
                                                        .setUnitAmount(50000L)
                                                        .setProductData(
                                                                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                        .setName("Test Payment")
                                                                        .build()
                                                        )
                                                        .build()
                                        )
                                        .build()
                        )
                        .build();

        try {
            Session session = Session.create(params);
            return session.toJson();
        } catch (StripeException e) {
            throw new RuntimeException("Failed to create Stripe checkout session", e);
        }
    }
}
