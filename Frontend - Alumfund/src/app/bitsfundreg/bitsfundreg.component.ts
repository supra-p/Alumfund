import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { FacultyReg } from '../facultyReg';
import { Registration } from '../registration';
import { StudentAlumniService } from '../studAlum.service';

@Component({
  selector: 'app-bitsfundreg',
  templateUrl: './bitsfundreg.component.html',
  styleUrls: ['./bitsfundreg.component.css']
})
export class BitsfundregComponent implements OnInit {

  registration: Registration = new Registration();
  constructor(private service : StudentAlumniService, private router:Router) { }

  facultyRegObj : FacultyReg = new FacultyReg();

  ngOnInit(): void {
  }

  onSubmit(){
    console.log(this.facultyRegObj);

    this.service.sendFacultyReg(this.facultyRegObj).subscribe(res => {
        console.log(res);
        alert("Registration Successful.")
        this.router.navigateByUrl('/bitsfundlogin')
    }, error => console.log(error));
    
    
}

}
