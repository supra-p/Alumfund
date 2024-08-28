package com.ooad.alumfund.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Data
@Entity
public class PersonalInfo implements BioData{
	
	private static PersonalInfo personalInfo = new PersonalInfo();
	private PersonalInfo() {}
	
	public static PersonalInfo getInstance(){
	      return personalInfo;
	   }
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pers")
	@SequenceGenerator(name = "pers", sequenceName = "pers", initialValue = 1, allocationSize=1)
	private int tableId;
	
	private String id;
	private String  fullName;
	 private String  aadharNumber;
	 private String  panCardNumber;
	 private String  physicallyDisabled;
	 private String  typeOfStudent;
	 private String  typeOfEntranceExam;
	 private String  numberOfPersonsInHousehold;
	 private String  totalHouseholdIncome;
//	 private ImageIncome imageIncome;
	 
	 
	@Override
	public String putInformation() {
		return "PersonalInfo [tableId=" + tableId + ", id=" + id + ", fullName=" + fullName + ", aadharNumber="
				+ aadharNumber + ", panCardNumber=" + panCardNumber + ", physicallyDisabled=" + physicallyDisabled
				+ ", typeOfStudent=" + typeOfStudent + ", typeOfEntranceExam=" + typeOfEntranceExam
				+ ", numberOfPersonsInHousehold=" + numberOfPersonsInHousehold + ", totalHouseholdIncome="
				+ totalHouseholdIncome + "]";
		
	}

	
	
}
