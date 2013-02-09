package by.cources.photostore.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.cources.photostore.dal.CrudDao;

import by.cources.photostore.exception.DalException;
import by.cources.photostore.model.Picture;
import by.cources.photostore.model.User;

@Service("pictureService")
@Repository
@Transactional
public class PictureServiceImp implements PictureService {

	@Autowired
	private CrudDao crudDao;

	private static final Logger LOGGER = Logger
			.getLogger(PictureServiceImp.class);

	public <T> T merge(T t) throws DalException {

		User user = crudDao.findByName(User.class, SecurityContextHolder
				.getContext().getAuthentication().getName());
		((Picture) t).setCreationTime(new Date());
		LOGGER.info(((Picture) t).getCreationTime());
		user.addPicture((Picture) t);

		return (T) (crudDao.merge(t));
	}

	@Transactional(readOnly = true)
	public <T> List<T> list(Class<T> type) throws DalException {

		User user = crudDao.findByName(User.class, SecurityContextHolder
				.getContext().getAuthentication().getName());
		List list = user.getPictures();

		return list;
	}

	@Override
	public <T, PK extends Serializable> T find(Class<T> type, PK id)
			throws DalException {

		return crudDao.find(type, id);
	}

	public <T, PK extends Serializable> void delete(Class<T> type, PK id)
			throws DalException {

		crudDao.delete(type, id);
	}
}
