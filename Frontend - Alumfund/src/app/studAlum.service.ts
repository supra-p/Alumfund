import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Registration } from './registration';
import { Observable } from 'rxjs';
import { Admin } from './admin';
import { RequestForm } from './requestForm';
import { CreateEvent } from './createEvent';
import { FacultyReg } from './facultyReg';

@Injectable({
  providedIn: 'root'
})
export class StudentAlumniService {

  private baseUrl  = "http://localhost:8080/fund";

  constructor(private httpClient : HttpClient) { }

  checkLogin(registration : Registration) : Observable<any>{
    
        return this.httpClient.post(`${this.baseUrl}/login`, registration, {responseType: 'text'});
  }

  checkFacultyLogin(facultyObj : FacultyReg):Observable<any>{
    return this.httpClient.post(`${this.baseUrl}/facultyLogin`, facultyObj, {responseType: 'text'});

  }

  register(regObj : Registration) : Observable<any>{
    // console.log("service", regObj)
        return this.httpClient.post(`${this.baseUrl}/reg`, regObj)
  }

  adminRecords() : Observable<any>{
    return this.httpClient.get(`${this.baseUrl}/adminRecords`)
  }

  deleteAdminRecord(id: string , userType: string) : Observable<any>
  {
    return this.httpClient.delete(`${this.baseUrl}/deleteAdminRecord/${id}/${userType}` , {responseType: 'text'});
  }

  approveAdminRecord(id: string, userType: string) : Observable<any>
  {
    return this.httpClient.delete(`${this.baseUrl}/approveAdminRecord/${id}/${userType}` , {responseType: 'text'});
  }

  // CHANGE HERE
  sendImageToBackend(uploadImageData: FormData) : Observable<any>{
      return this.httpClient.post(`${this.baseUrl}/sendImageToBackend`, uploadImageData);
  }

  sendRequestFormData(requestForm : RequestForm): Observable<any>{
    return this.httpClient.post(`${this.baseUrl}/fundRequestForm`, requestForm);
  }

  getImageFromBackend(x: any): Observable<any>{
    return this.httpClient.get(`${this.baseUrl}/get/${x}`)
  }

  getAllRecords(): Observable<any>{
    return this.httpClient.get(`${this.baseUrl}/getAllRecords`);
  }

  getAllImages() : Observable<any>{
    return this.httpClient.get(`${this.baseUrl}/getAllImages`);
  }

  getFeeStatus(userId: any) : Observable<any>{
    return this.httpClient.get(`${this.baseUrl}/getFeeStatus/${userId}`,  {responseType: 'text'});
  }

  sendEventDetails(createEvent : CreateEvent): Observable<any>{
    return this.httpClient.post(`${this.baseUrl}/createevent`, createEvent);
  }

  getAllEventsUnderFaculty(loginFacultyEmpId: any) : Observable<any> {
    return this.httpClient.get(`${this.baseUrl}/getAllEventsUnderFaculty/${loginFacultyEmpId}`);

  }

  getAllEvents(): Observable<any> {
    return this.httpClient.get(`${this.baseUrl}/getAllEvents`);

  }

  sendFacultyReg(facultyRegObj : FacultyReg ):Observable<any>{
    return this.httpClient.post(`${this.baseUrl}/sendFacultyReg`, facultyRegObj);

  }

  updateContribution(tempList:any):Observable<any>{
    console.log("hihi", tempList)
    return this.httpClient.post(`${this.baseUrl}/updateContribution`, tempList);

  }

  getFacultyName(empId:any):Observable<any>{
    return this.httpClient.get(`${this.baseUrl}/getFacultyName/${empId}`, {responseType: 'text'});

  }
  

  deleteEvent(eventId:any):Observable<any>{
    return this.httpClient.delete(`${this.baseUrl}/deleteEvent/${eventId}` , {responseType: 'text'});

  }

  paythisstudentfee(studId:any):Observable<any>{
    return this.httpClient.get(`${this.baseUrl}/paythisstudentfee/${studId}`);

  }


}
