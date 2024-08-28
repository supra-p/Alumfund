import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Registration } from '../registration';
import { StudentAlumniService } from '../studAlum.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  registration: Registration = new Registration();
  constructor(private studentAlumniService : StudentAlumniService, 
    private router : Router) { }

  ngOnInit(): void {
  }

  onSubmit(){
      console.log(this.registration);
      this.studentAlumniService.register(this.registration).subscribe(res => {
          console.log(res);
          alert("Registration Successful.")
          this.router.navigateByUrl('/login')
      }, error => console.log(error));
      
      
  }

}
