package com.udacity.jwdnd.course1.cloudstorage.services;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.UserCredential;

@Service
public class CredentialService {
    
    private CredentialMapper credentialMapper;
    private UserService userService;
    private EncryptionService encryptionService;
    
    public CredentialService(CredentialMapper credentialMapper, UserService userService, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.userService = userService;
        this.encryptionService = encryptionService;
    }

    public boolean storeCredential(String username, UserCredential userCredential) {
        boolean stored = false;
        userCredential.setUserid(userService.getUserId(username));
        encryptionService.encryptValue(userCredential.getPassword(), userCredential.getKey());
        
        int row = credentialMapper.insertCredential(userCredential);
        if(row >= 0) {
            stored = true;
        }
        return stored;
    }

}
