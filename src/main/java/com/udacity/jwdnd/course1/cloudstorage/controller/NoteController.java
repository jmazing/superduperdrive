package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.udacity.jwdnd.course1.cloudstorage.model.UserNote;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;

@Controller
public class NoteController {

    private NoteService noteService;
    
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/uploadNote")
    public String uploadNote(Authentication authentication, UserNote userNote) {
        String username = authentication.getName();
        noteService.storeNote(username, userNote);
        return "redirect:/result?success";
    }

}
