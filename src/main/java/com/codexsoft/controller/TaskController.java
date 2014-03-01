package com.codexsoft.controller;

import com.codexsoft.form.CommentForm;
import com.codexsoft.form.TaskForm;
import com.codexsoft.model.Task;
import com.codexsoft.model.User;
import com.codexsoft.service.CommentService;
import com.codexsoft.service.TaskService;
import com.codexsoft.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;


@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "general/task/{taskId}")
    public ModelAndView task(ModelAndView mav,
                                 @PathVariable long taskId) {
        Task task = taskService.get(taskId);
        mav.addObject("task", task);
        mav.addObject("commentForm", new CommentForm());
        mav.setViewName("general/task");
        return mav;
    }

    @RequestMapping(value = "general/task/{taskId}/add_comment", method = RequestMethod.POST)
    public ModelAndView addCommentPost(
            ModelAndView mav,
            @ModelAttribute("commentForm") @Valid CommentForm commentForm,
            BindingResult result,
            Principal principal,
            @PathVariable("taskId") long taskId) {

        mav.setViewName("redirect:/general/task/{taskId}");
        if (result.hasErrors()) {
            return mav;
        }

        Task task = taskService.get(taskId);
        User author = userService.getUserByName(principal.getName());
        commentService.addComment(task, commentForm, author);
        return mav;
    }

    @RequestMapping(value = "general/task/{taskId}/remove_comment",
            method = RequestMethod.POST)
    public ModelAndView deleteComment(ModelAndView mav,
                                      @RequestParam long commentId,
                                      @PathVariable("taskId") String taskId) {
        commentService.remove(commentId);
        mav.setViewName("redirect:/general/task/{taskId}");
        return mav;
    }

    @RequestMapping(value = "user/task/new")
    public ModelAndView createTask(ModelAndView mav) {
        mav.addObject("taskForm", new TaskForm());
        mav.setViewName("user/create_task");
        return mav;
    }

    @RequestMapping(value = "user/task/new", method = RequestMethod.POST)
    public ModelAndView createTaskPost(
            ModelAndView mav,
            Principal principal,
            @ModelAttribute("taskForm") @Valid TaskForm taskForm,
            BindingResult result) throws IOException {

        mav.setViewName("redirect:/user/task/new");

        if (result.hasErrors()) {
            return mav;
        }

        User author = userService.getUserByName(principal.getName());
        long id = taskService.addTask(taskForm, author);

        mav.setViewName("redirect:/general/task/" + id);
        return mav;
    }

    @RequestMapping(value = "user/task/{taskId}/edit")
    public ModelAndView editTask(ModelAndView mav,
                                 @PathVariable("taskId") long taskId) {
        Task task = taskService.get(taskId);
        TaskForm form = new TaskForm();
        form.setDescription(task.getDescription());
        form.setTitle(task.getTitle());
        String users = StringUtils.join(task.getUsers(), ',');
        mav.addObject("taskForm", form);
        mav.addObject("users", users);
        mav.addObject("taskId", taskId);
        mav.setViewName("user/edit_task");
        return mav;
    }

    @RequestMapping(value = "user/task/{taskId}/edit", method = RequestMethod.POST)
    public ModelAndView editTaskPost(
            ModelAndView mav,
            Principal principal,
            @PathVariable("taskId") long taskId,
            @ModelAttribute("taskForm") @Valid TaskForm taskForm,
            BindingResult result) throws IOException {

        if (result.hasErrors()) {
            mav.setViewName("redirect:/user/task/{taskId}/edit");
            return mav;
        }

        User author = userService.getUserByName(principal.getName());
        taskService.editTask(taskForm, author, taskId);
        mav.setViewName("redirect:/general/task/{taskId}");
        return mav;
    }

    @RequestMapping(value = "user/task/{taskId}/delete")
    public ModelAndView deleteTask(ModelAndView mav,
                                 @PathVariable("taskId") long taskId) {
        taskService.remove(taskId);
        mav.setViewName("redirect:/");
        return mav;
    }

}
