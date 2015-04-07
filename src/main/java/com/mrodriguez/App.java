package com.mrodriguez;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.mrodriguez.persistence.DaoFactory;
import com.mrodriguez.persistence.dao.UserDao;
import com.mrodriguez.persistence.dao.UserRoleDao;
import com.mrodriguez.persistence.entities.UserEntity;
import com.mrodriguez.persistence.entities.UserRoleEntity;
import com.mrodriguez.repository.HibernateUtil;

/**
 * Hibernate Session Generic Dao
 * A
 * @author Mauricio Rodriguez (MRodriguez08)
 * @see https://github.com/MRodriguez08
 */
public class App 
{
	
	private static final Logger logger = Logger.getLogger(App.class);
	
    public static void main( String[] args )
    {
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	try {
    		logger.info("####################################################");
    		logger.info("Launching hibernate session generic dao application");
    		session.beginTransaction();
    		listUsers();
    		listRoles();
    		session.getTransaction().commit();
		} catch (Exception e) {
			if (session != null)
				session.getTransaction().rollback();
			logger.error("generic dao " , e);
		} finally {
			System.exit(0);
		}
    }
    
    
    private static void listUsers(){
    	logger.info("####################################################");
    	logger.info("Listing users");
    	UserDao userDao = DaoFactory.getUserDao();
    	List<UserEntity> users = userDao.findAll();
    	for (UserEntity e : users){
    		logger.info("------------------------------------");
    		logger.info("email: " + e.getEmail());
    		logger.info("name: " + e.getName());
    		logger.info("surname: " + e.getSurname());
    	}
    }
    
    private static void listRoles(){
    	logger.info("####################################################");
    	logger.info("Listing roles");
    	UserRoleDao userRoleDao = DaoFactory.getUserRoleDao();
    	List<UserRoleEntity> roles = userRoleDao.findAll();
    	for (UserRoleEntity e : roles){
    		logger.info("------------------------------------");
    		logger.info("id: " + e.getId());
    		logger.info("name: " + e.getName());
    	}
    }
    
}
