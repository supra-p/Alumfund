package com.ooad.alumfund.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ooad.alumfund.model.FeePaymentStatus;

public interface PaymentStatusRepo extends JpaRepository<FeePaymentStatus, Long>{
	FeePaymentStatus findById(String id);
}
