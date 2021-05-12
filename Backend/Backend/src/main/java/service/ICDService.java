package service;

import model.ICD;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ICDService {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    int maxPage = 5;

    public void addICD(ICD icd){
        this.sessionFactory.getCurrentSession().saveOrUpdate(icd);
    }

    //Get all icds
    public List<ICD> getAllICDs(){
        Query query = sessionFactory.getCurrentSession().createQuery("from ICD order by code ASC");
        return query.list();

    }

    //Get all icds by page
    public List<ICD> getAllICDsByPage(int page){
        Query query = sessionFactory.getCurrentSession().createQuery("from ICD order by code ASC");

        query.setFirstResult((page-1)*maxPage);
        query.setMaxResults(maxPage);

        return query.list();

    }

    //DELETE
    public void deleteICD(String id){
        Query query = sessionFactory.getCurrentSession().createQuery("from ICD where code=:id");
        query.setString("id", id);

        ICD icd= (ICD) query.uniqueResult();

        sessionFactory.getCurrentSession().delete(icd);
    }

    //Find icds by description
    public List<ICD> findICDsbyDescription(String description){
        Query query = sessionFactory.getCurrentSession().createQuery("from ICD i where i.description like :description order by code ASC");

        query.setString("description", "%"+description+"%");

        return query.list();
    }

    //Find icds by description by page
    public List<ICD> findICDsbyDescriptionPaging(String description, int page){
        Query query = sessionFactory.getCurrentSession().createQuery("from ICD i where i.description like :description order by code ASC");

        query.setString("description", "%"+description+"%");

        query.setFirstResult((page-1)*maxPage);
        query.setMaxResults(maxPage);

        return query.list();
    }


    //Find icd by code
    public List<ICD> findICDsByCode(String code){
        Query query = sessionFactory.getCurrentSession().createQuery("from ICD i where i.code like :code order by code ASC");

        query.setString("code", code);

        return query.list();
    }
}
