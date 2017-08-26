/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import data.exceptions.EntityNotFoundException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import logic.EMFProvider;

/**
 *
 * @author Jakub
 */
public abstract class DaoBase<E extends BaseEntity> implements IDaoBase<E>{

    /*
    public DaoBase() {
        this.EntityManagerFactory = new PersistenceManager().getEntityManagerFactory();
    }
    */
    public abstract Class<E> getEntityClass();
    
    protected EMFProvider emfProvider = EMFProvider.getInstance();//new PersistenceManager();
    
    /**
     * Gets an entity manager.
     * 
     * @return the entity manager
     */
    public EntityManager getEM()
    {
        //persistence.getEntityManagerFactory().g
        return emfProvider.getEntityManagerFactory().createEntityManager();
    }
    
    @Override
    public void remove(E entity)
    {
        EntityManager em = getEM();
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
        em.close();
    }
    
    @Override
    public E update(E entity)
    {
        EntityManager em = getEM();
        em.getTransaction().begin();
        E reuslt = em.merge(entity);
        em.getTransaction().commit();
        em.close();
        return reuslt;
    }

    @Override
    public List<E> findAll()
    {   
        final String hql = "FROM " + getEntityClass().getName();
        Query q = getEM().createQuery(hql);
        return q.getResultList();
    }

    @Override
    public E find(Long id)
    {
        if (id == null)
    {
        return null;
    }
        EntityManager em = getEM();
        E result =  em.find(getEntityClass(), id);
        em.close();
        return result;
    }

    @Override
    public E get(long id)
    {
        E entity = getEM().find(getEntityClass(), id);
        if (entity == null)
        {
            //throw new EntityNotFoundException(getEntityClass().getSimpleName(), id);
            throw new EntityNotFoundException();
        }
        return entity;
    }

    @Override
    public void insert(E entity)
    {
        EntityManager em = getEM();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
    }
}
