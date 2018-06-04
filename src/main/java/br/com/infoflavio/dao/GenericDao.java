package br.com.infoflavio.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.infoflavio.config.EntityManagerUtil;
import br.com.infoflavio.exception.RestFulBeanException;


public class GenericDao<T extends Serializable> {

    @PersistenceContext(unitName = "app_crud")
    private EntityManager entityManager;
    private final Class<T> persistentClass;
    
    @SuppressWarnings({"unchecked"})
    public GenericDao() {
        this.entityManager = EntityManagerUtil.getEntityManager();
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void save(T entity) throws Exception {
    	checkSession();
        EntityTransaction tx = getEntityManager().getTransaction();        
        try {
            tx.begin();
            getEntityManager().persist(entity);
            tx.commit();
        } catch (Throwable t) {
            t.printStackTrace();
            tx.rollback();
            throw new RestFulBeanException("Erro ao persistir objeto.", t.getClass(), t);  
        } finally {
            close();
        }
    }

    public void update(T entity) throws Exception {
    	checkSession();
        EntityTransaction tx = getEntityManager().getTransaction();
        try {
            tx.begin();
            getEntityManager().merge(entity);
            tx.commit();
        } catch (Throwable t) {
            t.printStackTrace();
            tx.rollback();
            throw new RestFulBeanException("Erro ao atualizar objeto.", t.getClass(), t);  
        } finally {
            close();
        }
    }

    public void delete(T entity) throws Exception {
    	checkSession();
        EntityTransaction tx = getEntityManager().getTransaction();
        try {
            tx.begin();
            getEntityManager().remove(entity);
            tx.commit();
        } catch (Throwable t) {
            t.printStackTrace();
            tx.rollback();
            throw new RestFulBeanException("Erro ao deletar objeto.", t.getClass(), t);
        } finally {
            close();
        }
    }

    @SuppressWarnings("unchecked")
	public List<T> findAll() throws Exception {
    	checkSession();
        Session session = (Session) getEntityManager().getDelegate();
        return session.createCriteria(persistentClass).list();
    }

    @SuppressWarnings("unchecked")
    public T findByName(String nome) {
    	checkSession();
        Session session = (Session) getEntityManager().getDelegate();
        return (T) session.createCriteria(persistentClass).add(Restrictions.eq("nome", nome).ignoreCase()).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public T findById(Integer id) {
    	checkSession();
        Session session = (Session) getEntityManager().getDelegate();
        return (T) session.createCriteria(persistentClass).add(Restrictions.eq("id", id)).uniqueResult();
    }
    
    private void close() {
        if (getEntityManager().isOpen()) {
            getEntityManager().close();
        }
    }
    
    public void checkSession() {
        if (!getEntityManager().isOpen()) {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("app_crud");
            entityManager = factory.createEntityManager();
        }
    }

}