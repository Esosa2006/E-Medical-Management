package entities;

import enums.AppointmentStatus;

import java.time.LocalDate;
import java.util.Random;

public class Appointment {
    private String appointmentId;
    private String patientId;
    private String doctorId;
    private LocalDate scheduledDate;
    private AppointmentStatus appointmentStatus;


    public Appointment(String patientId, String doctorId) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        setAppointmentId();
    }

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId() {
        Random rand = new Random();
        this.appointmentId = patientId + " -" + rand.nextInt(500) + "- " + doctorId;
    }

    public void setScheduledDate(int number_of_days) {
        this.scheduledDate = LocalDate.now().plusDays(number_of_days);
    }

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }
}
