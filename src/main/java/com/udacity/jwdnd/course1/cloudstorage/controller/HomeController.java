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
        System.out.println(fileService.getFiles(authentication.getName()));
        model.addAttribute("userFiles", fileService.getFiles(authentication.getName()));
        System.out.println("flashAttribute is: " + flashAttribute.isEmpty());
        model.addAttribute("fileUploadError", flashAttribute);
        return "home";
    }

}
