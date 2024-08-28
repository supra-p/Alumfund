import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { CreateEvent } from '../createEvent';
import { StudentAlumniService } from '../studAlum.service';

@Component({
  selector: 'app-bitsfundhome',
  templateUrl: './bitsfundhome.component.html',
  styleUrls: ['./bitsfundhome.component.css']
})
export class BitsfundhomeComponent implements OnInit {

  value : any = '80%';
  max:any = 100;
  curr:any = 60;
  daas!:Date;

  diffDaysArr:any[] = [];

  displayStyle = "none";
  createEvent: CreateEvent = new CreateEvent();

  contributeIndexOfEvent: any;

  progressBarValues: any = [];

  tempRecordModal: CreateEvent = new CreateEvent();

  facultyNames : any = ["Professor 1", "Professor 2","Professor 3","Professor 4","Professor 5","Professor 6",]


  allEvents : CreateEvent[] = [];

  constructor(private service : StudentAlumniService, private router : Router) { }

  ngOnInit(): void {
    this.getAllRecords();
  }

  getAllRecords(){
      this.service.getAllEvents().subscribe(res=>{
          this.allEvents = res;

          // console.log(typeof new Date())
          // console.log(typeof new Date(this.allEvents[1].deadline));

          // console.log(this.allEvents[1].title)
          // console.log( new Date())          
          // console.log(new Date(this.allEvents[1].deadline));

          for (let index = 0; index < this.allEvents.length; index++) {
            
            var diff = Math.abs(new Date().getTime() - new Date(this.allEvents[index].deadline).getTime());
            var diffDays = Math.ceil(diff / (1000 * 3600 * 24)); 
            this.diffDaysArr.push(diffDays)
          }


          console.log(this.diffDaysArr)
          
  
          // return Math.round(Math.abs(Number(endDate) - Number(startDate)) / msInDay);

          for(let i=0; i<this.allEvents.length;i++){
              this.progressBarValues[i] = ((this.allEvents[i].amountReceived / this.allEvents[i].targetAmount)*100).toString()+"%";            
          }

          console.log(this.progressBarValues)
      })
  }


  openPopup(event:any) {
    this.tempRecordModal=event
    this.displayStyle = "block";
  }
  
  closePopup() {
    this.displayStyle = "none";
  }



 contributeFunc(i:any){
  this.contributeIndexOfEvent = i;
  localStorage.setItem('contributeIndexOfEvent', this.contributeIndexOfEvent+1)
  this.router.navigateByUrl('/bitsfundpayment')

}
  
}
