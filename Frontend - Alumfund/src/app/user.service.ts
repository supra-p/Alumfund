import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';
import { Router } from '@angular/router';
import { BehaviorSubject, map ,catchError, of, switchMap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  isLoggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  jwtToken = '';
  constructor(private http: HttpClient, private router: Router) { }
  //add user
  public addUser(user: any) {
    return this.http.post(`${baseUrl}/register`, user);
  };
 

  public login(ngo: any) {
    var formData: any = new FormData();
    formData.append('username', ngo.username);
    formData.append('password', ngo.password);
    // this.http.post(`${baseUrl}/login`, formData).pipe(map((res:any) => res.json())
    this.http.post(`${baseUrl}/login`, formData)
    .pipe(
      catchError((err) => of(err.status === 404 ? 'success' : 'failed')),
      switchMap ((x) => {
        if(x === "success")

          return this.http.get(`${baseUrl}/ngo`)
        else
          return of({status: "failed"});
        })
    ).subscribe((d: any) => {
          console.log(d);
          if(d.status === "success") {
            this.isLoggedIn.next(true);
        this.router.navigate([`./ngoDashboard`]);
          }
    });
  }

  public viewDonation() {
    const headers = { 'Authorization': this.jwtToken };
    return this.http.get(`${baseUrl}/ngo/view_donation`, { headers });
  }

  public logout() {
    alert("logout?")
    this.http.get(`${baseUrl}/logout`)
    
    // .subscribe((d: any) => {
    //   console.log(d);
    //   if ('Success') {
        this.isLoggedIn.next(false);
        
    //     this.jwtToken = '';
        this.router.navigate([`./home`]);
      }
      public TotalDonation() {
        return this.http.get(`${baseUrl}/ngo/total`);
      }
    }
    
    // )
    ;
//   }
// }
