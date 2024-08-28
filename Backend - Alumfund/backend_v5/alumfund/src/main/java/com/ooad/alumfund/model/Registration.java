package com.ooad.alumfund.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Registration")
public class Registration {
	
	@Id
	@Column(name = "registrationId", nullable = false)
	private String registrationId;	
	
	@Column(name="registeredEmail", nullable = false)
	private String registeredEmail;
	
	@Column(name="password", nullable = false)
	private String password;
	
	@Column(name="year", nullable = false)
	private long year;
	
	@Column(name="userType", nullable = false)
	private String userType;
	
	@Column(name="status", nullable = false)
	private String status;

	

}
