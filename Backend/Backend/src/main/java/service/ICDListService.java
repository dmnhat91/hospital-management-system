package service;

import model.ICDList;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ICDListService {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    int maxPage = 5;

    //Methods for ICDList
    //CREATE/UPDATE
    public void addICDList(ICDList icdList){
        this.sessionFactory.getCurrentSession().saveOrUpdate(icdList);
    }

    //READ
    public List<ICDList> getAllICDLists(){
        Query query = sessionFactory.getCurrentSession().createQuery("from ICDList order by id ASC");
        return query.list();

    }

    //Get all ICD Lists by pages
    public List<ICDList> getAllICDListsByPage(int page){
        Query query = sessionFactory.getCurrentSession().createQuery("from ICDList order by id ASC");

        query.setFirstResult((page-1)*maxPage);
        query.setMaxResults(maxPage);
        return query.list();

    }

    //DELETE
    public void deleteICDList(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from ICDList where id=:id");
        query.setInteger("id", id);

        ICDList icdList= (ICDList) query.uniqueResult();

        sessionFactory.getCurrentSession().delete(icdList);
    }
}
