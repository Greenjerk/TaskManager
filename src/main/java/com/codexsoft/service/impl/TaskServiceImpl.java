package com.codexsoft.service.impl;

import com.codexsoft.dao.TaskDao;
import com.codexsoft.form.TaskForm;
import com.codexsoft.model.Task;
import com.codexsoft.model.User;
import com.codexsoft.service.TaskService;
import com.codexsoft.service.UserService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class TaskServiceImpl extends GenericManagerImpl<Task, Long>
        implements TaskService {

    private TaskDao taskDao;

    @Autowired
    private UserService userService;

    @Autowired
    public TaskServiceImpl(TaskDao taskDao) {
        super(taskDao);
        this.taskDao = taskDao;
    }

    @Override
    public long addTask(TaskForm taskForm, User author){
        Task task = new Task();
        task.setAuthor(author);
        task.setDescription(taskForm.getDescription());
        task.setTitle(taskForm.getTitle());
        Set<User> users = new HashSet<User>();
        users.add(author);
        Set<User> inputUsers = userService.getUserSet(taskForm.getUsers());
        if(!inputUsers.isEmpty()){
           users.addAll(inputUsers);
        }
        task.setUsers(users);
        return taskDao.save(task).getId();
    }

    @Override
    public void editTask(TaskForm taskForm, User author, long id) {
        Task task = new Task();
        task.setId(id);
        task.setAuthor(author);
        task.setDescription(taskForm.getDescription());
        task.setTitle(taskForm.getTitle());
        Set<User> users = new HashSet<User>();
        users.add(author);
        Set<User> inputUsers = userService.getUserSet(taskForm.getUsers());
        if(!inputUsers.isEmpty()){
            users.addAll(inputUsers);
        }
        task.setUsers(users);
        taskDao.save(task);
    }

    @Override
    public List<Task> getAllByUser(String username) {
        return taskDao.getAllByUser(username);
    }

}
