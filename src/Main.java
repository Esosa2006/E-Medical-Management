import adminServices.Admin;
import adminServices.UserService;
import entities.Doctor;
import entities.Patient;
import entities.User;

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
        int choice = scanner.nextInt();
        scanner.nextLine();
        while(true){
            if (choice == 1){
                System.out.println("Are you registering as a Patient(1) or a Doctor(2): ");
                int number_choice = scanner.nextInt();
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
                System.out.println("Enter your userID: ");
                String user_id = scanner.nextLine();
                System.out.println("Enter your email: ");
                String email = scanner.nextLine();
                System.out.println("Patient(1) or Doctor(2): ");
                int number = scanner.nextInt();
                if (number == 1 && UserService.search_if_user_exists(email, user_id, number)){
                    System.out.println("What do you want to do");
                    System.out.println("1. Book Appointment");
                    System.out.println("2. Search for Doctor");
                    System.out.println("3. View Appointment History");
                    System.out.println("4. Cancel or Reschedule Appointment");
                }
            }
        }

    }
}