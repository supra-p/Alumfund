import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FacultyReg } from '../facultyReg';
import { Registration } from '../registration';
import { StudentAlumniService } from '../studAlum.service';

@Component({
  selector: 'app-bitsfundlogin',
  templateUrl: './bitsfundlogin.component.html',
  styleUrls: ['./bitsfundlogin.component.css']
})
export class BitsfundloginComponent implements OnInit {

  submitted:boolean = false;
  loginMsg : string = '';

  facultyObj : FacultyReg = new FacultyReg();
  facultyName:string = "";
  

  constructor(private service : StudentAlumniService, private router:Router) { }

  ngOnInit(): void {

  }

  async onSubmit(){
    this.submitted = true;
    this.facultyObj.empDept = '';
    this.facultyObj.empEmail = '';
    this.facultyObj.empStatus = '';
    this.facultyObj.empName = '';



    console.log(this.facultyObj);
  

    await this.service.getFacultyName(this.facultyObj.empId).subscribe(res =>{
       this.facultyName = res;

     });

    this.service.checkFacultyLogin(this.facultyObj).subscribe(res =>{
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
        if(res[1] == '"Faculty"]')
          {this.router.navigateByUrl("/bitsfundpostlogin")
          localStorage.setItem('facultyUserId', this.facultyObj.empId)
          localStorage.setItem('facultyName', this.facultyName )
          localStorage.setItem('facultyPassword', this.facultyObj.empPassword)  
          console.log(localStorage)

      }
          
        else
          this.router.navigateByUrl("/bitsfundlogin")
      }
        


  }, error => console.log(error));
    
}

}
