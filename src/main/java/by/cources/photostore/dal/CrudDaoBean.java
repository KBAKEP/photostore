package by.cources.photostore.dal;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

import by.cources.photostore.exception.*;
import by.cources.photostore.model.Picture;


/**
 * Hibernate crud dao interface implementation
 * 
 * @author harchevnikov_m Date: 18.09.11 Time: 21:20
 */
@Repository(value = "crudDaoBean")
@Transactional
public class CrudDaoBean implements CrudDao {
	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger LOGGER = Logger.getLogger(CrudDaoBean.class);
	/**
	 * Default constructor
	 */
	public CrudDaoBean() {
	}

	@SuppressWarnings("unchecked")
	public <T> T merge(T t) throws DalException {
		try {
			return (T) currentSession().merge(t);
		} catch (HibernateException e) {
			throw new DalException(e);
		} finally {
			currentSession().flush();
		}

	}

	@SuppressWarnings("unchecked")
	public <T, PK extends Serializable> T find(Class<T> type, PK id)
			throws DalException {
		try {
			return (T) currentSession().get(type, id);
		} catch (HibernateException e) {
			throw new DalException(e);
		}
	}

	public <T> T findByName(Class<T> type, String name) throws DalException {
		try {
			LOGGER.info("test message from DaoBean");
			/*			
			List list = currentSession()
					.createSQLQuery("select * from user where login=" + name)
					.addEntity(type).list();
			LOGGER.info((T) list.get(0).toString());
			return (T) list.get(0);
*/
			Criteria criteria = currentSession().createCriteria(type);
			criteria.add(Restrictions.eq("login", name));     //hardcode
			criteria.setMaxResults(1);
			LOGGER.info((T) criteria.uniqueResult().toString());        
	        return (T) criteria.uniqueResult();
	                
		} catch (HibernateException e) {
			throw new DalException(e);
		}
	}

	public <T, PK extends Serializable> void delete(Class<T> type, PK id)
			throws DalException {
		try {
			Session currentSession = currentSession();
			Object object = currentSession.get(type, id);
			currentSession.delete(object);
		} catch (HibernateException e) {
			throw new DalException(e);
		}

	}

	@SuppressWarnings({ "unchecked" })
	public <T> List<T> list(Class<T> type) throws DalException {
		try {
			return currentSession().createCriteria(type).list();
		} catch (HibernateException e) {
			throw new DalException(e);
		}
	}

	private Session currentSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		// Session currentSession = sessionFactory.openSession();
		return currentSession;
	}
}