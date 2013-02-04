package by.cources.photostore.service;

import java.io.Serializable;
import java.util.List;

import by.cources.photostore.exception.DalException;


public interface PictureService {

	<T> T merge(T t) throws DalException;

	<T, PK extends Serializable> T find(Class<T> type, PK id)
			throws DalException;

	<T> List<T> list(Class<T> type) throws DalException;
	
	<T, PK extends Serializable> void delete(Class<T> type, PK id)
			throws DalException;
}
