import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { StudentAlumniService } from '../studAlum.service';

@Component({
  selector: 'app-bitsfundpayment',
  templateUrl: './bitsfundpayment.component.html',
  styleUrls: ['./bitsfundpayment.component.css']
})
export class BitsfundpaymentComponent implements OnInit {

  amountEntered!:number;
  tempList:any = {
    "index":null,
    "amount":null
  }
  constructor(private service: StudentAlumniService, private router:Router) { }

  ngOnInit(): void {

  }

    
  onProceed(){
  

    this.tempList.amount = this.amountEntered;
    this.tempList.index = localStorage.getItem('contributeIndexOfEvent')

    console.log(this.tempList)

    this.service.updateContribution(this.tempList).subscribe(res=>{
        console.log(res);
        localStorage.removeItem('contributeIndexOfEvent')

        alert("Payement Successful")
        this.router.navigateByUrl('/bitsfundhome')


    })

  }

}
