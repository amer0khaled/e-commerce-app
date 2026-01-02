package com.amer.ecommerce.payment.service.impl;

import com.amer.ecommerce.payment.api.dto.PaymentRequest;
import com.amer.ecommerce.payment.api.dto.PaymentResponse;
import com.amer.ecommerce.payment.api.mapper.PaymentMapper;
import com.amer.ecommerce.payment.domain.PaymentStatus;
import com.amer.ecommerce.payment.notification.NotificationProducer;
import com.amer.ecommerce.payment.notification.PaymentNotificationRequest;
import com.amer.ecommerce.payment.repository.PaymentRepository;
import com.amer.ecommerce.payment.service.PaymentService;
import com.amer.ecommerce.payment.strategy.PaymentResult;
import com.amer.ecommerce.payment.strategy.PaymentStrategy;
import com.amer.ecommerce.payment.strategy.PaymentStrategyFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final PaymentStrategyFactory strategyFactory;
    private final NotificationProducer notificationProducer;

    @Transactional
    @Override
    public PaymentResponse createPayment(PaymentRequest request) {
        var payment = paymentRepository.save(
                paymentMapper.toPayment(request)
        );

        PaymentStrategy strategy =
                strategyFactory.getStrategy(payment.getPaymentMethod());

        PaymentResult result = strategy.pay(payment);

        payment.setTransactionId(result.getTransactionId());
        payment.setStatus(
                result.isSuccess() ?
                        PaymentStatus.SUCCESS
                        : PaymentStatus.FAILED
        );

        var persistedPayment = paymentRepository.save(payment);

        notificationProducer.sendNotification(
                PaymentNotificationRequest.toNotificationRequest(
                        request,
                        persistedPayment.getTransactionId()
                )
        );

        return paymentMapper.fromPayment(persistedPayment);

    }
}
