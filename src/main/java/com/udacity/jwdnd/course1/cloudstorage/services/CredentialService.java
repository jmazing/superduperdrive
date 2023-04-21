package com.udacity.jwdnd.course1.cloudstorage.services;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
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
        
        String key = RandomStringUtils.random(16, true, true);
        userCredential.setKey(key);
        userCredential.setUserid(userService.getUserId(username));
        String encryptedPassword = encryptionService.encryptValue(userCredential.getPassword(), userCredential.getKey());
        userCredential.setPassword(encryptedPassword);

        int row = credentialMapper.insertCredential(userCredential);
        if(row >= 0) {
            stored = true;
        }
        return stored;
    }

    public List<UserCredential> getCredentials(String username) {
        return credentialMapper.getCredentials(userService.getUserId(username));
    }

    public void deleteCredential(Integer credentialid) {
        credentialMapper.deleteCredential(credentialid);
    }

    public void editCredential(UserCredential userCredential) {
        String key = RandomStringUtils.random(16, true, true);
        userCredential.setKey(key);
        String encryptedPassword = encryptionService.encryptValue(userCredential.getPassword(), userCredential.getKey());
        userCredential.setPassword(encryptedPassword);
        credentialMapper.updateCredential(userCredential);
    }

    public boolean checkIfCredentialURLExists(String username, String credentialURL) {
        List<UserCredential> userCredentials = getCredentials(username);
        for(UserCredential tmpUserCredential: userCredentials) {
            String tmpCredentialURL = tmpUserCredential.getUrl();
            if(credentialURL.equals(tmpCredentialURL)) {
                return true;
            }
        }
        return false;
    }

}
