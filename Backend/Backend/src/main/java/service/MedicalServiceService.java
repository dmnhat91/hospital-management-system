package service;

import model.MedicalService;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class MedicalServiceService {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    int maxPage = 5;

    public void addMedService(MedicalService medicalService){
        this.sessionFactory.getCurrentSession().saveOrUpdate(medicalService);
    }

    //Get all med services
    public List<MedicalService> getAllMedServices(){
        Query query = sessionFactory.getCurrentSession().createQuery("from MedicalService order by id ASC");
        return query.list();

    }

    //Get all med services by page
    public List<MedicalService> getAllMedServicesByPage(int page){
        Query query = sessionFactory.getCurrentSession().createQuery("from MedicalService order by id ASC");

        query.setFirstResult((page-1)*maxPage);
        query.setMaxResults(maxPage);

        return query.list();

    }

    //DELETE
    public void deleteMedService(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from MedicalService where id=:id");
        query.setInteger("id", id);

        MedicalService medicalService= (MedicalService) query.uniqueResult();

        sessionFactory.getCurrentSession().delete(medicalService);
    }

    //Find med services by name
    public List<MedicalService> findMedServicesbyName(String name){
        Query query = sessionFactory.getCurrentSession().createQuery("from MedicalService m where m.name like :name order by id ASC");

        query.setString("name", "%"+name+"%");

        return query.list();
    }

    //Find med services by name by page
    public List<MedicalService> findMedServicesbyNamePaging(String name, int page){
        Query query = sessionFactory.getCurrentSession().createQuery("from MedicalService m where m.name like :name order by id ASC");

        query.setString("name", "%"+name+"%");

        query.setFirstResult((page-1)*maxPage);
        query.setMaxResults(maxPage);

        return query.list();
    }


    //Find medical service by id
    public List<MedicalService> findMedicalServicesById(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from MedicalService m where m.id=:id");

        query.setInteger("id", id);

        return query.list();
    }

    //Find medical services by type
    public List<MedicalService> findMedServicesByType(String type){
        Query query = sessionFactory.getCurrentSession().createQuery("from MedicalService m where m.type=:type order by id ASC");

        query.setString("type", type);

        return query.list();
    }
}
