package entities;

public class Medications {
    private Long medicationId;
    private String patientId;
    private String name_of_medication;
    private int dosages;
    private int frequency;

    public Medications(String patientId) {
        this.patientId = patientId;
    }

    public void setName_of_medication(String name_of_medication) {this.name_of_medication = name_of_medication;}
    public void setDosages(int dosages) {this.dosages = dosages;}
    public void setFrequency(int frequency) {this.frequency = frequency;}
}
