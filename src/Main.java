import adminServices.Admin;
import adminServices.UserService;
import entities.Doctor;
import entities.MedicalReport;
import entities.Patient;
import entities.User;
import exceptions.NullUserException;
import exceptions.UserNotFoundException;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------------------------------------");
        System.out.println("|      E - Medical Management Platform        |");
        System.out.println("----------------------------------------------");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Quit");
        int choice = scanner.nextInt();
        scanner.nextLine();
        while(true){
            if (choice == 1){
                System.out.println("Are you registering as a Patient(1) or a Doctor(2): ");
                int number_choice = scanner.nextInt();
                scanner.nextLine();
                if (number_choice == 1){
                    Patient patient = new Patient();
                    System.out.println("What is your name: ");
                    patient.setName(scanner.nextLine());
                    System.out.println("Email: ");
                    patient.setEmail(scanner.nextLine());
                    System.out.println("Phone Number: ");
                    patient.setPhone_no(scanner.nextLine());
                    patient.setId();
                    UserService.addNewPatient(patient);
                    System.out.println("This is your userID, use this to log into your account");
                    System.out.println(patient.getId());
                }
                else if (number_choice == 2){
                    Doctor doctor =  new Doctor();
                    System.out.println("What is your name: ");
                    doctor.setName(scanner.nextLine());
                    System.out.println("Email: ");
                    doctor.setEmail(scanner.nextLine());
                    System.out.println("Phone Number: ");
                    doctor.setPhone_no(scanner.nextLine());
                    System.out.println("Specialization: ");
                    doctor.setSpecialization(scanner.nextLine());
                    doctor.setId();
                    UserService.addNewDoctor(doctor);
                    System.out.println("This is your userID, use this to log into your account");
                    System.out.println(doctor.getId());
                }
                else{
                    System.out.println("Invalid input!");
                }
            }
            else if (choice == 2) {
                scanner.nextLine();
                System.out.println("Enter your userID: ");
                String user_id = scanner.nextLine();
                System.out.println("Enter your email: ");
                String email = scanner.nextLine();
                System.out.println("Patient(1) or Doctor(2): ");
                int number = scanner.nextInt();
                if (number == 1 && UserService.search_if_user_exists(email, user_id, number)){
                    Patient patient = UserService.getPatientFromList(email);
                    System.out.println("What do you want to do");
                    System.out.println("1. Book Appointment");
                    System.out.println("2. Search for Doctor");
                    System.out.println("3. View Appointment History");
                    System.out.println("4. Cancel or Reschedule Appointment");
                    System.out.println("5. View Medical History");
                    int patient_number_selection = scanner.nextInt();
                    if (patient == null){
                        throw new NullUserException("Null user found");
                    }
                    if (patient_number_selection == 1 ){
                        System.out.println("With whom would you like to book an appointment with: ");
                        String doctors_name = scanner.nextLine();
                        patient.bookAppointment(doctors_name);
                    }
                    else if (patient_number_selection == 2){
                        patient.searchForDoctor();
                    }
                    else if (patient_number_selection == 3){
                        patient.viewAppointmentHistory();
                    }
                    else if (patient_number_selection == 4){
                        patient.cancelOrRescheduleAppointment();
                    }
                    else if (patient_number_selection == 5) {
                        System.out.println(patient.viewMedicalHistory());
                    }
                    else{
                        System.out.println("Invalid number selection");
                    }
                }
                else if (number == 2 && UserService.search_if_user_exists(email, user_id, number)){
                    Doctor doctor = UserService.getDoctorFromList(email);
                    System.out.println("What would you like to do: ");
                    System.out.println("1. Give Patient Diagnosis");
                    System.out.println("2. Set your Availability Status");
                    System.out.println("3. View all your appointments");
                    System.out.println("4. Accept appointment");
                    System.out.println("5. Update Appointment Statuses");
                    System.out.println("6. View all Medical Reports");
                    int doctor_number_selection = scanner.nextInt();
                    if (doctor == null){
                        throw new NullUserException("Null user found");
                    }
                    if (doctor_number_selection == 1){
                        System.out.println("Patient email: ");
                        String patient_email = scanner.nextLine();
                        Patient patient = UserService.getPatientFromList(patient_email);
                        if (patient == null){
                            throw new NullUserException("Null user found");
                        }
                        MedicalReport report = doctor.givePatientDiagnosis(patient_email);
                        patient.addToMedicalHistory(report);
                    }
                    else if (doctor_number_selection == 2){
                        System.out.println("What is your availability status(available/unavailable");
                        String availabilityStatus = scanner.nextLine();
                        doctor.setDoctorAvailabilityStatus(availabilityStatus);
                    }
                    else if (doctor_number_selection == 3) {
                        System.out.println("View all your appointments: ");
                        System.out.println(doctor.viewListOfAcceptedAppointments());
                    }
                    else if (doctor_number_selection == 4){
                        System.out.println("Enter the appointment ID: ");
                        String appointmentID = scanner.nextLine();
                        doctor.acceptFromPending(appointmentID);
                    }
                    else if (doctor_number_selection == 5) {
                        doctor.updateAppointmentStatuses();
                    }
                    else if (doctor_number_selection == 6){
                        System.out.println(doctor.viewAllMedicalReports());
                    }
                    else{
                        System.out.println("Invalid input!");
                    }
                }
                else{
                    System.out.println("Invalid Input / User not found!");
                }
            }
            else if (choice == 3) {
                System.out.println("Thank you for using the system!");
                break;
            }
            else{
                System.out.println("Invalid Input!");
            }
            System.out.println("Is there anything else you would like to do?");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Quit");
            choice = scanner.nextInt();
        }

    }
}