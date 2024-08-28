import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { StudentAlumniService } from '../studAlum.service';

@Component({
  selector: 'app-payment-fee',
  templateUrl: './payment-fee.component.html',
  styleUrls: ['./payment-fee.component.css']
})
export class PaymentFeeComponent implements OnInit {

  amountEntered!:number;
  tempStudId:any;
  constructor(private service: StudentAlumniService, private router:Router) { }

  ngOnInit(): void {
  }

  onProceed(){  

    this.tempStudId = localStorage.getItem('paythisstudentfee')
    console.log(this.tempStudId)

    this.service.paythisstudentfee(this.tempStudId).subscribe(res=>{
      console.log(res);
    })

    localStorage.removeItem('paythisstudentfee')
    alert("Fee payment successful")
    this.router.navigateByUrl("/postloginalumni")
    // window.location.reload();



   

  }


}
