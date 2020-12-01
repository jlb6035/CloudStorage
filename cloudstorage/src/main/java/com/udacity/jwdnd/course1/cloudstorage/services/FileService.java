package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Files;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class FileService {

    @Autowired
    FileMapper fileMapper;

    @Autowired
    UserService userService;

    public void addFile(MultipartFile file, int userid) throws IOException {
        Files myFile = new Files();
        myFile.setContentType(file.getContentType());
        myFile.setFileData(file.getBytes());
        myFile.setFileName(file.getOriginalFilename());
        myFile.setFileSize(String.valueOf(file.getSize()));
        myFile.setUserid(userid);

        fileMapper.addFile(myFile);
    }

    public boolean isFileNameAvailable(MultipartFile fileName){
        return fileMapper.getFileName(fileName.getOriginalFilename()) == null;
    }


    public List<Files> getAllFiles(int userid){
        return fileMapper.getAllFiles(userid);
    }

    public void deleteFile(int id){
        fileMapper.deleteFile(id);
    }

    public Files findByFileName(String fileName){
        return fileMapper.findByName(fileName);
    }
}
