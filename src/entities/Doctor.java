package entities;

import enums.AppointmentStatus;
import enums.DoctorAvailability;
import enums.Role;
import exceptions.AppointmentNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Doctor extends User{
    private String specialization;
    private ArrayList<Appointment> pendingAppointments;
    private ArrayList<Appointment> acceptedAppointments = new ArrayList<>();
    private ArrayList<MedicalReport> allMedicalReports;
    private DoctorAvailability doctorAvailability = DoctorAvailability.AVAILABLE;

    public Doctor(String name, String email, String phone_no) {
        super(name, email, phone_no);
        this.setRole(Role.DOCTOR);

    }

    public Doctor() {
        this.setRole(Role.DOCTOR);
        this.pendingAppointments = new ArrayList<>();
        this.allMedicalReports = new ArrayList<>();
        this.acceptedAppointments = new ArrayList<>();
    }

    public ArrayList<MedicalReport> viewAllMedicalReports(){
        return allMedicalReports;
    }

    public void setSpecialization(String specialization){this.specialization = specialization;}
    public String getSpecialization() {return specialization;}

    public DoctorAvailability getDoctorAvailabilityStatus() {return doctorAvailability;}

    public MedicalReport givePatientDiagnosis(String patients_email){
        Scanner scanner = new Scanner(System.in);
        MedicalReport medicalReport = new MedicalReport();
        System.out.println("Diagnosis: ");
        String diagnosis = scanner.nextLine();
        medicalReport.setDiagnosis(diagnosis);
        System.out.println("Add Notes: ");
        String notes = scanner.nextLine();
        medicalReport.setNotes(notes);
        System.out.println("Prescriptions");
        Prescriptions prescriptions = new Prescriptions(patients_email, getId());
        prescriptions.setPrescriptionDetails();
        allMedicalReports.add(medicalReport);
        return medicalReport;
    }

    public void setDoctorAvailabilityStatus(String status){
        if (status.equalsIgnoreCase("available")){
            this.doctorAvailability = DoctorAvailability.AVAILABLE;
        }
        else if (status.equalsIgnoreCase("unavailable")){
            this.doctorAvailability = DoctorAvailability.NOT_AVAILABLE;
        }
        else{
            System.out.println("Choose between (Available / Not-Available");
        }
    }

    public ArrayList<Appointment> viewListOfAcceptedAppointments(){
        return acceptedAppointments;
    }

    private Appointment findAppointmentByID(String appID){
        for (Appointment appointment : pendingAppointments){
            if (appointment.getAppointmentId().equalsIgnoreCase(appID)){
                return appointment;
            }
        }
        throw new AppointmentNotFoundException("Appointment was not found");
    }

    public void acceptFromPending(String appID){
        Appointment appointment = findAppointmentByID(appID);
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many days till appointment: ");
        int number_of_days = scanner.nextInt();
        appointment.setScheduledDate(number_of_days);
        acceptedAppointments.add(appointment);
    }

    public void updateAppointmentStatuses(){
        for (Appointment appointment : acceptedAppointments){
            if (appointment.getScheduledDate().isEqual(LocalDate.now())){
                appointment.setAppointmentStatus(AppointmentStatus.IN_PROGRESS);
            }
            if (appointment.getScheduledDate().isAfter(LocalDate.now())){
                appointment.setAppointmentStatus(AppointmentStatus.CONFIRMED);
            }
            if (appointment.getScheduledDate().isBefore(LocalDate.now())){
                appointment.setAppointmentStatus(AppointmentStatus.COMPLETED);
            }
        }
    }

    public void addToPending(Appointment appointment) {
        pendingAppointments.add(appointment);
    }
}
