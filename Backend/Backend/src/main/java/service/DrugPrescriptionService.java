package service;

import model.DrugPrescription;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class DrugPrescriptionService {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    int maxPage = 5;

    //Methods for DrugPrescription
    //CREATE/UPDATE
    public void addDrugPres(DrugPrescription drugPrescription){
        this.sessionFactory.getCurrentSession().saveOrUpdate(drugPrescription);
    }

    //READ
    public List<DrugPrescription> getAllDrugPres(){
        Query query = sessionFactory.getCurrentSession().createQuery("from DrugPrescription order by id ASC");
        return query.list();

    }

    //Get all drug prescriptions by pages
    public List<DrugPrescription> getAllDrugPresByPage(int page){
        Query query = sessionFactory.getCurrentSession().createQuery("from DrugPrescription order by id ASC");

        query.setFirstResult((page-1)*maxPage);
        query.setMaxResults(maxPage);
        return query.list();

    }

    //DELETE
    public void deleteDrugPres(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from DrugPrescription where id=:id");
        query.setInteger("id", id);

        DrugPrescription drugPrescription= (DrugPrescription) query.uniqueResult();

        sessionFactory.getCurrentSession().delete(drugPrescription);
    }
}
