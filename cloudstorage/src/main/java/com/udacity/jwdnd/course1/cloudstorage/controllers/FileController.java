package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Files;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class FileController {

    @Autowired
    FileService fileService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/addFile", method = RequestMethod.POST)
    public String addFile(MultipartFile file, Authentication authentication, RedirectAttributes redirectAttributes) throws IOException {

        if(!fileService.isFileNameAvailable(file)){
            redirectAttributes.addFlashAttribute("errorMessage", "That filename already exist!");
            return "redirect:/result?error";
        }

        User user = userService.getUser(authentication.getPrincipal().toString());
        fileService.addFile(file, user.getUserid());
        return "redirect:/result?success";
    }

    @RequestMapping("/deleteFile")
    public String deleteFile(@RequestParam("id") int fileid){
        fileService.deleteFile(fileid);
        return "redirect:/result?success";
    }

    @RequestMapping("/download")
    public ResponseEntity download(@RequestParam String fileName){
        Files files = fileService.findByFileName(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + files.getFileName() + "\"")
                .body(new ByteArrayResource(files.getFileData()));

    }
}
