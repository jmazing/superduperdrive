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

        // If user has a notetitle of the same name return an error
        String noteTitle = userNote.getNotetitle();
        if(noteService.checkIfNoteExists(noteTitle, username)) {
            return "redirect:/result?error";
        }

        // If note cannot be store then return an error
        if(!noteService.storeNote(username, userNote)) {
            return "redirect:/result?error";
        }

        return "redirect:/result?success";
    }

}
