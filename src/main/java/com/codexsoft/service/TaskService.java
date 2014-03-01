package com.codexsoft.service;

import com.codexsoft.form.TaskForm;
import com.codexsoft.model.Task;
import com.codexsoft.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TaskService extends GenericManager<Task, Long> {

    public long addTask(TaskForm taskForm, User author);

    public void editTask(TaskForm taskForm, User author, long id);

    public List<Task> getAllByUser(String username);



}
