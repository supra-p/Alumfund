import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { AdminpostloginComponent } from './adminpostlogin/adminpostlogin.component';
import { BitsfundhomeComponent } from './bitsfundhome/bitsfundhome.component';
import { BitsfundloginComponent } from './bitsfundlogin/bitsfundlogin.component';
import { BitsfundregComponent } from './bitsfundreg/bitsfundreg.component';
import { BitsfundpostloginComponent } from './bitsfundpostlogin/bitsfundpostlogin.component';
import { CreateeventComponent } from './createevent/createevent.component';
import { FeerequestComponent } from './feerequest/feerequest.component';
import { HomepageComponent } from './homepage/homepage.component';
import { LoginComponent } from './login/login.component';
import { PaymentFeeComponent } from './payment-fee/payment-fee.component';
import { PostloginComponent } from './postlogin/postlogin.component';
import { PostloginalumniComponent } from './postloginalumni/postloginalumni.component';
import { RegistrationComponent } from './registration/registration.component';
import { ViewfeestatusComponent } from './viewfeestatus/viewfeestatus.component';
import { ViewfullprofilestudentComponent } from './viewfullprofilestudent/viewfullprofilestudent.component';
import { BitsfundpaymentComponent } from './bitsfundpayment/bitsfundpayment.component';

const routes: Routes = [
  {path:'', component:HomepageComponent},
  {path:'login', component:LoginComponent},
  {path:'reg', component:RegistrationComponent},
  {path:'postlogin', component: PostloginComponent},
  {path:'postloginalumni', component:PostloginalumniComponent},
  {path:'feerequest', component:FeerequestComponent},
  {path:'viewfeestatus', component:ViewfeestatusComponent},
  {path:'admin', component:AdminComponent},
  {path:'adminpostlogin', component:AdminpostloginComponent},
  {path:'paymentFee', component:PaymentFeeComponent},
  {path:'viewstudprofile', component:ViewfullprofilestudentComponent},
  {path:'bitsfundhome', component:BitsfundhomeComponent},
  {path:'bitsfundpostlogin', component:BitsfundpostloginComponent},
  {path:'createevent', component: CreateeventComponent},
  {path:'bitsfundlogin', component:BitsfundloginComponent},
  {path:'bitsfundreg', component:BitsfundregComponent},
  {path:'bitsfundpayment', component:BitsfundpaymentComponent},
  


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
