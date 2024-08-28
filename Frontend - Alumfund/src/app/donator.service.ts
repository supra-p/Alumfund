import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class DonatorService {

  constructor(private http: HttpClient) { }
  //add user
  
  public browse() {
    return this.http.get(`${baseUrl}/crowdfund/browse`);
  }
  public donate(don : any , ngo_id : string|null) {
    return this.http.post(`${baseUrl}/crowdfund/donate/${ngo_id}`,don);
  }
}
