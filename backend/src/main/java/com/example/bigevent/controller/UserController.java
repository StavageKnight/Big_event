package com.example.bigevent.controller;

import com.example.bigevent.dto.User;
import com.example.bigevent.exception.Code;
import com.example.bigevent.service.UserService;
import com.example.bigevent.utils.JWTUtil;
import com.example.bigevent.utils.Md5Util;
import com.example.bigevent.vo.ResultVo;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://47.93.166.101:10512"})
public class UserController {

    @Autowired
    private UserService userService;
    private final JWTUtil jwtUtil;

    @PostMapping("/user/register")
    public ResultVo register(@RequestBody User user) {
//        log.info("------------------你哈哈哈哈哈哈哈哈: {}", user.getPassword());
        User u = userService.findByUsername(user.getUsername());
        if (u != null) {
            return ResultVo.error(Code.ERROR, "用户存在!!!");
        }
        userService.register(user.getUsername(), user.getPassword());
        return ResultVo.success(user.getUsername(), "注册成功!!!");
    }

    @PostMapping("/user/login")
    public ResultVo login(@RequestBody User user, HttpServletResponse response) {
        log.info(user.getPassword() + "---------------------");
        User u = userService.findByUsername(user.getUsername());
        if (u == null) {
            return ResultVo.error(Code.ERROR, "用户不存在!!");
        }

        if (!Md5Util.getMD5String(user.getPassword()).equals(u.getPassword())) {
            return ResultVo.error(400, "密码错误");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("id", u.getId());
        map.put("username", u.getUsername());
        String token = jwtUtil.encode(map);
        response.setHeader("token", token);
        return ResultVo.success(token, "登录成功");
    }

    @GetMapping("/user/userinfo")
    public ResultVo userinfo(@RequestAttribute String username) {
        User u = userService.findByUsername(username);
//        log.info(u.getUpdateTime().toString());
        return ResultVo.success(u, "查询成功");
    }

    @PutMapping("/user/update")
    public ResultVo updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return ResultVo.ok(200, "更新成功");
    }


    @PatchMapping("/user/updateAvatar")
    public ResultVo updateAvatar(@RequestParam String avatar, @RequestAttribute Integer id) {
        userService.updateAvatar(avatar, id);
        return ResultVo.ok(200, "更新成功");
    }

    @PatchMapping("/user/updatePwd")
    public ResultVo updatePwd(@RequestBody Map<String, String> m, @RequestAttribute String username) {
        String oldPwd = m.get("old_pwd");
        String newPwd = m.get("new_pwd");
        String rePwd = m.get("re_pwd");
        if (!StringUtils.hasLength(oldPwd) && !StringUtils.hasLength(newPwd) && !StringUtils.hasLength(rePwd)) {
            return ResultVo.error(400, "缺少必要的填写");
        }


        User u = userService.findByUsername(username);
        if (!u.getPassword().equals(Md5Util.getMD5String(oldPwd))) {
            return ResultVo.error(400, "原密码错误");
        }

        if (!newPwd.equals(rePwd)) {
            return ResultVo.error(400, "两次新密码不一致");
        }

        userService.updatePassword(newPwd, username);
        return ResultVo.ok(200, "密码更新成功");
    }
}
