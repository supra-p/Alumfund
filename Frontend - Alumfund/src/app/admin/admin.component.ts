import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  username : any;
  password: any;
  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit(){
    
    if(this.username == "123" && this.password == "123")
        this.router.navigateByUrl("/adminpostlogin")
    else
      alert("Invalid Credentials")

  }

}
