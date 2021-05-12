
package service;

import model.*;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class PatientService {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    int maxPage = 5;

    //READ method: Get all patients py pages
    public List<Patient> getAllPatientsByPage(int page){
        Query query = sessionFactory.getCurrentSession().createQuery("from Patient order by id ASC");
        query.setFirstResult((page-1)*maxPage);
        query.setMaxResults(maxPage);
        return query.list();
    }

    //READ Method: Get all patients
    public List<Patient> getAllPatients(){
        Query query = sessionFactory.getCurrentSession().createQuery("from Patient order by id ASC");
        return query.list();

    }

    //CREATE/UPDATE method
    public void addPatient(Patient patient){
        sessionFactory.getCurrentSession().saveOrUpdate(patient);
    }

    //DELETE method
    public void deletePatient(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from Patient where id=:id");
        query.setInteger("id", id);

        Patient patient= (Patient) query.uniqueResult();

        sessionFactory.getCurrentSession().delete(patient);
    }

    //Find patients by name
    public List<Patient> findPatientsByName(String name){
        Query query = sessionFactory.getCurrentSession().createQuery("from Patient p where p.name like :name order by id ASC");

        query.setString("name", "%"+name+"%");

        return query.list();
    }

    //Find patients by name by page
    public List<Patient> findPatientsByNamePaging(String name, int page){
        Query query = sessionFactory.getCurrentSession().createQuery("from Patient p where p.name like :name order by id ASC");

        query.setString("name", "%"+name+"%");
        query.setFirstResult((page-1)*maxPage);
        query.setMaxResults(maxPage);

        return query.list();
    }

    //Find patients by id
    public List<Patient> findPatientsById(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from Patient p where p.id=:id");

        query.setInteger("id", id);

        return query.list();
    }

    //Find patients by birthdate - ex: birthdate=10-05-1999 (dd-mm-yyyy)
    public List<Patient> findPatientsByBd(String birthdate) throws  Exception{
        Query query = sessionFactory.getCurrentSession().createQuery("from Patient p where p.birthdate=:birthdate order by id ASC");

        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(birthdate);
        query.setDate("birthdate", date);

        return query.list();
    }

    //Find patients by birthdate paging
    public List<Patient> findPatientsByBdPaging(String birthdate, int page) throws  Exception{
        Query query = sessionFactory.getCurrentSession().createQuery("from Patient p where p.birthdate=:birthdate order by id ASC");

        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(birthdate);
        query.setDate("birthdate", date);

        query.setFirstResult((page-1)*maxPage);
        query.setMaxResults(maxPage);

        return query.list();
    }


}
