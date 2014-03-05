package com.codexsoft.dao;

import com.codexsoft.model.Task;

import java.util.List;

public interface TaskDao extends GenericDao<Task, Long> {

    public List getAllByUser(String username);

}
