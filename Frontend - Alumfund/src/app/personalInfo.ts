import { ImageIncome } from "./imageIncome";

export class PersonalInformation{
    id !: any;
    fullName!: string;
    aadharNumber!: string;
    panCardNumber!: string;
    physicallyDisabled!:string;
    typeOfStudent!: string;
    numberOfPersonsInHousehold!: number;
    totalHouseholdIncome!: number;
    imageIncome: ImageIncome = new ImageIncome;
}