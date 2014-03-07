package com.codexsoft.dao.hibernate;

import com.codexsoft.constants.DateConst;
import com.codexsoft.dao.TaskDao;
import com.codexsoft.model.Task;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
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
    public List getAllByUser(String username) {
        Criteria crit = getSession().createCriteria(Task.class, "task");
        crit.createAlias("subscribers", "s");
        crit.add(Restrictions.like("s.username", username));
        List result = crit.addOrder(Order.desc(DateConst.LAST_UPDATED)).list();
//        Query query = getSession().createSQLQuery("select * from task where id in (select ts.task_id from user u inner join task_subscriber ts where ts.subscriber_id in(select id from user u where username = ?)) order by last_updated desc").addEntity(Task.class);
//        List result = query.setString(0, username).list();
        return result;
    }

}
