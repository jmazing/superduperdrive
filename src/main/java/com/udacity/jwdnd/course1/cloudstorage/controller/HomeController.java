package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.udacity.jwdnd.course1.cloudstorage.services.FileService;

@Controller
public class HomeController {

    private FileService fileService;

    public HomeController(FileService fileService) {
        this.fileService = fileService;
    }
    
    @GetMapping("/home")
    public String getHomePage(Authentication authentication, Model model, @ModelAttribute("fileUploadError") String flashAttribute) {
        model.addAttribute("userFiles", fileService.getFiles(authentication.getName()));
        return "home";
    }

}
