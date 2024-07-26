import java.util.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Hospital hospital = new Hospital();
        //Appointment appointment = new Appointment();
        //Admin admin = new Admin("AdminName", "AdminUserName", "AdminPassword");

        boolean flag = true;
        while (flag) {
            System.out.println("**************************");
            System.out.println("Hospital Management System");
            System.out.println("**************************");
            
            //System.out.println("");
            System.out.println("----HOME PAGE----");
            System.out.println("Enter 1 for Registration.   Enter 2 for Patient Login.  Enter 3 for Doctor Login.   Enter 4 for Admin Login.     Enter 5 for Pharmacist Login.     Enter 6 to Exit.");
            System.out.println("Enter your choice: ");

            int userChoice = sc.nextInt();
            switch (userChoice) {
                case 1:
                    hospital.registerUser(sc);
                    break;
                case 2:
                    System.out.println("Patient Option Selected.");
                    System.out.println("Enter your UserName: ");
                    String patientUserName = sc.next();
                    System.out.println("Enter your Password: ");
                    String patientPassword = sc.next();

                    User verifiedPatient = hospital.verifyPatient(patientUserName, patientPassword);
                    if (verifiedPatient != null) {
                        System.out.println("Patient verified. Welcome " + verifiedPatient.getName());
                        
                        boolean patientFlag = true;
                        while(patientFlag)
                        {   
                            System.out.println("**************************");
                            System.out.println("Hospital Management System");
                            System.out.println("**************************");
                            System.out.println("Enter 1 to view all available doctors.  Enter 2 to book an Appointment.  Enter 3 to view your Appointment.  Enter 4 to cancel appointment.  Enter 5 to go back to Home Page.");
                            System.out.println("Enter your choice: ");

                            int patientChoice = sc.nextInt();
                            switch(patientChoice)
                            {
                                case 1:
                                    hospital.displayAllDoctors();
                                    break;
                                case 2:
                                    System.out.println("List of all doctor for your reference: ");
                                    hospital.displayAllDoctors();
                                    hospital.bookAppointment(sc, verifiedPatient);
                                    break;
                                case 3:
                                    hospital.displayPatientAppointments(verifiedPatient.getUserName());
                                    break;
                                case 4:
                                    hospital.cancelAppointment(sc, verifiedPatient);
                                    break;
                                case 5:
                                    patientFlag = false;
                                    break;
                            }
                            
                        }
                    } else
                        System.out.println("Invalid login details for Patient.");
                    break;

                case 3:
                    System.out.println("Doctor Option Selected.");
                    System.out.println("Enter your UserName: ");
                    String doctorUserName = sc.next();
                    System.out.println("Enter your Password: ");
                    String doctorPassword = sc.next();

                    User verifiedDoctor = hospital.verifyDoctor(doctorUserName, doctorPassword);
                    if (verifiedDoctor != null) {
                        System.out.println("Doctor verified. Welcome " + verifiedDoctor.getName()+"!");
                        
                        boolean doctorFlag = true;
                        while(doctorFlag)
                        {
                            System.out.println("**************************");
                            System.out.println("Hospital Management System");
                            System.out.println("**************************");
                            //System.out.println("");
                            System.out.println("Enter 1 to register Doctor. Enter 2 to view all appointments.   Enter 3 to provide Prescription.   Enter 4 to return to Home Page.");
                            System.out.println("Enter your choice: ");

                            int doctorChoice = sc.nextInt();

                            switch(doctorChoice){
                            case 1:
                                // Assuming that we have verifiedDoctor and it's not null
                                //String verifiedDoctorUsername = verifiedDoctor.getUserName();
                                hospital.registerDoctor(sc, verifiedDoctor);
                                break;
                            case 2:
                                hospital.displayDoctorAppointments(verifiedDoctor.getUserName());
                                break;
                            case 3:
                                hospital.providePrescription(sc, (Doctor) verifiedDoctor);
                                break;
                            case 4:
                                doctorFlag = false;
                                break;
                            default:
                                System.out.println("Please Eenter a valid choice.");
                                break;
                            }
                        }
                        
                    } else
                        System.out.println("Invalid login details for Doctor.");
                    break;

                case 4:
                    System.out.println("Admin Option Selected.");
                    System.out.println("Enter your UserName: ");
                    String adminUserName = sc.next();
                    System.out.println("Enter your Password: ");
                    String adminPassword = sc.next();

                    User verifiedAdmin = hospital.verifyAdmin(adminUserName, adminPassword);
                    if (verifiedAdmin != null) {
                        System.out.println("Admin verified. Welcome " + verifiedAdmin.getName()+"!");
                        System.out.println("");

                        boolean adminFlag = true;
                        while(adminFlag)
                        {
                            System.out.println("**************************");
                            System.out.println("Hospital Management System");
                            System.out.println("**************************");
                            System.out.println("Enter 1 to show all available Doctors.  Enter 2 to register new Doctors.    Enter 3 to view all Appointments.   Enter 4 to view all Registered Users.  Enter 5 to cancel an Appointment.    Enter 6 to delete an User.   Enter 7 to exit to Home Page. ");
                            System.out.println("Enter your Choice: ");

                            int adminChoice = sc.nextInt();
                            switch(adminChoice)
                            {
                                case 1: 
                                    hospital.displayAllDoctors();
                                    break;
                                case 2:
                                    hospital.registerDoctorByAdmin(sc);
                                    break;
                                case 3:
                                    hospital.displayAllAppointments();
                                    break;
                                case 4:
                                    hospital.displayAllUsers();
                                    break;
                                case 5:
                                    hospital.cancelAppointment(sc, verifiedAdmin);
                                    break;
                                case 6:
                                    hospital.deleteUserByAdmin(sc);
                                    break;
                                case 7:
                                    adminFlag = false;
                                    break;
                                default:
                                    System.out.println("Please enter a valid choice.");
                                    break;
                            }
                        }
                        
                    } else
                        System.out.println("Invalid login details for Admin.");
                    break;

                case 5:
                    System.out.println("Pharmacist Option Selected.");
                    System.out.println("Enter your UserName: ");
                    String pharmaUserName = sc.next();
                    System.out.println("Enter your Password: ");
                    String pharmaPassword = sc.next();

                    User verifiedPharmacist = hospital.verifyPharmacist(pharmaUserName, pharmaPassword);
                    if (verifiedPharmacist != null) {
                        System.out.println("Pharmacist verified. Welcome " + verifiedPharmacist.getName()+"!");
                        System.out.println("");

                        boolean patientFlag = true;
                        while(patientFlag)
                        {   
                            System.out.println("**************************");
                            System.out.println("Hospital Management System");
                            System.out.println("**************************");
                            System.out.println("Enter 1 to provide Medicine.  Enter 2 to Update Stock.  Enter 3 to go back to Home Page.");
                            System.out.println("Enter your choice: ");

                            int patientChoice = sc.nextInt();
                            switch(patientChoice)
                            {
                                case 1:
                                    //function to provide Medicine
                                    break;
                                case 2:
                                    //function to update Stock
                                    break;
                                case 3:
                                    patientFlag = false;
                                    break;
                            }
                            
                        }
                    } else
                        System.out.println("Invalid login details for Pharmacist.");

                    break;
                
                case 6:
                    flag = false;
                    System.out.println("Thank you. Visit again!");
                    break;

                default:
                    System.out.println("Invalid Option. Please enter a valid choice");
            }
        }
    }
}