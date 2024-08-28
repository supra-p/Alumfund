package com.ooad.alumfund.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Data
public class CreateEvent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="event")
	@SequenceGenerator(name = "event", sequenceName = "event", initialValue = 1, allocationSize=1)
	private long id;
	
	private String empId;
	private String empName;
	
	private String title;
	private String typeOfEvent;
	private String teamSize;
	private long targetAmount;
	private Date deadline;
	private String briefDesc;
	private String detailDesc;
	private long amountReceived;
	
	

}
