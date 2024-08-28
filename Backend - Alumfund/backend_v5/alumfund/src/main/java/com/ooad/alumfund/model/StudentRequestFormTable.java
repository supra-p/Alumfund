package com.ooad.alumfund.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class StudentRequestFormTable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String userId;
	private long incomeImageId;
	private int personalInfoId;
	private int academicInfoId;
	private int contactDetailsId;
}
