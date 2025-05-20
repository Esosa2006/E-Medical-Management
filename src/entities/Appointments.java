package entities;

import enums.AppointmentStatus;

import java.time.LocalDate;

public class Appointments {
    private String patientId;
    private String doctorId;
    private LocalDate scheduledDate;
    private AppointmentStatus appointmentStatus;


    public Appointments(String patientId, String doctorId) {
        this.patientId = patientId;
        this.doctorId = doctorId;
    }

    public LocalDate getScheduledDate() {return scheduledDate;}
    public void setScheduledDate(int number_of_days){this.scheduledDate = LocalDate.now().plusDays(number_of_days);}

    public AppointmentStatus getAppointmentStatus() {return appointmentStatus;}
    public void setAppointmentStatus(AppointmentStatus appointmentStatus) {this.appointmentStatus = appointmentStatus;}
}
