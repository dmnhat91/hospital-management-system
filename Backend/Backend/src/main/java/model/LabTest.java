
package model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table (name = "lab_test")
public class LabTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private MedicalService medicalService;

    //@JsonBackReference
    @ManyToOne
    private Prescription prescription;

    public LabTest(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MedicalService getMedicalService() {
        return medicalService;
    }

    public void setMedicalService(MedicalService medicalService) {
        this.medicalService = medicalService;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }
}
