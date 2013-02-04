package by.cources.photostore.dal;

import java.util.List;

import by.cources.photostore.exception.DalException;

public interface PictureDao extends CrudDao {
	
	public <T> List<T> picturesList(Class<T> type) throws DalException;
			
			
}
