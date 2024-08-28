package com.ooad.alumfund.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Data
public class AcademicInfo implements BioData {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="acad")
	@SequenceGenerator(name = "acad", sequenceName = "acad", initialValue = 1, allocationSize=1)
	private int tableId;
	
	 private String id;
	 private String  tenthPerc;
	 private String  twelfthPerc;
	 private String  typeOfDegree;
	 private String  highestEducationText;
	 private String  highestEducationValue;
	 private String  typeOfEntranceExam;
	 private String  marksObtainedInExam;
	 private String  course;
	 private int  courseDuration;
	 @Column(columnDefinition="LONGTEXT")
	 private String sop;
	 private String academicAccomplishment;
	 private String futureGoals;
	 
	 
	@Override
	public String putInformation() {
		return "AcademicInfo [tableId=" + tableId + ", id=" + id + ", tenthPerc=" + tenthPerc + ", twelfthPerc="
				+ twelfthPerc + ", typeOfDegree=" + typeOfDegree + ", highestEducationText=" + highestEducationText
				+ ", highestEducationValue=" + highestEducationValue + ", typeOfEntranceExam=" + typeOfEntranceExam
				+ ", marksObtainedInExam=" + marksObtainedInExam + ", course=" + course + ", courseDuration="
				+ courseDuration + ", sop=" + sop + ", academicAccomplishment=" + academicAccomplishment
				+ ", futureGoals=" + futureGoals + "]";
	}
	 
	  
}
