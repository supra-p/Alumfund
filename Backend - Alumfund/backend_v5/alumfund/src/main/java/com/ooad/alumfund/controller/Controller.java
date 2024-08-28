package com.ooad.alumfund.controller;

import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.annotation.PostConstruct;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.RepositoryQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ooad.alumfund.exception.ResourceNotFoundException;
import com.ooad.alumfund.model.AcademicInfo;
import com.ooad.alumfund.model.Admin;
import com.ooad.alumfund.model.ContactDetails;
import com.ooad.alumfund.model.Contribution;
import com.ooad.alumfund.model.CreateEvent;
import com.ooad.alumfund.model.FacultyRegistration;
import com.ooad.alumfund.model.FeePaymentStatus;
import com.ooad.alumfund.model.ImageIncome;
import com.ooad.alumfund.model.PersonalInfo;
import com.ooad.alumfund.model.Registration;
import com.ooad.alumfund.model.StudentRequestForm;
import com.ooad.alumfund.model.StudentRequestFormTable;
import com.ooad.alumfund.model.FeePaymentStatus;
import com.ooad.alumfund.repository.AcademicInfoRepo;
import com.ooad.alumfund.repository.AdminRepo;
import com.ooad.alumfund.repository.ContactDetailsRepo;
import com.ooad.alumfund.repository.CreateEventRepo;
import com.ooad.alumfund.repository.ImageIncomeRepo;
import com.ooad.alumfund.repository.PaymentStatusRepo;
import com.ooad.alumfund.repository.PersonalInfoRepo;
import com.ooad.alumfund.repository.RegistrationRepo;
//import com.ooad.alumfund.repository.StudentRequestFormRepo;
import com.ooad.alumfund.repository.StudentRequestFormTableRepo;
import com.ooad.alumfund.repository.FacultyRegRepo;

import java.time.temporal.ChronoUnit;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@ResponseBody
@RequestMapping("/fund")
public class Controller {

	@Autowired
	private RegistrationRepo registrationRepo;
	
	@Autowired
	private AdminRepo adminRepo;
	
	@Autowired
	private StudentRequestFormTableRepo studentRequestFormTableRepo;
	
	@Autowired
	private PersonalInfoRepo personalInfoRepo;
	
	@Autowired
	private ContactDetailsRepo contactDetailsRepo;
	
	@Autowired
	private AcademicInfoRepo academicInfoRepo;
	
	@Autowired
	private ImageIncomeRepo imageIncomeRepo;
	
	@Autowired
	private PaymentStatusRepo paymentStatusRepo;
	
	@Autowired
	private FacultyRegRepo facultyRegRepo;
	
	@Autowired
	private CreateEventRepo createEventRepo;
	
	@GetMapping("/disp")
	public List<Registration> getAllRegisteredRecords(){
		return registrationRepo.findAll();
	}
	
	@GetMapping("/dispFaculty")
	public List<FacultyRegistration> getAllFacultyRegisteredRecords(){
		return facultyRegRepo.findAll();
	}
	
