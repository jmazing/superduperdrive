package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;

@Controller
public class HomeController {

    private FileService fileService;
    private NoteService noteService;
    private CredentialService credentialService;
    private EncryptionService encryptionService;

    public HomeController(FileService fileService, NoteService noteService, CredentialService credentialService, EncryptionService encryptionService) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }
    
    @GetMapping("/home")
    public String getHomePage(Authentication authentication, Model model) {
        String username = authentication.getName();
        model.addAttribute("userFiles", fileService.getFiles(username));
        model.addAttribute("userNotes", noteService.getNotes(username));
        model.addAttribute("userTableCredentials", credentialService.getCredentials(username));
        model.addAttribute("encryptionService", encryptionService);
        return "home";
    }
}
