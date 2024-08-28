package com.ooad.alumfund.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class ContactDetails implements BioData{

	
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="contact")
	@SequenceGenerator(name = "contact", sequenceName = "contact", initialValue = 1, allocationSize=1)
	private int tableId;
	
	private String id;
	private String  mobileNumber;
	 private String  email;
	 private String  guardianMobileNumber;
	 private String  homeMobileNumber;
	 private String  presentAddress;
	 
	 
	@Override
	public String putInformation() {
		return "ContactDetails [tableId=" + tableId + ", id=" + id + ", mobileNumber=" + mobileNumber + ", email="
				+ email + ", guardianMobileNumber=" + guardianMobileNumber + ", homeMobileNumber=" + homeMobileNumber
				+ ", presentAddress=" + presentAddress + "]";
	}



	

}
