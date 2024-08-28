import { Component, OnInit } from '@angular/core';
import { Registration } from '../registration';

import { StudentAlumniService } from '../studAlum.service';

import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  registration: Registration = new Registration();
  submitted:boolean = false;
  loginMsg : string = '';

  constructor(private studentAlumniService : StudentAlumniService, 
              private router : Router) { }

  ngOnInit(): void {
  this.submitted = false;

  }

  onSubmit(){
    this.submitted = true;
    this.registration.registeredEmail = '';
    this.registration.status = '';
    this.registration.userType='';
    this.registration.year = 0;
    console.log(this.registration)
  
    this.studentAlumniService.checkLogin(this.registration).subscribe(res =>{
          // alert(res)
          res = res.split(',')
          
          console.log(res[0])
          console.log(res[1])


          if(res[0] == '["Registration Pending with Admin"')
            this.loginMsg = "Registration Pending with Admin"
          else if(res[0] == '["Rejected"')
            this.loginMsg = "Registration Rejected. Contact Administrator."
          else if (res[0] == '["Invalid password"')
            this.loginMsg = "Invalid password"
          else if (res[0] == '["User not found!"')
            this.loginMsg = "User not found!"
          else if (res[0] == '["Successful login"')
          {
            if(res[1] == '"Student"]')
              {this.router.navigateByUrl("/postlogin")
              localStorage.setItem('localUserId', this.registration.registrationId)
              localStorage.setItem('localUserPassword', this.registration.password)
              console.log(localStorage)

          }
              
            else
              this.router.navigateByUrl("/postloginalumni")
          }
            


      }, error => console.log(error));
    
  }

}
