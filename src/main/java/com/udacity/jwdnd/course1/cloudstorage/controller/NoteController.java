package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.udacity.jwdnd.course1.cloudstorage.model.UserNote;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;

@Controller
public class NoteController {

    private NoteService noteService;
    
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    /*
     * This method handles the action of uploading and editing a note
     */
    @PostMapping("/handleNote")
    public String handleNote(Authentication authentication, UserNote userNote) {
        String username = authentication.getName();
        Integer noteid = userNote.getNoteid();

        // handle editing note
        if(noteid > 0) {
            noteService.editNote(userNote);
        } else { // handle adding a note

            // If adding a note with the same title throw an error
            String noteTitle = userNote.getNotetitle();
            if(noteService.checkIfNoteTitleExists(username, noteTitle)) {
                return "redirect:/result?error";
            }

            // If note cannot be stored then return an error
            if(!noteService.storeNote(username, userNote)) {
                return "redirect:/result?error";
            }
        }
        return "redirect:/result?success";
    }

    @GetMapping("/deleteNote")
    public String deleteNote(@RequestParam Integer noteid) {
        noteService.deleteNote(noteid);
        return "redirect:/result?success";
    }

}
