package com.udacity.jwdnd.course1.cloudstorage.controller;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.udacity.jwdnd.course1.cloudstorage.model.UserFile;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;


@Controller
public class FileController {

    FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/uploadFile")
    public String uploadFile(Authentication authentication, @RequestParam("fileUpload") MultipartFile file) throws IOException {
        
        // If the user just clicked the upload button without uploading a file display an error
        if(file.getSize() == 0) {
            return "redirect:/result?error";
        } 
        
        // If filename arleady exists throw an error
        String username = authentication.getName();
        String fileName = file.getOriginalFilename();
        if(fileService.checkIfFileTitleExists(username, fileName)) {
            return "redirect:/result?error";
        }

        // If the file could not be stored then throw an error
        if(!fileService.storeFile(username, file)) {
            return "redirect:/result?error";
        }

        return "redirect:/result?success";
    }

    @GetMapping("/downloadFile")
    public String downloadFile(@RequestParam Integer fileId, HttpServletResponse response) throws IOException {
        UserFile userFile = null;
        userFile = fileService.getFile(fileId);

        if(userFile != null) {
            response.setContentType(userFile.getContenttype());
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename = " + userFile.getFilename();
            response.setHeader(headerKey, headerValue);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(userFile.getFiledata());
            outputStream.close();
        }

        return "redirect:/home";
    }

    @GetMapping("/deleteFile")
    public String downloadFile(@RequestParam Integer fileId) throws IOException {
        fileService.deleteFile(fileId);
        return "redirect:/home";
    }

}
