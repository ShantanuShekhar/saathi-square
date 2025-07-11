package com.saathisquare.societyservice.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.saathisquare.societyservice.dto.request.PaymentRequest;
import com.saathisquare.societyservice.dto.response.PaymentResponse;
import com.saathisquare.societyservice.enums.PaymentStatus;
import com.saathisquare.societyservice.model.Flat;
import com.saathisquare.societyservice.model.FlatPayment;
import com.saathisquare.societyservice.model.PaymentPlan;
import com.saathisquare.societyservice.repository.FlatPaymentRepository;
import com.saathisquare.societyservice.repository.FlatRepository;
import com.saathisquare.societyservice.repository.PaymentPlanRepository;
import com.saathisquare.societyservice.service.PaymentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PayementServiceImpl implements PaymentService {

	private final FlatRepository flatRepository;
	private final PaymentPlanRepository planRepository;
	private final FlatPaymentRepository flatPaymentRepository;

	/**
	 * Generate and store payment record based on flat's area or fixed price.
	 */
	public PaymentResponse makePayment(PaymentRequest request) {
		Flat flat = flatRepository.findById(request.flatId()).orElseThrow();
		PaymentPlan plan = planRepository.findById(request.paymentPlanId()).orElseThrow();

		double amount = plan.getFixedAmount() != null ? plan.getFixedAmount()
				: plan.getRatePerSqft() * flat.getAreaSqft();

		FlatPayment payment = FlatPayment.builder().flat(flat).paymentPlan(plan).amount(amount)
				.paymentDate(LocalDateTime.now()).paymentStatus(PaymentStatus.PAID).build();
		flatPaymentRepository.save(payment);
		return new PaymentResponse(payment.getPaymentId(), amount, payment.getPaymentDate(),
				payment.getPaymentStatus());
	}

}
