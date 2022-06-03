package com.udacity.jwdnd.course1.cloudstorage.services.impl;

import com.udacity.jwdnd.course1.cloudstorage.constants.CommonConstant;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.HashService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final HashService hashService;

    public UserServiceImpl(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public int createUser(User user) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String passwordHashed = hashService.getHashedValue(user.getPassword(), encodedSalt);
        User newUser = new User(null, user.getUsername(), encodedSalt, passwordHashed, user.getFirstName(), user.getLastName());
        return userMapper.insertUser(newUser);
    }

    @Override
    public User getUser(String username) {
        return userMapper.getUser(username);
    }

    @Override
    public String signupUser(User user) {
        if (userMapper.getUser(user.getUsername()) != null) {
            return CommonConstant.USER_EXIST;
        } else {
            int result = this.createUser(user);
            if (result < 0) {
                return CommonConstant.SIGNUP_ERROR;
            }
        }
        return null;
    }
}
