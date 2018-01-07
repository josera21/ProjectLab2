/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.criteria.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;

/**
 *
 * @author josera
 */
public interface IHibernateDAO {
  public void setSessionFactory(SessionFactory sessionFactory);
  public abstract void refreshEntity(Object paramObject);
  
  public abstract void initialiceCollection1(Set paramSet);
  
  public void initialiceCollection(List collections);
  
  public abstract void initialiceCollection(Object paramObject, Set paramSet);
  
  public abstract Object load(Class paramClass, Serializable paramSerializable);
  
  public abstract Object findByKey(Class paramClass, Serializable paramSerializable);
  
  public abstract List findByQuery(String paramString, String[] paramArrayOfString, Object[] paramArrayOfObject);
  
  public abstract void flushSession()
    throws DAOException;
  
  public abstract void clearSession()
    throws DAOException;
  
  public abstract void update(Object paramObject)
    throws DAOException;
  
  public abstract void delete(Object paramObject)
    throws DAOException;
  
  public abstract List queryByExample(Object paramObject)
    throws DAOException;
  
  public abstract List findAll(Class paramClass)
    throws DAOException;
  
  public abstract List findAll(Class paramClass, List paramList)
    throws DAOException;
  
  public abstract List findByProperty(Class paramClass, Criterion paramCriterion)
    throws DAOException;
  
  public abstract List findByCriterions(Class paramClass, List paramList)
    throws DAOException;
  
  public abstract List findByCriterions(Class paramClass, List paramList1, List paramList2)
    throws DAOException;
  
  public abstract List findBySQLQuery(String paramString1, String paramString2, Class paramClass)
    throws DAOException;
  
  public abstract List findBySQLQuery(String paramString)
    throws DAOException;
  
  public abstract List findByHQLQuery(String paramString)
    throws DAOException;
  
  public abstract List findByNamedParam(String paramString1, String paramString2, Object paramObject)
    throws DAOException;
  
  public abstract List findByNamedParam(String paramString1, String paramString2, Object paramObject, int paramInt1, int paramInt2)
    throws DAOException;
  
  public abstract List findByNamedParam(String paramString, String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DAOException;
  
  public abstract List findByNamedParam(String paramString, String[] paramArrayOfString, Object[] paramArrayOfObject, int paramInt1, int paramInt2)
    throws DAOException;
  
  public abstract List findByValueBean(String paramString, Object paramObject)
    throws DAOException;
  
  public abstract List findByNamedQuery(String paramString)
    throws DAOException;
  
  public abstract List findByNamedQuery(String paramString, Object paramObject)
    throws DAOException;
  
  public abstract List findByNamedQuery(String paramString, Object[] paramArrayOfObject)
    throws DAOException;
  
  public abstract List findByNamedQueryAndNamedParam(String paramString1, String paramString2, Object paramObject)
    throws DAOException;
  
  public abstract List findByNamedQueryAndNamedParam(String paramString, String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DAOException;
  
  public abstract List findByNamedQueryAndValueBean(String paramString, Object paramObject)
    throws DAOException;
  
  public abstract boolean isCacheQueries();
  
  public abstract String getQueryCacheRegion();
  
  public abstract Object get(Class paramClass, Serializable paramSerializable);
  
  public abstract Serializable save(Object paramObject)
    throws DAOException;
  
  public abstract void saveOrUpdate(Object paramObject)
    throws DAOException;
  
  public abstract List find(String paramString, int paramInt1, int paramInt2)
    throws DAOException;
  
  public abstract List find(String paramString)
    throws DAOException;
  
  public abstract List find(String paramString, Object paramObject)
    throws DAOException;
  
  public abstract List find(String paramString, Object[] paramArrayOfObject)
    throws DAOException;
  
  public abstract List find(String paramString, Object[] paramArrayOfObject, int paramInt1, int paramInt2)
    throws DAOException;
  
  public abstract List loadAll(Class paramClass)
    throws DAOException;
  
  public abstract List loadAll(Class paramClass, int paramInt1, int paramInt2)
    throws DAOException;
  
  public abstract void executeUpdateBySQL(String paramString, Object[] paramArrayOfObject)
    throws DAOException;
  
  public abstract Session getCurrentSession();
  
  public abstract void evictEntity(Object paramObject);
  
  public abstract void mergeEntity(Object paramObject);
  
  public abstract List findByProperty(Class paramClass, Criterion paramCriterion, Order paramOrder);
}
