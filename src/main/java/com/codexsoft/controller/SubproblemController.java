package com.codexsoft.controller;

import com.codexsoft.form.SubproblemForm;
import com.codexsoft.model.Task;
import com.codexsoft.service.SubproblemService;
import com.codexsoft.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
public class SubproblemController {

    @Autowired
    private SubproblemService subproblemService;
    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "user/task/{taskId}/add_subproblem")
    public ModelAndView createSubproblem(
            ModelAndView mav, @PathVariable("taskId") String taskId) {
        mav.addObject("subproblemForm", new SubproblemForm());
        mav.addObject("taskId", taskId);
        mav.setViewName("user/create_subproblem");
        return mav;
    }

    @RequestMapping(value = "user/task/{taskId}/add_subproblem",
            method = RequestMethod.POST)
    public ModelAndView createSubproblemPost(
            ModelAndView mav,
            @PathVariable("taskId") long taskId,
            @ModelAttribute("subproblemForm") @Valid SubproblemForm subproblemForm,
            BindingResult result) {

        mav.setViewName("redirect:/user/task/{taskId}/add_subproblem");
        if (result.hasErrors()) {
            return mav;
        }

        Task task = taskService.get(taskId);
        subproblemService.addSubproblem(subproblemForm, task);
        mav.setViewName("redirect:/general/task/{taskId}");
        return mav;
    }

    @RequestMapping(value = "user/task/{taskId}/{subproblemId}/delete_subproblem")
    public ModelAndView createSubproblem(
            ModelAndView mav,
            @PathVariable("taskId") long taskId,
            @PathVariable("subproblemId") long subproblemId) {
        subproblemService.remove(subproblemId);
        mav.setViewName("redirect:/general/task/{taskId}");
        return mav;
    }

}
