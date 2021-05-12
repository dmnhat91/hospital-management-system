
package model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table (name = "icd_list")
public class ICDList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private ICD icd;

    //@JsonBackReference
    @ManyToOne
    private Prescription prescription;

    public ICDList(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ICD getIcd() {
        return icd;
    }

    public void setIcd(ICD icd) {
        this.icd = icd;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }
}
