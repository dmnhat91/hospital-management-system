package service;

import model.Drug;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class DrugService {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    int maxPage = 5;

    public void addDrug(Drug drug){
        this.sessionFactory.getCurrentSession().saveOrUpdate(drug);
    }

    //Get all drugs
    public List<Drug> getAllDrugs(){
        Query query = sessionFactory.getCurrentSession().createQuery("from Drug order by id ASC");
        return query.list();

    }

    //Get all drugs by page
    public List<Drug> getAllDrugsByPage(int page){
        Query query = sessionFactory.getCurrentSession().createQuery("from Drug order by id ASC");

        query.setFirstResult((page-1)*maxPage);
        query.setMaxResults(maxPage);

        return query.list();

    }

    //DELETE
    public void deleteDrug(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from Drug where id=:id");
        query.setInteger("id", id);

        Drug drug= (Drug) query.uniqueResult();

        sessionFactory.getCurrentSession().delete(drug);
    }

    //Find drugs by name
    public List<Drug> findDrugsbyName(String name){
        Query query = sessionFactory.getCurrentSession().createQuery("from Drug d where d.name like :name order by id ASC");

        query.setString("name", "%"+name+"%");

        return query.list();
    }

    //Find drugs by name by page
    public List<Drug> findDrugsbyNamePaging(String name, int page){
        Query query = sessionFactory.getCurrentSession().createQuery("from Drug d where d.name like :name order by id ASC");

        query.setString("name", "%"+name+"%");

        query.setFirstResult((page-1)*maxPage);
        query.setMaxResults(maxPage);

        return query.list();
    }


    //Find drug by id
    public List<Drug> findDrugsById(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from Drug d where d.id=:id");

        query.setInteger("id", id);

        return query.list();
    }
}
