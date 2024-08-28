package com.ooad.alumfund.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ooad.alumfund.exception.ResourceNotFoundException;
import com.ooad.alumfund.model.Admin;
import com.ooad.alumfund.model.Contribution;
import com.ooad.alumfund.model.CreateEvent;
import com.ooad.alumfund.model.FacultyRegistration;
import com.ooad.alumfund.repository.AdminRepo;
import com.ooad.alumfund.repository.CreateEventRepo;
import com.ooad.alumfund.repository.FacultyRegRepo;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@ResponseBody
@RequestMapping("/bitsfund")
public class BitsFundController {
	
	@Autowired
	private FacultyRegRepo facultyRegRepo;
	
	@Autowired
	private CreateEventRepo createEventRepo;
	
	@Autowired
	private AdminRepo adminRepo;
	
	@GetMapping("/dispFaculty")
	public List<FacultyRegistration> getAllFacultyRegisteredRecords(){
		return facultyRegRepo.findAll();
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
	
	
	
	@PostMapping("/sendFacultyReg")
	public FacultyRegistration sendFacultyReg (@RequestBody 
			FacultyRegistration facultyRegistration) {
		
//		adminVerification();
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
//	 date_difference 
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
	
	
	

}
