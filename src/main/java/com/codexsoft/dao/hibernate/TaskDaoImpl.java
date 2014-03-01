package com.codexsoft.dao.hibernate;

import com.codexsoft.dao.TaskDao;
import com.codexsoft.model.Task;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Repository
public class TaskDaoImpl extends GenericDaoImpl<Task, Long> implements TaskDao {

    public TaskDaoImpl() {
        super(Task.class);
    }

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Task> getAll() {
        List<Task> taskList = sessionFactory.getCurrentSession().createCriteria(Task.class).list();
        return new ArrayList<Task>(new HashSet<Task>(taskList));
    }

    @Override
    public List getAllByUser(String username) {
        Criteria crit = getSession().createCriteria(Task.class, "task");
        crit.createAlias("users", "u");  // Create alias for users
        crit.add(Restrictions.like("u.username", username));
        return crit.list();
    }

}
