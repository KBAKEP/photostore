package by.cources.photostore.dal;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import by.cources.photostore.exception.DalException;
import by.cources.photostore.model.Picture;
import by.cources.photostore.model.User;

@Repository
@Transactional
public class PictureDaoBean extends CrudDaoBean implements PictureDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional(readOnly = true)
	@Override
	public <T> List<T> picturesList(Class<T> type) throws DalException {
		try {
			User user = find(User.class, 2L);
			List list = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"select * from picture where user_id="
									+ user.getId()).addEntity(Picture.class)
					.list();

			return list;
		} catch (DalException e) {
			e.printStackTrace();
		} finally {
			return null;
		}
	}
}
