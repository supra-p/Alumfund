package com.ooad.alumfund.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Data
public class FeePaymentStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="paymentStat")
	@SequenceGenerator(name = "paymentStat", sequenceName = "paymentStat", initialValue = 1, allocationSize=1)
	private Long tableId;
	
	private String id;
	private String status;
}
