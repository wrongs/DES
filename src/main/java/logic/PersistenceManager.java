/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Jakub
 */
public class PersistenceManager {
    
    private EMFProvider emfProvider = EMFProvider.getInstance();
    
    public EntityManager getEntityManager()
    {
        return emfProvider.getEntityManagerFactory().createEntityManager();
    }
      
    public <T> List<T> getHqlResult(Class<T> type, String hql, Serializable... parameters)
    {
        EntityManager em = getEntityManager();
       // try
        //{
            Query q = em.createQuery(hql);
            bindParams(q, parameters);
            List<T> r = q.getResultList();
            em.close();
            return r;
        //}
        //catch(RuntimeException e)
        //{
                    
        //}
       
        //EntityManager em = getEntityManager();
        // em.createQuery(hql, type);
    }
    
    public <T> T getHqlSingleResult(Class<T> type, String hql, Serializable... parameters)
    {
        EntityManager em = getEntityManager();
        Query q = em.createQuery(hql, type);
        bindParams(q, parameters);
        q.setMaxResults(1);
        List<T> r = q.getResultList();
        em.close();
        if(r.isEmpty())
        {
            return null;
        }
        return r.get(0);
    }
    
    public <T> List<T> getSqlResult(Class<T> type, String sql, Serializable... parameters)
    {
        EntityManager em = getEntityManager();
        Query q = em.createNativeQuery(sql, type);
        bindParams(q, parameters);
        List<T> r = q.getResultList();
        em.close();
        return r;
    }
        
    public <T> T getSqlSingleResult(Class<T> type, String sql, Serializable... parameters)
    {
        EntityManager em = getEntityManager();
        Query q = em.createNativeQuery(sql, type);
        bindParams(q, parameters);
        q.setMaxResults(1);
        List<T> r = q.getResultList();
        em.close();
        if(r.isEmpty())
        {
            return null;
        }
        return r.get(0);    
    }    
    
    public static void bindParams(Query q, Serializable[] params)
    {
        int i = 1;
        for (Serializable s : params)
        {
            if(s != null && s.getClass().isArray())
            {
                q.setParameter(i, convert((Serializable[])s));
            }
            else
            {
                q.setParameter(i, s);
            }
            i++;
        }
    }
    
    protected static List<Serializable> convert(Serializable[] s)
    {
        //return new ArrayList<>(Arrays.asList(s));
        return Arrays.asList(s);
    }
}
