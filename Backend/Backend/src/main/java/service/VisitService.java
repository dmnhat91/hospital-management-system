package service;

import model.DrugPrescription;
import model.Patient;
import model.Prescription;
import model.Visit;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.sql.Types.NULL;

@Transactional
@Service
public class VisitService {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    int maxPage = 5;

    //Add a new visit for new/return patient
    //CREATE/UPDATE
    public void addVisit(Visit visit){
        if (visit.getPatient().getId() == NULL){
            Patient new_patient = visit.getPatient();
            sessionFactory.getCurrentSession().saveOrUpdate(new_patient);
            visit.setPatient(new_patient);
        }


        for (Prescription prescription: visit.getPrescriptions()){
            prescription.setVisit(visit);
            for (DrugPrescription drugPrescription: prescription.getDrugpres()){
                drugPrescription.setPrescription(prescription);
            }
        }
        sessionFactory.getCurrentSession().saveOrUpdate(visit);
    }

    //READ: Get all visits
    public List<Visit> getAllVisits(){
        Query query = sessionFactory.getCurrentSession().createQuery("from Visit order by id ASC");
        return query.list();

    }

    //Get all visits by page
    public List<Visit> getAllVisitsByPage(int page){
        Query query = sessionFactory.getCurrentSession().createQuery("from Visit order by id ASC");

        query.setFirstResult((page-1)*maxPage);
        query.setMaxResults(maxPage);

        return query.list();

    }

    //Find visits by id
    public List<Visit> findVisitsById(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from Visit v where v.id=:id");

        query.setInteger("id", id);

        return query.list();
    }

    //DELETE
    public void deleteVisit(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from Visit where id=:id");
        query.setInteger("id", id);

        Visit visit= (Visit) query.uniqueResult();

        sessionFactory.getCurrentSession().delete(visit);
    }

    //Find visit based on patient id
    public List<Visit> findVisitsByPatientId(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from Visit v where patient.id=:id order by v.id ASC");

        query.setInteger("id", id);

        return query.list();
    }

    //Find visit based on patient id with page
    public List<Visit> findVisitsByPatientIdPaging(int id, int page){
        Query query = sessionFactory.getCurrentSession().createQuery("from Visit v where patient.id=:id order by v.id ASC");

        query.setInteger("id", id);

        query.setFirstResult((page-1)*maxPage);
        query.setMaxResults(maxPage);

        return query.list();
    }

    //Find visit based on patient name
    public List<Visit> findVisitsByPatientName(String name){
        Query query = sessionFactory.getCurrentSession().createQuery("from Visit v where patient.name like :name order by id ASC");

        query.setString("name", "%"+name+"%");

        return query.list();
    }

    //Find visit based on patient name with pages
    public List<Visit> findVisitsByPatientNamePaging(String name, int page){
        Query query = sessionFactory.getCurrentSession().createQuery("from Visit v where patient.name like :name order by id ASC");

        query.setString("name", "%"+name+"%");

        query.setFirstResult((page-1)*maxPage);
        query.setMaxResults(maxPage);

        return query.list();
    }

    //Find visit based on visit dates
    public List<Visit> findVisitsByDays(String startDate, String endDate) throws Exception {
        Query query = sessionFactory.getCurrentSession().createQuery("from Visit v where v.visitDate BETWEEN :startDate AND :endDate order by id ASC");

        Date sDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
        query.setDate("startDate", sDate);

        Date eDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
        query.setDate("endDate", eDate);

        return query.list();
    }

    //Find vist based on visit dates paging
    public List<Visit> findVisitsByDaysPaging(String startDate, String endDate, int page) throws Exception {
        Query query = sessionFactory.getCurrentSession().createQuery("from Visit v where v.visitDate BETWEEN :startDate AND :endDate order by id ASC");

        Date sDate = new SimpleDateFormat("dd-MM-yyyy").parse(startDate);
        query.setDate("startDate", sDate);


        Date eDate = new SimpleDateFormat("dd-MM-yyyy").parse(endDate);
        query.setDate("endDate", eDate);

        query.setFirstResult((page-1)*maxPage);
        query.setMaxResults(maxPage);

        return query.list();
    }

    //Find visit based on visit date
    public List<Visit> findVisitsByDate(String visitdate) throws  Exception{
        Query query = sessionFactory.getCurrentSession().createQuery("from Visit v where v.visitDate=:visitdate order by id ASC");

        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(visitdate);
        query.setDate("visitdate", date);

        return query.list();
    }

    //Find visit based on visit date with pages
    public List<Visit> findVisitsByDatePaging(String visitdate, int page) throws  Exception{
        Query query = sessionFactory.getCurrentSession().createQuery("from Visit v where v.visitDate=:visitdate order by id ASC");

        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(visitdate);
        query.setDate("visitdate", date);

        query.setFirstResult((page-1)*maxPage);
        query.setMaxResults(maxPage);

        return query.list();
    }


    //Under construction:
    /*
    //Find visit based on visit dates
    public List<Visit> findVisitsByDate(List<String> indate) throws  Exception{
        List result = new ArrayList();
        for (String visitdate: indate) {
            Query query = sessionFactory.getCurrentSession().createQuery("from Visit v where v.visitDate=:visitdate");

            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(visitdate);
            query.setDate("visitdate", date);
            result.addAll(query.list());
        }

        return result;
    }

    //Find visit based on visit dates with page
    public List<Visit> findVisitsByDatePaging(List<String> indate, int page) throws  Exception{
        List result = new ArrayList();

        for (String visitdate: indate) {
            Query query = sessionFactory.getCurrentSession().createQuery("from Visit v where v.visitDate=:visitdate");

            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(visitdate);
            query.setDate("visitdate", date);
            result.addAll(query.list());
        }

        int fromIndex = (page-1)*maxPage;
        int toIndex = fromIndex + maxPage;

        if (fromIndex >= result.size()) return new ArrayList();
        else if (fromIndex < 0)  fromIndex=0;

        if ((toIndex > result.size()) && (toIndex - result.size()) < maxPage) toIndex = result.size();
        else if (toIndex > result.size()) return new ArrayList();
        else if (toIndex <= 0) toIndex=0;

        return result.subList(fromIndex,toIndex);
    }
    */

}
