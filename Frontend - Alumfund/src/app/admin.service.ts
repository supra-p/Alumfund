import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, of, switchMap } from 'rxjs';
import { Router } from '@angular/router';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  public isLoggedInAdmin: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  constructor(private http: HttpClient,private router: Router) { }

  public validate(id:any){
    return this.http.put(`${baseUrl}/admin/validate/${id}`,null)
   
  }
  public adminviewngo(){
    return this.http.get(`${baseUrl}/admin/dashboard`);
  }
  public adminlogin(admin : any ){
    var formData: any = new FormData();
    formData.append('username', admin.username);
    formData.append('password', admin.password);
    // this.http.post(`${baseUrl}/login`, formData).pipe(map((res:any) => res.json())
    this.http.post(`${baseUrl}/login`, formData)
    .pipe(
      catchError((err) => of(err.status === 404 ? 'success' : 'failed')),
      switchMap ((x) => {
        if(x === "success"){
          
          return this.http.get(`${baseUrl}/admin`)
        }
        else
          return of({status: "failed"});
        })
    ).subscribe((d: any) => {
          console.log(d);
          if(d.status === "success") {
            this.isLoggedInAdmin.next(true);
        this.router.navigate([`./admin`]);
          }
    });
  }
  public logout() {
    alert("logout?")
    this.http.get(`${baseUrl}/logout`)
    
    // .subscribe((d: any) => {
    //   console.log(d);
    //   if ('Success') {
        this.isLoggedInAdmin.next(false);
    //     this.jwtToken = '';
        this.router.navigate([`./home`]);
      }
  }

