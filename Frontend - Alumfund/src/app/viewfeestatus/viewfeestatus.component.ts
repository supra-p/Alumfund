import { Component, OnInit } from '@angular/core';
import { StudentAlumniService } from '../studAlum.service';

@Component({
  selector: 'app-viewfeestatus',
  templateUrl: './viewfeestatus.component.html',
  styleUrls: ['./viewfeestatus.component.css']
})
export class ViewfeestatusComponent implements OnInit {

  message: any= '';
  public feePaid:any = 0;
  constructor(private service : StudentAlumniService) { }

  ngOnInit(): void {

    this.service.getFeeStatus(localStorage.getItem('localUserId')).subscribe(res=>{
      console.log(res);

      if(res == "Pending")
        {this.message='Your request is being processed, please come back later!';
        this.feePaid = 0;}
      else
        {this.message='Your transaction has been processed successfully, and your fee has been paid. Your admission is now confirmed!';
        this.feePaid = 1;}

    })
  }

}
