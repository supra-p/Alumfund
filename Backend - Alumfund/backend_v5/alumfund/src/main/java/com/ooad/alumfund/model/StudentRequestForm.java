package com.ooad.alumfund.model;

import lombok.Data;

//@Entity
//@Table(name = "StudentRequestForm")
@Data
public class StudentRequestForm {
		
//		@Id
		private String id;
		

		private PersonalInfo personalInfo;
		private ContactDetails contactDetails;
		private AcademicInfo academicInfo;
		
		//Facade
		public String getPersonalInfoString() {
			return personalInfo.putInformation();
		}
		
		public String getContactInfoString() {
			return contactDetails.putInformation();
		}
		
		public String getAcademicInfoString() {
			return academicInfo.putInformation();
		}
	
		
}



//@Embedded
//@AttributeOverrides({
//	  @AttributeOverride( name = "fullName", column = @Column(name = "contact_first_name")),
////	  @AttributeOverride( name = "lastName", column = @Column(name = "contact_last_name")),
////	  @AttributeOverride( name = "phone", column = @Column(name = "contact_phone"))
//	})
