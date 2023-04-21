package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.udacity.jwdnd.course1.cloudstorage.model.UserCredential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;

@Controller
public class CredentialController {

    private CredentialService credentialService;
 
    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping("/handleCredential")
    public String handleCredential(Authentication authentication, UserCredential userCredential) {
        String username = authentication.getName();
        
        // if credential cannot be stored properly then return an error
        if(!credentialService.storeCredential(username, userCredential)) {
            return "redirect:/result?error";
        }

        return "redirect:/result?success";
    }

    @GetMapping("/deleteCredential")
    public String deleteCredential(@RequestParam Integer credentialid) {
        credentialService.deleteCredential(credentialid);
        return "redirect:/home";
    }

}
