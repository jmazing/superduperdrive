package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.udacity.jwdnd.course1.cloudstorage.model.UserCredential;

@Controller
public class CredentialController {
 
    @PostMapping("/uploadCredential")
    public String uploadCredential(Authentication authentication, UserCredential userCredential) {
        System.out.println(userCredential);

        return "redirect:/result?success";
    }

}
