package com.codexsoft.controller;

import com.codexsoft.form.RegisterForm;
import com.codexsoft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Locale;

@Controller
public class AccountController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "anonymous/login", method = RequestMethod.GET)
    public String login(ModelMap model) {
        return "anonymous/login";
    }

    @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
    public String loginerror(ModelMap model) {
        model.addAttribute("error", "true");
        return "anonymous/login";

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ModelMap model) {
        return "anonymous/login";
    }

    @RequestMapping(value = "anonymous/register")
    public ModelAndView register(ModelAndView mav) {
        mav.addObject("registerForm", new RegisterForm());
        mav.setViewName("anonymous/register");
        return mav;
    }

    @RequestMapping(value = "anonymous/register", method = RequestMethod.POST)
    public ModelAndView registerPost(
            ModelAndView mav,
            Locale locale,
            @ModelAttribute("registerForm") @Valid RegisterForm r,
            BindingResult result) {

        if (!r.getPassword().equals(r.getConfirmPassword())) {

            result.addError(new FieldError("registerForm", "password",
                    messageSource.getMessage("validation.different_pass", null, locale)));
        }

        if ((userService.getUserByName(r.getUsername())) != null) {
            result.addError(new FieldError("registerForm", "username",
                    messageSource.getMessage("validation.duplicate_name", null, locale)));
        }

        if (result.hasErrors()) {
            mav.setViewName("anonymous/register");
            return mav;
        }

        userService.addUser(r);
        mav.setViewName("redirect:/anonymous/login");
        return mav;
    }

}