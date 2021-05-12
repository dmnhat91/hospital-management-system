package service;

import model.*;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class PrescriptionService {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    int maxPage = 5;

    //Methods for prescription
    //Add new prescription
    public void addPrescription(Prescription prescription){

        for (DrugPrescription drugpres: prescription.getDrugpres()) {
            drugpres.setPrescription(prescription);
        }

        for (ICDList icdlist: prescription.getIcdlist()) {
            icdlist.setPrescription(prescription);
        }

        for (LabTest labTest: prescription.getLabtest()) {
            labTest.setPrescription(prescription);
        }


        this.sessionFactory.getCurrentSession().saveOrUpdate(prescription);
    }

    //Get all prescriptions
    public List<Prescription> getAllPrescriptions(){
        Query query = sessionFactory.getCurrentSession().createQuery("from Prescription order by id ASC");
        return query.list();

    }

    //Get all prescriptions by pages
    public List<Prescription> getAllPrescriptionsByPage(int page){
        Query query = sessionFactory.getCurrentSession().createQuery("from Prescription order by id ASC");

        query.setFirstResult((page-1)*maxPage);
        query.setMaxResults(maxPage);

        return query.list();

    }

    public List<Prescription> findPrescriptionsById(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from Prescription p where p.id=:id");

        query.setInteger("id", id);

        return query.list();
    }

    //Find prescriptions by visit ID
    public List<Prescription> findPrescriptionsByVisitId(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("select p from Prescription p where p.visit=:id");

        query.setInteger("id", id);

        return query.list();
    }

    //Find drugs by prescription ID
    public List<Drug> findDrugsByPrescriptionId(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("select d from Drug d, DrugPrescription dp where dp.prescription=:id and d.id = dp.drug");

        query.setInteger("id", id);

        return query.list();
    }

    //Find ICDs by prescription ID
    public List<ICD> findICDsByPrescriptionId(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("select i from ICD i, ICDList il where il.prescription=:id and i.code = il.icd");

        query.setInteger("id", id);

        return query.list();
    }

    //Find drugpres by prescription ID
    public List<DrugPrescription> findDrugPresByPrescriptionId(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from DrugPrescription dp where dp.prescription=:id order by dp.id ASC");

        query.setInteger("id", id);

        return query.list();
    }

    //Find icd lists by prescription ID
    public List<ICDList> findIcdListByPrescriptionId(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from ICDList il where il.prescription=:id order by il.id ASC");

        query.setInteger("id", id);

        return query.list();
    }

    //Find lab order by prescription ID
    public List<LabTest> findLabTestByPrescriptionId(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from LabTest lt where lt.prescription=:id order by lt.id ASC");

        query.setInteger("id", id);

        return query.list();
    }

    //Find med services by prescription ID
    public List<MedicalService> findMedServicesByPrescriptionId(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("select m from MedicalService m, LabTest l where l.prescription=:id and m.id = l.medicalService");

        query.setInteger("id", id);

        return query.list();
    }

    //DELETE
    public void deletePrescription(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from Prescription where id=:id");
        query.setInteger("id", id);

        Prescription prescription= (Prescription) query.uniqueResult();

        sessionFactory.getCurrentSession().delete(prescription);
    }

}
