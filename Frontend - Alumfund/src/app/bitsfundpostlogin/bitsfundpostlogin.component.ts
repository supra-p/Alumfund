

import { Component, OnInit } from '@angular/core';
import { CreateEvent } from '../createEvent';
import { StudentAlumniService } from '../studAlum.service';

@Component({
  selector: 'app-bitsfundpostlogin',
  templateUrl: './bitsfundpostlogin.component.html',
  styleUrls: ['./bitsfundpostlogin.component.css']
})
export class BitsfundpostloginComponent implements OnInit {

  value : any = '80%';
  max:any = 100;
  curr:any = 60;

  displayStyle = "none";
  registration : any = null;

  loginFacultyEmpId:any;

  createEvent: CreateEvent = new CreateEvent();

  eventsUnderFaculty: CreateEvent[] = [];
  tempRecordModal: CreateEvent = new CreateEvent();
  displayStyle1!: string;

  loginFacultyName: any;

  deleteText: any;


  constructor(private service: StudentAlumniService) { }

  ngOnInit(): void {
    this.createEvent.detailDesc;
    this.getAllEventsUnderFaculty();
    this.loginFacultyName = localStorage.getItem('facultyName')
  }
  
  
  openPopup() {
   
    this.displayStyle = "block";
  }
  
  closePopup() {
    this.displayStyle = "none";
  }
  
  onSubmit(){
    alert("Event Created Successfully")
    console.log(localStorage.getItem('facultyUserId'))
    this.createEvent.empId = localStorage.getItem('facultyUserId');
    console.log(this.createEvent);
    
    this.service.sendEventDetails(this.createEvent).subscribe(res=>{
          console.log(res);
    })
    this.closeEventPopup();
    window.location.reload();

  }
  
  getAllEventsUnderFaculty(){
      this.loginFacultyEmpId = localStorage.getItem('facultyUserId')
      this.service.getAllEventsUnderFaculty(this.loginFacultyEmpId).subscribe(res=>{
              this.eventsUnderFaculty = res;
              console.log(this.eventsUnderFaculty)
        })
      
    }

    openEventPopup(event:any){
      this.displayStyle1 = "block";
        this.tempRecordModal = event
    }

    closeEventPopup() {
      this.displayStyle1 = "none";
    }

    deleteEvent(event:any){
        this.service.deleteEvent(event.id).subscribe(res=>{
          this.deleteText = res;
        })
        alert("Deleted Successfully");
        window.location.reload();

    }

}