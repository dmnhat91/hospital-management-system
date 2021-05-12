package model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "prescription")
public class Prescription {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int quantity;

    @Column
    private String dose;

    @Column
    private String usage;

    @Column
    private String generalDiagnosis;

    @JsonManagedReference
    @OneToMany (mappedBy = "prescription", fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JsonIgnore
    private List<DrugPrescription> drugpres = new ArrayList();

    //@JsonBackReference
    @ManyToOne
    private Visit visit;

    @JsonManagedReference
    @OneToMany (mappedBy = "prescription", fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JsonIgnore
    private List<LabTest> labtest = new ArrayList();

    @JsonManagedReference
    @OneToMany (mappedBy = "prescription", fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JsonIgnore
    private List<ICDList> icdlist = new ArrayList();

    public Prescription(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public List<DrugPrescription> getDrugpres() {
        return drugpres;
    }

    public void setDrugpres(List<DrugPrescription> drugpres) {
        this.drugpres = drugpres;
    }

    public Visit getVisit() {
        return visit;
    }

    public void setVisit(Visit visit) {
        this.visit = visit;
    }

    public String getGeneralDiagnosis() {
        return generalDiagnosis;
    }

    public void setGeneralDiagnosis(String generalDiagnosis) {
        this.generalDiagnosis = generalDiagnosis;
    }

    public List<LabTest> getLabtest() {
        return labtest;
    }

    public void setLabtest(List<LabTest> labtest) {
        this.labtest = labtest;
    }

    public List<ICDList> getIcdlist() {
        return icdlist;
    }

    public void setIcdlist(List<ICDList> icdlist) {
        this.icdlist = icdlist;
    }
}

/*
Sample records:


 */