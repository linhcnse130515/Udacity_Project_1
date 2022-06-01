package com.udacity.jwdnd.course1.cloudstorage.services.impl;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {

    private final UserService userService;

    private final FileMapper fileMapper;

    public FileServiceImpl(UserService userService, FileMapper fileMapper) {
        this.userService = userService;
        this.fileMapper = fileMapper;
    }

    @Override
    public String uploadFile(MultipartFile fileUpload, String userName) throws IOException {
        Integer userId = userService.getUser(userName).getUserId();

        if (fileUpload.isEmpty()) {
            return "There is no file to upload.";
        } else if (fileUpload.getSize() > 1048576) {
            return "Too large file size to upload. Maximum 1MB allowed";
        } else if (fileMapper.getFile(userId, fileUpload.getOriginalFilename()) != null) {
            return "Filename already exist.";
        } else {
            File file = new File(null, fileUpload.getOriginalFilename(), fileUpload.getContentType(), Long.toString(fileUpload.getSize()), userId, fileUpload.getBytes());
            int result = fileMapper.insertFile(file);
            if (result < 0) {
                return "There was an error upload your file. Please try again.";
            }
        }
        return null;
    }

    @Override
    public File getFile(String fileName, String userName) {
        Integer userId = userService.getUser(userName).getUserId();
        return fileMapper.getFile(userId, fileName);
    }

    @Override
    public String deleteFile(Integer fileId) {
        int result = fileMapper.deleteFile(fileId);
        if (result < 0) {
            return "There was an error delete your file. Please try again.";
        }
        return null;
    }
}
