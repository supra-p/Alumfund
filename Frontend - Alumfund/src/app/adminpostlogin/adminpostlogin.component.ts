import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Admin } from '../admin';
import { StudentAlumniService } from '../studAlum.service';

@Component({
  selector: 'app-adminpostlogin',
  templateUrl: './adminpostlogin.component.html',
  styleUrls: ['./adminpostlogin.component.css']
})
export class AdminpostloginComponent implements OnInit {

  public adminTableRecords!: Array<Admin>; 

  constructor(private studAlum : StudentAlumniService,
              private router : Router) { }

  ngOnInit(): void {
    this.getAdminRecords();
  }

  getAdminRecords(){
    this.studAlum.adminRecords().subscribe(res=>{
        console.log(res)
        this.adminTableRecords = res;
        console.log(this.adminTableRecords);
    })
  }

  rejectAction(record: Admin){
    console.log("Pressed")
    this.studAlum.deleteAdminRecord(record.registrationId , record.userType).subscribe(res=>{
        console.log("res", res)
        window.location.reload();
    });
  }

  approveAction(record: Admin){
    console.log(record.registrationId);
    this.studAlum.approveAdminRecord(record.registrationId, record.userType).subscribe(res=>{
        console.log("res", res)
        window.location.reload();
    });
  }


  logout(){
    this.router.navigateByUrl("/")

  }

}
