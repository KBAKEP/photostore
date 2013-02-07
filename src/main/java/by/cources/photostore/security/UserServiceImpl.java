package by.cources.photostore.security;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import by.cources.photostore.dal.CrudDao;
import by.cources.photostore.exception.DalException;
import by.cources.photostore.model.User;
import by.cources.photostore.web.controller.PictureController;

@Component
@Transactional
public class UserServiceImpl implements UserDetailsService {
	
    @Autowired
    CrudDao crudDao;
    
    private static final Logger LOGGER = Logger
			.getLogger(PictureController.class);

        
    @Override
    public UserDetails loadUserByUsername(String username)throws 
            UsernameNotFoundException, DataAccessException {         
        try {
			return crudDao.findByName(User.class, username);
		} catch (DalException e) {
			// TODO Auto-generated catch block
			LOGGER.info(e.getMessage());
		}
        return null;
    }
}