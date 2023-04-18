package com.udacity.jwdnd.course1.cloudstorage.controller;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.services.FileService;

@Controller
public class FileUploadController {

    FileService fileService;

    public FileUploadController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/file-upload")
    public String handleFileUpload(Authentication authentication, @RequestParam("fileUpload") MultipartFile fileUpload, Model model, RedirectAttributes ra) throws IOException {
        String fileUploadError = null;

        if(fileUpload.getSize() == 0) {
            System.out.println("IN HERE");
            fileUploadError = "Please choose a file to upload";
            ra.addFlashAttribute("fileUploadError", fileUploadError);
        } else {
            String username = authentication.getName();
            fileService.storeFile(username, fileUpload);
        }
        return "redirect:/home";
    }

}
