package com.codexsoft.service.impl;

import com.codexsoft.dao.TaskDao;
import com.codexsoft.form.TaskForm;
import com.codexsoft.model.Comment;
import com.codexsoft.model.Task;
import com.codexsoft.model.User;
import com.codexsoft.service.TaskService;
import com.codexsoft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    public Task get(Long id) {
        Task task = super.get(id);
        List comments = task.getComments();
        Collections.sort(comments, new Comp());
        return task;

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
        task.setSubscribers(users);
        long id = taskDao.save(task).getId();
        return id;
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
        task.setSubscribers(users);
        taskDao.save(task);
    }

    @Override
    public List getAllByUser(String username) {
        return taskDao.getAllByUser(username);
    }

    class Comp implements Comparator<Comment> {

        @Override
        public int compare(Comment comment1, Comment comment2) {
            return comment2.getLastUpdated().compareTo(comment1.getLastUpdated());
        }
    }

}
