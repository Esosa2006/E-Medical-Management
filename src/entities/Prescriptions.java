package entities;

import java.util.ArrayList;
import java.util.Scanner;

public class Prescriptions {
    private Long prescriptionId = 0L;
    private final String patientId;
    private String doctorId;
    private final ArrayList<Medications> medications = new ArrayList<>();

    public Prescriptions(String patientId, String doctorId) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.prescriptionId++;
    }

    public ArrayList<Medications> getMedications() {return medications;}

    private void setMedications(Medications medication){
        medications.add(medication);
    }

    public void setPrescriptionDetails(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many medications does your patient require: ");
        int size = scanner.nextInt();
        for (int i = 0; i < size; i++){
            Medications medication = new Medications(patientId);
            System.out.println("Name of Medication: ");
            medication.setName_of_medication(scanner.nextLine());
            System.out.println("Amount of Dosages:");
            medication.setDosages(scanner.nextInt());
            System.out.println("Frequency of intake: ");
            medication.setFrequency(scanner.nextInt());
            setMedications(medication);
        }
    }

}
