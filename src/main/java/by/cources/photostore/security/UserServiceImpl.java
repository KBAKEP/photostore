package by.cources.photostore.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import by.cources.photostore.dal.CrudDao;
import by.cources.photostore.exception.DalException;

import by.cources.photostore.web.controller.PictureController;

@Component
@Transactional
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	@Qualifier("crudDaoBean")
	CrudDao crudDao;

	private static final Logger LOGGER = Logger
			.getLogger(PictureController.class);

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		LOGGER.info("test message");
		try {
			by.cources.photostore.model.User user = crudDao.findByName(
					by.cources.photostore.model.User.class, username);

			List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
			authList.add(new GrantedAuthorityImpl(user.getGrantedAuthority()
					.getRoleName()));

			return new org.springframework.security.core.userdetails.User(
					user.getLogin(), user.getPassword(), true, true, true,
					true, authList);

		} catch (DalException e) {
			// TODO Auto-generated catch block
			LOGGER.info(e.getMessage());
		}
		return null;
	}
}