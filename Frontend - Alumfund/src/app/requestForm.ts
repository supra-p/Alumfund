import { AcademicInfo } from "./academicInfo";
import { ContactDetails } from "./contactDetails";
import { ImageIncome } from "./imageIncome";
import { PersonalInformation } from "./personalInfo";

export class RequestForm{
    id !: any;
    academicInfo: AcademicInfo = new AcademicInfo;
    personalInfo: PersonalInformation = new PersonalInformation;
    contactDetails: ContactDetails = new ContactDetails;
}