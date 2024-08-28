import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PersonalInformation } from '../personalInfo';
import { RequestForm } from '../requestForm';
import { StudentAlumniService } from '../studAlum.service';


@Component({
  selector: 'app-feerequest',
  templateUrl: './feerequest.component.html',
  styleUrls: ['./feerequest.component.css']
})
export class FeerequestComponent implements OnInit {
  retrieveResonse: any;
  retrievedImage: any;
  base64Data: any;

  constructor(private httpClient : HttpClient, private service : StudentAlumniService,
    private router :Router) { }

  checkBoxValueExam: any = null;
  checkBoxValueUgPg: any = null;
  ugpgtext:string = "";
  imgFile!: File;
  imageResponseMessage! : string;
  url!: string;

  personalInfo: PersonalInformation = new PersonalInformation;
  requestFormObj: RequestForm = new RequestForm;

  successResponse: any = true;

 
  
  ngOnInit(): void {
      this.requestFormObj.id = localStorage.getItem('localUserId');
      this.requestFormObj.academicInfo.id = localStorage.getItem('localUserId');
      this.requestFormObj.personalInfo.id = localStorage.getItem('localUserId');
      this.requestFormObj.contactDetails.id = localStorage.getItem('localUserId');
      this.requestFormObj.personalInfo.imageIncome.id = localStorage.getItem('localUserId');

  this.successResponse = true;


  }

  onChange(event: any){
    this.imgFile = event.target.files[0];
    this.url = event.target.result;

    console.log(this.imgFile)
    
  }
  
  uploadImageData!: FormData;
  onUpload(){
    console.log("before",this.uploadImageData)
    this.uploadImageData = new FormData();
    this.uploadImageData.append('imageFile', this.imgFile, this.imgFile.name);
    // this.requestFormObj.imageIncome = uploadImageData;  
    console.log("after",this.uploadImageData)

  }
  
 onSubmit(){
    
     
    console.log(this.uploadImageData)
    
    
     this.service.sendImageToBackend(this.uploadImageData).subscribe(response => {
      console.log(response)
      if (response.status === 200) {
        this.imageResponseMessage = 'Image uploaded successfully';
      } else {
        this.imageResponseMessage = 'Image not uploaded successfully';
      }
      console.log(this.imageResponseMessage);
    })
    

     this.service.sendRequestFormData(this.requestFormObj).subscribe(response =>{
            console.log(response);
    })

    this.uploadImageData = new FormData();

    alert("Registered Successfully")
    this.router.navigateByUrl("\postlogin")
    
  }

   onClickRadio(){
     if( this.requestFormObj.academicInfo.typeOfDegree == "UG")
            {console.log(this.requestFormObj.academicInfo.typeOfDegree)
              this.ugpgtext = "UG Percentage"
            }
            else
            this.ugpgtext = "12th Ranking"
    console.log("TEXT:", this.ugpgtext)
    console.log(this.requestFormObj.academicInfo.marksObtainedInExam)
  }

// x: any = 3;
//   //Gets called when the user clicks on retieve image button to get the image from back end
//   getImage() {
//       this.service.getImageFromBackend(this.x).subscribe(
//         res => {
//           this.retrieveResonse = res;
//           this.base64Data = this.retrieveResonse.picByte;
//           this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data;
//         }
//       );
//   }



}
