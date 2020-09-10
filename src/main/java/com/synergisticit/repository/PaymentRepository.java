package com.synergisticit.repository;

import java.util.List;

import com.synergisticit.domain.Payment;

public interface PaymentRepository {

	List<Payment> findAll();
	Payment save(Payment payment);
	Payment update(Payment payment);
	void delete(int payment_id, int order_id);
}
