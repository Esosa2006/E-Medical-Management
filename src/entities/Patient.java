package entities;

import adminServices.UserService;
import enums.AppointmentStatus;
import enums.DoctorAvailability;
import enums.Role;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Patient extends User {
    private ArrayList<MedicalReport> medicalHistory;
    private ArrayList<Appointment> appointmentHistory;

    public Patient(Long id, String name, String email, String phone_no) {
        super(name, email, phone_no);
        this.setRole(Role.PATIENT);
    }

    public Patient() {
        this.setRole(Role.PATIENT);
        this.medicalHistory = new ArrayList<>();
        this.appointmentHistory = new ArrayList<>();
    }

    public void addToMedicalHistory(MedicalReport report){
        medicalHistory.add(report);
    }

    public ArrayList<MedicalReport> viewMedicalHistory(){
        return medicalHistory;
    }
    public void bookAppointment(String doctors_name) {
        boolean found = false;
        for (Doctor doctor : UserService.getList_of_doctors()) {
            if (doctor.getName().equalsIgnoreCase(doctors_name)) {
                found = true;
                if (doctor.getDoctorAvailabilityStatus() == DoctorAvailability.AVAILABLE) {
                    System.out.println("Doctor is available");
                    Appointment appointment = new Appointment(getId(), doctor.getId());
                    doctor.addToPending(appointment);
                    appointment.setAppointmentStatus(AppointmentStatus.CONFIRMED);
                    appointmentHistory.add(appointment);
                } else {
                    System.out.println("Doctor is not available");
                }
                break;
            }
        }
        if (!found) {
            System.out.println("Doctor does not exist");
        }

    }

    public void searchForDoctor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Search by name: ");
        System.out.println("2. Search by specialization: ");
        System.out.println("3. Search by availability: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            System.out.println("Enter the name of the doctor: ");
            String name = scanner.nextLine();
            UserService.searchByName(name);
        } else if (choice == 2) {
            System.out.println("Specialization: ");
            String specialization = scanner.nextLine();
            UserService.searchBySpecialization(specialization);
        } else if (choice == 3) {
            UserService.searchByAvailability();
        } else {
            System.out.println("Invalid Input");
        }
    }
    public ArrayList<Appointment> viewAppointmentHistory(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. View Upcoming Appointments");
        System.out.println("2. View Past Appointments");
        int choice =  scanner.nextInt();
        scanner.nextLine();
        if (choice == 1){
            ArrayList<Appointment> upcoming = new ArrayList<>();
            for (Appointment appointment : appointmentHistory){
                if (LocalDate.now().isBefore(appointment.getScheduledDate())){
                    upcoming.add(appointment);
                }
            }
            return upcoming;
        }
        else if (choice == 2){
            ArrayList<Appointment> past = new ArrayList<>();
            for (Appointment appointment : appointmentHistory){
                if (LocalDate.now().isAfter(appointment.getScheduledDate())){
                    past.add(appointment);
                }
            }
            return past;
        }
        else{
            System.out.println("Invalid input!");
            return new ArrayList<>();
        }
    }
    public void cancelOrRescheduleAppointment(){
        Scanner scanner = new Scanner(System.in);
        for (Appointment appointment : appointmentHistory){
            if (appointment.getAppointmentStatus() == AppointmentStatus.CONFIRMED){
                System.out.println(appointment);
                System.out.println("Do you want to cancel or reschedule this appointment: ");
                String answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("cancel")){
                    appointment.setAppointmentStatus(AppointmentStatus.CANCELLED);
                }
                else if(answer.equalsIgnoreCase("reschedule")){
                    System.out.println("How many days from now would you like to reschedule for: ");
                    int number = scanner.nextInt();
                    appointment.setScheduledDate(number);
                }
                else{
                    System.out.println("Invalid Option");
                }
            }
        }
    }
}
