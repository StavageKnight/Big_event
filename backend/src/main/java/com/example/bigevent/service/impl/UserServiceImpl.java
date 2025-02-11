package com.example.bigevent.service.impl;

import com.example.bigevent.utils.Md5Util;
import com.example.bigevent.dto.User;
import com.example.bigevent.mapper.UserRepository;
import com.example.bigevent.service.UserService;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Service
@Validated
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        User u = userRepository.findByUsername(username);
        return u;
    }

    @Override
    public void register(String username, String password) {
//        log.info("Registering user " + username + " with password " + password);
        String md5String = Md5Util.getMD5String(password);
        userRepository.addUser(username, md5String);
    }

    @Override
    public void updateUser(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userRepository.updateUser(user);
    }

    @Override
    public void updateAvatar(String avatar, Integer id) {
        userRepository.updateAvatar(avatar, id);
    }

    @Override
    public void updatePassword(String password, String username) {
        String md5String = Md5Util.getMD5String(password);
        userRepository.updatePassword(md5String, username);
    }
}
