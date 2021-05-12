package service;

import model.LabTest;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class LabTestService {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    int maxPage = 5;

    //Methods for LabTest
    //CREATE/UPDATE
    public void addLabTest(LabTest labTest){
        this.sessionFactory.getCurrentSession().saveOrUpdate(labTest);
    }

    //READ
    public List<LabTest> getAllLabTests(){
        Query query = sessionFactory.getCurrentSession().createQuery("from LabTest order by id ASC");
        return query.list();

    }

    //Get all lab tests by pages
    public List<LabTest> getAllLabTestByPage(int page){
        Query query = sessionFactory.getCurrentSession().createQuery("from LabTest order by id ASC");

        query.setFirstResult((page-1)*maxPage);
        query.setMaxResults(maxPage);
        return query.list();

    }

    //DELETE
    public void deleteLabTest(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from LabTest where id=:id");
        query.setInteger("id", id);

        LabTest labTest= (LabTest) query.uniqueResult();

        sessionFactory.getCurrentSession().delete(labTest);
    }
}
