import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegistrationComponent } from './registration/registration.component';
import { LoginComponent } from './login/login.component';

import { FormsModule } from '@angular/forms';

import { HttpClientModule } from '@angular/common/http';
import { PostloginComponent } from './postlogin/postlogin.component';
import { HomepageComponent } from './homepage/homepage.component';
import { PostloginalumniComponent } from './postloginalumni/postloginalumni.component';
import { FeerequestComponent } from './feerequest/feerequest.component';
import { ViewfeestatusComponent } from './viewfeestatus/viewfeestatus.component';
import { AdminComponent } from './admin/admin.component';
import { AdminpostloginComponent } from './adminpostlogin/adminpostlogin.component';
import { PaymentFeeComponent } from './payment-fee/payment-fee.component';
import { ViewfullprofilestudentComponent } from './viewfullprofilestudent/viewfullprofilestudent.component';
import { BitsfundhomeComponent } from './bitsfundhome/bitsfundhome.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CreateeventComponent } from './createevent/createevent.component';
import { BitsfundloginComponent } from './bitsfundlogin/bitsfundlogin.component';
import { BitsfundregComponent } from './bitsfundreg/bitsfundreg.component';
import { BitsfundpostloginComponent } from './bitsfundpostlogin/bitsfundpostlogin.component';
import { BitsfundpaymentComponent } from './bitsfundpayment/bitsfundpayment.component';
// import { BitsfundregComponent } from './bitsfundreg/bitsfundreg.component';



@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    LoginComponent,
    PostloginComponent,
    HomepageComponent,
    PostloginalumniComponent,
    FeerequestComponent,
    ViewfeestatusComponent,
    AdminComponent,
    AdminpostloginComponent,
    PaymentFeeComponent,
    ViewfullprofilestudentComponent,
    BitsfundhomeComponent,
    BitsfundpostloginComponent,
    CreateeventComponent,
    BitsfundloginComponent,
    BitsfundregComponent,
    BitsfundpostloginComponent,
    BitsfundregComponent,
    BitsfundpaymentComponent,
 
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
