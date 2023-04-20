package com.udacity.jwdnd.course1.cloudstorage.services;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.UserFile;

@Service
public class FileService {
    
    private UserService userService;
    private FileMapper fileMapper;

    public FileService(UserService userService, FileMapper fileMapper) {
        this.userService = userService;
        this.fileMapper = fileMapper;
    }

    public boolean storeFile(String username, MultipartFile multipartFile) throws IOException {
        boolean stored = false;
        UserFile userFile = new UserFile(null, multipartFile.getOriginalFilename(), multipartFile.getContentType(), 
                        String.valueOf(multipartFile.getSize()), userService.getUserId(username), multipartFile.getBytes());
        
        int row = fileMapper.insertFile(userFile);
        if(row >= 0) {
            stored = true;
        }
        return stored;
    }

    public List<UserFile> getFiles(String username) {
        return fileMapper.getFiles(userService.getUserId(username));
    }

    public UserFile getFile(Integer fileId) {
        return fileMapper.getFile(fileId);
    }

    public void deleteFile(Integer fileId) {
        fileMapper.deleteFile(fileId);
    }

    public boolean checkIfFileExists(String username, String filename) {
        List<UserFile> userFiles = getFiles(username);
        for(UserFile userFile: userFiles) {
            String tmpFileName = userFile.getFilename();
            if(filename.equals(tmpFileName)) {
                return true;
            }
        }
        return false;
    }

}
