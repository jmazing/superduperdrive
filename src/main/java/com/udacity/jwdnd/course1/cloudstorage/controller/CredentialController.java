package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.udacity.jwdnd.course1.cloudstorage.model.UserCredential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;

@Controller
public class CredentialController {

    private CredentialService credentialService;
 
    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping("/uploadCredential")
    public String uploadCredential(Authentication authentication, UserCredential userCredential) {
        String username = authentication.getName();
        String key = RandomStringUtils.random(16, true, true);
        userCredential.setKey(key);

        // check error if credential is the same
        
        
        // if credential cannot be stored properly then return an error
        if(!credentialService.storeCredential(username, userCredential)) {
            return "redirect:/result?error";
        }

        return "redirect:/result?success";
    }

}
