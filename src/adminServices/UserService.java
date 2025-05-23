package adminServices;

import entities.Doctor;
import entities.Patient;
import enums.DoctorAvailability;
import exceptions.UserAlreadyExistsException;
import exceptions.UserNotFoundException;

import java.util.ArrayList;

public class UserService {
    private static final ArrayList<Doctor> list_of_doctors = new ArrayList<>();
    private static final ArrayList<Patient> list_of_patients = new ArrayList<>();

    public static void addNewDoctor(Doctor doctor2) {
        for (Doctor doctor : list_of_doctors){
            if (doctor.getEmail().equalsIgnoreCase(doctor2.getEmail())){
                throw new UserAlreadyExistsException("User already exists!");
            }
        }
        System.out.println("User registration successful");
        list_of_doctors.add(doctor2);
    }

    public static Patient getPatientFromList(String email){
        for (Patient patient : list_of_patients){
            if (patient.getEmail().equalsIgnoreCase(email)){
                return patient;
            }
        }
        return null;
    }
    public static Doctor getDoctorFromList(String email){
        for (Doctor doctor : list_of_doctors){
            if (doctor.getEmail().equalsIgnoreCase(email)){
                return doctor;
            }
        }
        return null;
    }
    public static void patientMenu(){
        System.out.println("What do you want to do");
        System.out.println("1. Book Appointment");
        System.out.println("2. Search for Doctor");
        System.out.println("3. View Appointment History");
        System.out.println("4. Cancel or Reschedule Appointment");
        System.out.println("5. View Medical History");
        System.out.println("6. Logout");
    }

    public static void doctorMenu(){
        System.out.println("What would you like to do: ");
        System.out.println("1. Give Patient Diagnosis");
        System.out.println("2. Set your Availability Status");
        System.out.println("3. View all your appointments");
        System.out.println("4. Accept appointment");
        System.out.println("5. Update Appointment Statuses");
        System.out.println("6. View all Medical Reports");
        System.out.println("7. Get list of patients");
        System.out.println("8. Logout");
    }

    public static void addNewPatient(Patient patient2){
        for (Patient patient : list_of_patients){
            if (patient.getEmail().equalsIgnoreCase(patient2.getEmail())){
                throw new UserAlreadyExistsException("User already exists!");
            }
        }
        System.out.println("User registration successful");
        list_of_patients.add(patient2);
    }

    public static ArrayList<Doctor> getList_of_doctors() {
        return list_of_doctors;
    }

    public static ArrayList<Patient> getList_of_patients() {
        return list_of_patients;
    }
    public static void searchByName(String name){
        for (Doctor doctor : list_of_doctors){
            if (doctor.getName().equalsIgnoreCase(name)){
                System.out.println(doctor);
            }
            else{
                System.out.println("No doctor by this name!");
            }
        }
    }

    public static void searchBySpecialization(String specialization){
        for (Doctor doctor : list_of_doctors){
            if (doctor.getSpecialization().equalsIgnoreCase(specialization)){
                System.out.println(doctor);
            }
            else{
                System.out.println("No specialist in this field!");
            }
        }
    }

    public static void searchByAvailability(){
        for (Doctor doctor : list_of_doctors){
            if (doctor.getDoctorAvailabilityStatus() == DoctorAvailability.AVAILABLE){
                System.out.println(doctor);
            }
            else{
                System.out.println("No available doctors at the moment!");
            }
        }
    }

    public static boolean search_if_user_exists(String email, String user_id, int number) {
        if (number == 1) {
            for (Patient patient : list_of_patients) {
                if (patient.getEmail().equalsIgnoreCase(email) && patient.getId().equalsIgnoreCase(user_id)) {
                    System.out.println("Welcome back " + patient.getName());
                    return true;
                }
                else {
                    throw new UserNotFoundException("This id does not belong to this patient!");
                }
            }
        }
        else if (number == 2) {
            for (Doctor doctor : list_of_doctors) {
                if (doctor.getEmail().equalsIgnoreCase(email) && doctor.getId().equalsIgnoreCase(user_id)) {
                    System.out.println("Welcome back Doctor " + doctor.getName());
                    return true;
                }
                else {
                    throw new UserNotFoundException("This id does not belong to this doctor!");
                }
            }
        }
        else {
            System.out.println("Wrong input");
            return false;
        }
        return false;
    }
}
