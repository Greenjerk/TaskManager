package com.codexsoft.dao.hibernate;

import com.codexsoft.dao.UserDao;
import com.codexsoft.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {
	
	@Autowired
    private SessionFactory sessionFactory;

    public UserDaoImpl() {
        super(User.class);
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public List<User> getSimpleUsers() {
        return sessionFactory.getCurrentSession().createCriteria(User.class)
                .add(Restrictions.eq("authority", "ROLE_USER")).list();
    }


    @Transactional
    public User getUserByName(String username) {
        User user = (User) sessionFactory.getCurrentSession().createCriteria(User.class)
                .add(Restrictions.eq("username", username)).uniqueResult();
        return  user;
    }
}
