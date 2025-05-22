package entities;

import java.time.LocalDate;

public class MedicalReport {
    private Long patientId;
    private Long doctorId;
    private String diagnosis;
    private String notes;
    private Prescriptions prescriptions;     
    private LocalDate localDate;

    public MedicalReport() {
        this.localDate = LocalDate.now();
    }

    public void setPatientId(Long patientId) {this.patientId = patientId;}
    public void setDoctorId(Long doctorId) {this.doctorId = doctorId;}
    public void setDiagnosis(String diagnosis) {this.diagnosis = diagnosis;}
    public void setNotes(String notes) {this.notes = notes;}
    public void setPrescriptions(Prescriptions prescriptions) {this.prescriptions = prescriptions;}
    public void setLocalDate(LocalDate localDate) {this.localDate = localDate;}


}
