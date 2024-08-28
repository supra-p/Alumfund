package com.ooad.alumfund.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Data
public class FacultyRegistration {	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="emp")
	@SequenceGenerator(name = "emp", sequenceName = "emp", initialValue = 1, allocationSize=1)
	long id;

	String empId;
	String empName;
    String empEmail;
    String empPassword;
    String empDept;
    String empStatus;

}
