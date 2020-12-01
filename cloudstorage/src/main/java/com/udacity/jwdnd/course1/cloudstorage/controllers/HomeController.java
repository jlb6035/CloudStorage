package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    NoteService noteService;

    @Autowired
    CredentialsService credentialsService;

    @Autowired
    FileService fileService;

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public ModelAndView home(Authentication authentication){

        User user = userService.getUser(authentication.getPrincipal().toString());

        ModelAndView mav = new ModelAndView("home");
        mav.addObject("note", new Note());
        mav.addObject("notes", noteService.getAllNotes(user.getUserid()));
        mav.addObject("credential", new Credential());
        mav.addObject("credentials", credentialsService.getAllCredentials(user.getUserid()));
        mav.addObject("files", fileService.getAllFiles(user.getUserid()));
        return mav;
    }

    @RequestMapping("/result")
    public ModelAndView result(){
        ModelAndView mav = new ModelAndView("result");
        return mav;
    }
}
