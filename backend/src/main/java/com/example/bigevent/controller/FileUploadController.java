package com.example.bigevent.controller;

import com.example.bigevent.vo.ResultVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Base64;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://47.93.166.101:10512"})
public class FileUploadController {

    private static final String GITHUB_API_URL = "https://api.github.com/repos/StavageKnight/Blog_picture/contents/Img/";
    private static final String GITHUB_TOKEN = ""; // 替换为你的GitHub Token

    @PostMapping("/upload")
    public ResultVo uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        String url = "";
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + "." + originalFilename.substring(originalFilename.lastIndexOf("."));

        // 获取文件内容并转化为Base64编码
        byte[] fileContent = file.getBytes();
        String encodedContent = Base64.getEncoder().encodeToString(fileContent);

        // 构建GitHub API请求体
        String requestBody = "{\n" +
                "  \"message\": \"Upload " + fileName + "\",\n" +
                "  \"content\": \"" + encodedContent + "\",\n" +
                "  \"branch\": \"main\"\n" +
                "}";

//        log.info(requestBody);
        // 使用RestTemplate向GitHub API发送请求
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "token " + GITHUB_TOKEN);
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(GITHUB_API_URL + fileName, HttpMethod.PUT, entity, String.class);

        if (response.getStatusCodeValue() == 201) {
            https:
//cdn.jsdelivr.net/gh/StavageKnight/Blog_picture@main/Blog/001.png
            url = "https://cdn.jsdelivr.net/gh/StavageKnight/Blog_picture@main/Img/" + fileName;
            return ResultVo.ok(200, url);
        } else {
            return ResultVo.error(500, "File upload failed to GitHub");
        }
    }
}
