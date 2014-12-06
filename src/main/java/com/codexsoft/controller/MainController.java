package com.codexsoft.controller;

import com.codexsoft.constants.DateConst;
import com.codexsoft.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/")
    public ModelAndView home(ModelAndView mav) {
        List tasks = taskService.getAllDesc(DateConst.LAST_UPDATED);
        mav.addObject("tasks", tasks);
        mav.setViewName("general/main");
        return mav;
    }

}
