package com.example.bigevent.service;

import com.example.bigevent.dto.User;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;

@Validated
public interface UserService {
    User findByUsername(String username);

    void register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password);

    void updateUser(User user);

    void updateAvatar(String avatar,Integer id);

    void updatePassword(String password,String username);
}
