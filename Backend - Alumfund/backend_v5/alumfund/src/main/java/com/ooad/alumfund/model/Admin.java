package com.ooad.alumfund.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "Admin")
public class Admin {
	
	@Id
	@Column(name = "registrationId", nullable = false) 
	private String registrationId;	
	
	@Column(name="registeredEmail", nullable = false)
	private String registeredEmail;

	@Column(name="userType", nullable = false)
	private String userType;
	
	@Column(name="status", nullable = false)
	private String status;
	
	@Column(name = "dateOfRegistration")
	private LocalDate dateOfRegistration;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "registration_id")
//	private Registration userReg;
	
	

	

	

	
	
	
	

	
	
	
	
	
	
}