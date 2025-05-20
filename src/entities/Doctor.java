package entities;

import enums.AppointmentStatus;
import enums.DoctorAvailability;
import enums.Role;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Doctor extends User{
    private String specialization;
    private final ArrayList<Appointments> appointments = new ArrayList<>();
    private final ArrayList<MedicalReport> allMedicalReports = new ArrayList<>();
    private DoctorAvailability doctorAvailability = DoctorAvailability.AVAILABLE;

    public Doctor(String name, String email, String phone_no) {
        super(name, email, phone_no);
        this.setRole(Role.DOCTOR);
    }

    public Doctor() {
        setId();
    }

    public void setSpecialization(String specialization){this.specialization = specialization;}
    public String getSpecialization() {return specialization;}

    public DoctorAvailability getDoctorAvailabilityStatus() {return doctorAvailability;}

    public MedicalReport givePatientDiagnosis(String patientId){
        Scanner scanner = new Scanner(System.in);
        MedicalReport medicalReport = new MedicalReport();
        System.out.println("Diagnosis: ");
        String diagnosis = scanner.nextLine();
        medicalReport.setDiagnosis(diagnosis);
        System.out.println("Add Notes: ");
        String notes = scanner.nextLine();
        medicalReport.setNotes(notes);
        System.out.println("Prescriptions");
        Prescriptions prescriptions = new Prescriptions(patientId, getId());
        prescriptions.setPrescriptionDetails();
        allMedicalReports.add(medicalReport);
        return medicalReport;
    }

    public void setAppointmentStatus(String status){
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

    public ArrayList<Appointments> viewListOfAppointments(){
        return appointments;
    }

    public void addAppointment(Appointments appointment){
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many days till appointment: ");
        int number_of_days = scanner.nextInt();
        appointment.setScheduledDate(number_of_days);
        appointments.add(appointment);
    }

    public void updateAppointmentStatuses(){
        for (Appointments appointment : appointments){
            if (appointment.getScheduledDate().isEqual(LocalDate.now())){
                appointment.setAppointmentStatus(AppointmentStatus.IN_PROGRESS);
            }
            if (appointment.getScheduledDate().isBefore(LocalDate.now())){
                appointment.setAppointmentStatus(AppointmentStatus.CONFIRMED);
            }
            if (appointment.getScheduledDate().isAfter(LocalDate.now())){
                appointment.setAppointmentStatus(AppointmentStatus.COMPLETED);
            }
        }
    }
}
