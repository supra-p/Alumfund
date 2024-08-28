import { Component, OnInit } from '@angular/core';
import { RequestForm } from '../requestForm';
import { StudentAlumniService } from '../studAlum.service';

import { MdbModalRef , MdbModalService } from 'mdb-angular-ui-kit/modal';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-postloginalumni',
  templateUrl: './postloginalumni.component.html',
  styleUrls: ['./postloginalumni.component.css']
})
export class PostloginalumniComponent implements OnInit {


  public requestFormData!: any; 
  constructor(private studentAlumniService:StudentAlumniService, private route:Router){}

  ngOnInit(): void {
    this.studentAlumniService.getAllRecords().subscribe(res =>{
      console.log("records" , res);
      this.requestFormData = res;
    })
  }

  displayStyle = "none";
  tempRecordModal : any = null;

  openPopup(record:any) {
    this.tempRecordModal = record;
    console.log(record )
    this.displayStyle = "block";
  }

  closePopup() {
    this.displayStyle = "none";
  }

  payFee(record:any){
      console.log("rec", record)
      localStorage.setItem('paythisstudentfee', record.id)
      this.route.navigateByUrl("/paymentFee")
  }


}
