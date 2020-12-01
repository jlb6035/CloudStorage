package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NoteController {

    @Autowired
    NoteService noteService;

    @Autowired
    UserService userService;


    @RequestMapping(value = "/addNotes", method = RequestMethod.POST)
    public String addNotes(@ModelAttribute Note note, Authentication authentication){
        User user = userService.getUser(authentication.getPrincipal().toString());
        note.setUserid(user.getUserid());
        System.out.println(note.getNoteid());
        if (note.getNoteid() > 0){
            noteService.updateNote(note);
        } else {
            noteService.addNote(note);
        }
        return "redirect:/result?success";
    }

    @RequestMapping(value = "/deleteNote")
    public String deleteNote(@RequestParam("id") int noteid){
        noteService.deleteNote(noteid);
        return "redirect:/result?success";
    }
}
