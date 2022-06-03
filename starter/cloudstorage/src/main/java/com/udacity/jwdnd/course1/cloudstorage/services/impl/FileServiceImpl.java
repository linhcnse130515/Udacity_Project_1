package com.udacity.jwdnd.course1.cloudstorage.services.impl;

import com.udacity.jwdnd.course1.cloudstorage.constants.CommonConstant;
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
            return CommonConstant.NO_FILE_UPLOAD;
        } else if (fileUpload.getSize() > CommonConstant.FILE_SIZE_1_MB) {
            return CommonConstant.SIZE_TOO_LARGE;
        } else if (fileMapper.getFile(userId, fileUpload.getOriginalFilename()) != null) {
            return CommonConstant.FILE_EXIST;
        } else {
            File file = new File(null, fileUpload.getOriginalFilename(), fileUpload.getContentType(), Long.toString(fileUpload.getSize()), userId, fileUpload.getBytes());
            int result = fileMapper.insertFile(file);
            if (result < 0) {
                return CommonConstant.UPLOAD_FILE_ERROR;
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
            return CommonConstant.DELETE_FILE_ERROR;
        }
        return null;
    }
}