	@GetMapping("/adminRecords")
	public List<Admin> getAllRecordsAdmin(){
		
//		adminVerification();
		return adminRepo.findAll();
	}

		
	@PostMapping("/reg")
	public Registration createRegistration (@RequestBody 
			Registration registration) {
		
//		adminVerification();
		Admin admin = new Admin();
		LocalDate todaysdate = LocalDate.now();
		registration.setStatus("Pending");			
		
		admin.setRegisteredEmail(registration.getRegisteredEmail());
		admin.setRegistrationId(registration.getRegistrationId());
		admin.setUserType(registration.getUserType());
		admin.setStatus(registration.getStatus());
		
		admin.setDateOfRegistration(todaysdate);
		
		adminRepo.save(admin);
		
		return registrationRepo.save(registration);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<String[]> loginVerification (@RequestBody Registration loginObject) {
		System.out.println(loginObject);
System.out.println("HIIii");
		
		boolean res = false;
		String message = null;
		List<Registration> getAllRegistrations = getAllRegisteredRecords();
		Registration temp = null ;
		
		for(Registration x : getAllRegistrations) {
			
			if(!(x.getRegistrationId().equals(loginObject.getRegistrationId()))) {
				message = "User not found!";
				res= false;
			}
			
			else
			{
				if((loginObject.getPassword().equals(x.getPassword())) && 
						x.getStatus().equals("Pending")){
					message = "Registration Pending with Admin";
					res = false;
				}
				else if((loginObject.getPassword().equals(x.getPassword())) && 
						x.getStatus().equals("Rejected")){
					message = "Rejected";
					res = false;
				}
				
				
				
				else if(!(loginObject.getPassword().equals(x.getPassword())))
				{
					message = "Invalid password";
					res = false;
				}				
				else {
					message = "Successful login";
					res = true;
					
				}
				temp = x;
				break;
			}
						
			 
		}
		
		if(res == false)
				System.out.println("Invalid user");
		
		System.out.println(message);
		if(temp == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		String[] msgArray = {message, temp.getUserType()};
		return new ResponseEntity<>(msgArray, HttpStatus.OK);
	}	
	
	
	
	
	@PostMapping("/facultyLogin")
	public ResponseEntity<String[]> loginVerification (@RequestBody FacultyRegistration facultyRegistration) {
		System.out.println(facultyRegistration);
		System.out.println("HIIii");
		
		boolean res = false;
		String message = null;
		List<FacultyRegistration> getAllFacultyRegistrations = getAllFacultyRegisteredRecords();
		FacultyRegistration temp = null ;
		
		for(FacultyRegistration x : getAllFacultyRegistrations) {
			
			if(!(x.getEmpId().equals(facultyRegistration.getEmpId()))) {
				message = "User not found!";
				res= false;
			}
			
			else
			{
				if((facultyRegistration.getEmpPassword().equals(x.getEmpPassword())) && 
						x.getEmpStatus().equals("Pending")){
					message = "Registration Pending with Admin";
					res = false;
				}
				else if((facultyRegistration.getEmpPassword().equals(x.getEmpPassword())) && 
						x.getEmpStatus().equals("Rejected")){
					message = "Rejected";
					res = false;
				}
				
				
				
				else if(!(facultyRegistration.getEmpPassword().equals(x.getEmpPassword())))
				{
					message = "Invalid password";
					res = false;
				}				
				else {
					message = "Successful login";
					res = true;
					
				}
				temp = x;
				break;
			}
						
			 
		}
		
		if(res == false)
				System.out.println("Invalid user");
		
		System.out.println(message);
		if(temp == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		String[] msgArray = {message, "Faculty"};
		return new ResponseEntity<>(msgArray, HttpStatus.OK);
	}	
	
	
	
//	@PostConstruct
//	public void adminVerification() {
//		List<Registration> getAllRegistrations = registrationRepo.findAll();
//		Admin admin = new Admin(); 
//		
//		System.out.println(getAllRegistrations);
//		
//		for(Registration x : getAllRegistrations) {
//			System.out.println(x.getStatus());
//			if(x.getStatus().equals("Pending")) {
//				admin.setRegisteredEmail(x.getRegisteredEmail());
//				admin.setRegistrationId(x.getRegistrationId());
//				admin.setUserType(x.getUserType());
//				admin.setStatus(x.getStatus());
//				
//				
//				adminRepo.save(admin);
//			}
//		}
//	}

	@DeleteMapping("/deleteAdminRecord/{id}/{userType}")
	public String deleteAdminRecord(@PathVariable(value = "id") String registrationId,
			@PathVariable(value = "userType") String userType)  throws ResourceNotFoundException {
        
		if(userType.equals("Faculty"))
		{
			FacultyRegistration facRegObj = facultyRegRepo.findByEmpId(registrationId)
				       .orElseThrow(() -> new ResourceNotFoundException("Record not found for this id: Faculty " ));
			facRegObj.setEmpStatus("Rejected");
		    facultyRegRepo.save(facRegObj);
		
		}
		else {
			Registration regObj = registrationRepo.findById(registrationId)
					.orElseThrow(() -> new ResourceNotFoundException("Record not found for this id: Registration " ));
			regObj.setStatus("Rejected");
			registrationRepo.save(regObj);
		}		
        
        
        Admin adminObj = adminRepo.findById(registrationId)
        .orElseThrow(() -> new ResourceNotFoundException("Record not found for this id: Admin " ));
        
        adminRepo.delete(adminObj);
        
        return "Deleted Successfully";
        

	}
	
	
	@DeleteMapping("/approveAdminRecord/{id}/{userType}")
	public String approveAdminRecord(@PathVariable(value = "id") String registrationId,
			@PathVariable(value = "userType") String userType)  throws ResourceNotFoundException {
        
		if(userType.equals("Faculty"))
		{
			FacultyRegistration facRegObj = facultyRegRepo.findByEmpId(registrationId)
				       .orElseThrow(() -> new ResourceNotFoundException("Record not found for this id: Faculty " ));
			facRegObj.setEmpStatus("Approved");
		    facultyRegRepo.save(facRegObj);
		
		}
		else {
			Registration regObj = registrationRepo.findById(registrationId)
					.orElseThrow(() -> new ResourceNotFoundException("Record not found for this id: Registration " ));
			regObj.setStatus("Approved");
			registrationRepo.save(regObj);
		}
		
        Admin adminObj = adminRepo.findById(registrationId)
        .orElseThrow(() -> new ResourceNotFoundException("Record not found for this id " ));
        
        adminRepo.delete(adminObj);
        
        return "Approved Successfully";
        

	}
	
	StudentRequestFormTable studentRequestFormTable; 
	String requestFormId;
	int persTableId;
	int conTableId;
	int acaTableId;
	
	@PostMapping("/fundRequestForm")
	public StudentRequestFormTable fundRequestForm(@RequestBody StudentRequestForm requestForm) throws IOException {
		
		
		
		System.out.println(requestForm.getPersonalInfo().getId());
		
		//Singleton Pattern
		PersonalInfo personalInfo =  PersonalInfo.getInstance();
		ContactDetails contactDetails = new ContactDetails();
		AcademicInfo academicInfo = new AcademicInfo();
		FeePaymentStatus feePaymentStatus = new FeePaymentStatus();
		
		requestFormId = requestForm.getId();
		
		feePaymentStatus.setId(requestForm.getId());
		feePaymentStatus.setStatus("Pending");
		
		
		personalInfo.setId(requestForm.getId());
		personalInfo.setAadharNumber(requestForm.getPersonalInfo().getAadharNumber());
		personalInfo.setFullName(requestForm.getPersonalInfo().getFullName());
		personalInfo.setPanCardNumber(requestForm.getPersonalInfo().getPanCardNumber());
		personalInfo.setPhysicallyDisabled(requestForm.getPersonalInfo().getPhysicallyDisabled());
		personalInfo.setNumberOfPersonsInHousehold(requestForm.getPersonalInfo().getNumberOfPersonsInHousehold());
		personalInfo.setTotalHouseholdIncome(requestForm.getPersonalInfo().getTotalHouseholdIncome());
		personalInfo.setTypeOfEntranceExam(requestForm.getPersonalInfo().getTypeOfEntranceExam());
		personalInfo.setTypeOfStudent(requestForm.getPersonalInfo().getTypeOfStudent());
		
		contactDetails.setId(requestForm.getId());
		contactDetails.setMobileNumber(requestForm.getContactDetails().getMobileNumber());
		contactDetails.setEmail(requestForm.getContactDetails().getEmail());
		contactDetails.setGuardianMobileNumber(requestForm.getContactDetails().getGuardianMobileNumber());
		contactDetails.setHomeMobileNumber(requestForm.getContactDetails().getHomeMobileNumber());
		contactDetails.setPresentAddress(requestForm.getContactDetails().getPresentAddress());
		
		academicInfo.setId(requestForm.getId());
		academicInfo.setTenthPerc(requestForm.getAcademicInfo().getTenthPerc());
		academicInfo.setTwelfthPerc(requestForm.getAcademicInfo().getTwelfthPerc());
		academicInfo.setTypeOfDegree(requestForm.getAcademicInfo().getTypeOfDegree());
		academicInfo.setHighestEducationText(requestForm.getAcademicInfo().getHighestEducationText());
		academicInfo.setHighestEducationValue(requestForm.getAcademicInfo().getHighestEducationValue());
		academicInfo.setTypeOfEntranceExam(requestForm.getAcademicInfo().getTypeOfEntranceExam());
		academicInfo.setMarksObtainedInExam(requestForm.getAcademicInfo().getMarksObtainedInExam());
		academicInfo.setCourse(requestForm.getAcademicInfo().getCourse());
		academicInfo.setCourseDuration(requestForm.getAcademicInfo().getCourseDuration());
		academicInfo.setSop(requestForm.getAcademicInfo().getSop());
		academicInfo.setAcademicAccomplishment(requestForm.getAcademicInfo().getAcademicAccomplishment());
		academicInfo.setFutureGoals(requestForm.getAcademicInfo().getFutureGoals());
		
		
		personalInfoRepo.save(personalInfo);
		contactDetailsRepo.save(contactDetails);
		academicInfoRepo.save(academicInfo);
		paymentStatusRepo.save(feePaymentStatus);
		
		persTableId = personalInfo.getTableId();
		conTableId = contactDetails.getTableId();
		acaTableId = academicInfo.getTableId();
		
		
		studentRequestFormTable.setUserId(requestFormId);
		studentRequestFormTable.setPersonalInfoId(persTableId);
		studentRequestFormTable.setAcademicInfoId(conTableId);
		studentRequestFormTable.setContactDetailsId(acaTableId);
		
		
		
		return studentRequestFormTableRepo.save(studentRequestFormTable);
	}
		 
	 @PostMapping("/sendImageToBackend")
		public BodyBuilder uplaodImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
		 
		 	studentRequestFormTable = new StudentRequestFormTable();
		 	
		 	
		 
			System.out.println("Original Image Byte Size - " + file.getBytes().length);
			ImageIncome img = new ImageIncome(file.getOriginalFilename(),
					compressBytes(file.getBytes()));
			
			imageIncomeRepo.save(img);
			System.out.println("ID:" + img.getId());
			
			studentRequestFormTable.setIncomeImageId(img.getId());
			
			return ResponseEntity.status(HttpStatus.OK);
		}
	 
	 
	 public static byte[] compressBytes(byte[] data) {
			Deflater deflater = new Deflater();
			deflater.setInput(data);
			deflater.finish();

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
			byte[] buffer = new byte[1024];
			while (!deflater.finished()) {
				int count = deflater.deflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			try {
				outputStream.close();
			} catch (IOException e) {
			}
			System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

			return outputStream.toByteArray();
		}
	 	 	 
	 public static byte[] decompressBytes(byte[] data) {
		 
		         Inflater inflater = new Inflater();
		 
		         inflater.setInput(data);
		 
		         ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		
		         byte[] buffer = new byte[1024];
		 
		         try {
		
		             while (!inflater.finished()) {
	
		                 int count = inflater.inflate(buffer);
		
		                 outputStream.write(buffer, 0, count);
		
		             }
	
		             outputStream.close();
	
		         } catch (IOException ioe) {
		
		         } catch (DataFormatException e) {
	
		         }
		 
		         return outputStream.toByteArray();
		 
		     }
	 
	 
	 @GetMapping(path = { "/get/{imageId}" })
		public ImageIncome getImage(@PathVariable("imageId") long imageId) throws IOException {

		 System.out.println(imageId);
			final Optional<ImageIncome> retrievedImage = imageIncomeRepo.findById(imageId);
			System.out.println(retrievedImage.get().getImageName());
			
			ImageIncome img = new ImageIncome( retrievedImage.get().getImageName(),
					decompressBytes(retrievedImage.get().getPicByte()));
			return img;
		}
	 
	 @GetMapping("/getAllRecords")
	 public List<StudentRequestForm> getAllRecords() {
		 List<StudentRequestFormTable> studentDataArrTable = new ArrayList<StudentRequestFormTable>();
		 studentDataArrTable = studentRequestFormTableRepo.findAll();
		 
		 List<StudentRequestForm> studentDataArr = new ArrayList<StudentRequestForm>();
		 
		 StudentRequestForm student;
		 PersonalInfo pinfo;
		 ContactDetails conc;
		 AcademicInfo acad;
		 
		 for(StudentRequestFormTable obj : studentDataArrTable) {
			 student = new StudentRequestForm();
			 
			 student.setId(obj.getUserId());
			 
			 pinfo = personalInfoRepo.findById(obj.getPersonalInfoId()).orElseThrow(IllegalArgumentException::new);
			 student.setPersonalInfo(pinfo);
			 
			 conc = contactDetailsRepo.findById(obj.getContactDetailsId()).orElseThrow(IllegalArgumentException::new);
			 student.setContactDetails(conc);
			 
			 acad = academicInfoRepo.findById(obj.getAcademicInfoId()).orElseThrow(IllegalArgumentException::new);
			 student.setAcademicInfo(acad);
			 
			 studentDataArr.add(student);
		 }
		 
		 return studentDataArr;
		 
	 }
	 

	 @GetMapping("/getAllImages")
	 public List<ImageIncome> getAllImages() {
		 List<StudentRequestFormTable> studentDataArrTable = new ArrayList<StudentRequestFormTable>();
		 studentDataArrTable = studentRequestFormTableRepo.findAll();
		 List<ImageIncome> imageIncomeArr = new ArrayList<ImageIncome>();
		 
		 for(StudentRequestFormTable obj : studentDataArrTable) {
			 ImageIncome image = new ImageIncome();
			 System.out.println(obj.getIncomeImageId());
			 image = imageIncomeRepo.findById(obj.getIncomeImageId()).orElseThrow(IllegalArgumentException::new);
			 imageIncomeArr.add(image);
		 }
		 
		 return imageIncomeArr;
	 }
	 
	 @GetMapping("/getFeeStatus/{userId}")
	 public String getFeeStatus(@PathVariable("userId") String userId) throws IOException {
		 
		 FeePaymentStatus feeTemp= paymentStatusRepo.findById(userId);
		 System.out.println(feeTemp.getStatus());
		 
		 return feeTemp.getStatus();
	 }
	 
	 
	 @PostMapping("/sendFacultyReg")
		public FacultyRegistration sendFacultyReg (@RequestBody 
				FacultyRegistration facultyRegistration) {
			
//			adminVerification();
			Admin admin = new Admin();
			LocalDate todaysdate = LocalDate.now();
			facultyRegistration.setEmpStatus("Pending");			
			
			admin.setRegisteredEmail(facultyRegistration.getEmpEmail());
			admin.setRegistrationId(facultyRegistration.getEmpId());
			admin.setUserType("Faculty");
			admin.setStatus(facultyRegistration.getEmpStatus());
			
			admin.setDateOfRegistration(todaysdate);
			
			adminRepo.save(admin);
			
			return facultyRegRepo.save(facultyRegistration);
			
		}
	 
	 
	 
	 @PostMapping("/createevent")
		public CreateEvent sendEventDetails (@RequestBody 
				CreateEvent createEvent) {
		 
		 LocalDate date2 = LocalDate.now();
		 createEvent.setAmountReceived(0);
		 FacultyRegistration facultyRegistration1= facultyRegRepo.findByEmpId(createEvent.getEmpId()).orElseThrow(IllegalArgumentException::new);
		 String facName = facultyRegistration1.getEmpName();
		 createEvent.setEmpName(facName);
//		 date_difference 
		 System.out.println(date2.until(createEvent.getDeadline().toLocalDate(), ChronoUnit.DAYS));
			return createEventRepo.save(createEvent);
			
		}
	 
	 @GetMapping("/getAllEventsUnderFaculty/{loginFacultyEmpId}")
	 public List<CreateEvent> getAllEventsUnderFaculty(@PathVariable("loginFacultyEmpId") String loginFacultyEmpId ){
		 
		 List<CreateEvent> listOfEventsUnderFaculty = new ArrayList<CreateEvent>();
		 List<CreateEvent> listOfAllEvents = new ArrayList<CreateEvent>();
		 
		 listOfAllEvents = createEventRepo.findAll();
		 
		 for( CreateEvent event : listOfAllEvents) {
//			 CreateEvent temp = new CreateEvent();
			 if(event.getEmpId().equals(loginFacultyEmpId))
			 {			 
				 listOfEventsUnderFaculty.add(event);
			 }
		 }
		 
		 return listOfEventsUnderFaculty;
	 }
	 
	 @GetMapping("/getAllEvents")
		public List<CreateEvent> getAllEvents () {				
			return createEventRepo.findAll();			
		}
	 
	 
	 @PostMapping("/updateContribution")
		public CreateEvent updateContribution (@RequestBody 
				Contribution contribution ) {
		 CreateEvent createEvent = new CreateEvent();
		 createEvent = createEventRepo.findById(contribution.getIndex()).orElseThrow(IllegalArgumentException::new);
		 long newAmount = createEvent.getAmountReceived() + contribution.getAmount();
		 createEvent.setAmountReceived(newAmount);
		 
		 System.out.println(newAmount);
		 
		 
		return createEventRepo.save(createEvent);
			
		}
	 
	@GetMapping("/getFacultyName/{empId}")
	public String getFacultyName(@PathVariable("empId") String empId) {
		
//		facultyRegRepo.findByEmpId(empId);
		
		return facultyRegRepo.findByEmpId(empId).get().getEmpName();
	}
	
	
	
	@DeleteMapping("/deleteEvent/{eventId}")
	public String deleteEvent(@PathVariable(value = "eventId") long eventId) throws ResourceNotFoundException {
		
		CreateEvent deleteEvent = new CreateEvent();
		deleteEvent = createEventRepo.findById(eventId).orElseThrow(IllegalArgumentException::new);
		createEventRepo.delete(deleteEvent);
		
		
		return "Event Successfully Deleted";
	}
	
	
	@GetMapping("/paythisstudentfee/{studId}")
	public String paythisstudentfee(@PathVariable("studId") String studId) {
		
		FeePaymentStatus feePaymentStatus = paymentStatusRepo.findById(studId);
		feePaymentStatus.setStatus("Paid");
		paymentStatusRepo.save(feePaymentStatus);
		
		StudentRequestFormTable studentRequestFormTable = studentRequestFormTableRepo.findByUserId(studId);
		studentRequestFormTableRepo.delete(studentRequestFormTable);
		
		
		return "Fee Paid Successfully";
	}
        

	 
	
	
	 
	
}
