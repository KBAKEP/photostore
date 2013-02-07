package by.cources.photostore.dal;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import by.cources.photostore.exception.*;

/**
 * CRUD interface
 * 
 * @author mihm
 */
public interface CrudDao {

	/**
	 * Creates/updates a new object for the given type.
	 * 
	 * @param <T>
	 *            Entity class
	 * @param t
	 *            entity
	 * @return persisted Object
	 */
	<T> T merge(T t) throws DalException;

	/**
	 * Delete object by id
	 * 
	 * @param <T>
	 * @param <PK>
	 * @param type
	 *            , entity class type
	 * @param id
	 */
	<T, PK extends Serializable> void delete(Class<T> type, PK id)
			throws DalException;

	/**
	 * Find object by id
	 * 
	 * @param <T>
	 * @param <PK>
	 * @param type
	 * @param id
	 * @return the object
	 */
	<T, PK extends Serializable> T find(Class<T> type, PK id)
			throws DalException;
	
	/**
	 * Find object by id
	 * 
	 * @param <T>
	 * @param String
	 * @param type
	 * @param name
	 * @return the object
	 */
	<T> T findByName(Class<T> type, String name)
			throws DalException;

	/**
	 * List of objects
	 * 
	 * @param type
	 * @param <T>
	 * @return
	 */
	<T> List<T> list(Class<T> type) throws DalException;
	
	
}
