package com.ziyi.controller;

import com.ziyi.pojo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author zhouYi
 * @description TODO
 * @date 2024/4/21 19:19
 */
@RestController
public class FileController {

    // 上传文件
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String filename = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
        file.transferTo(new File("/Users/ziyi2/Desktop/local-pro/files/" + filename));
        //TODO 替换为OSS服务
        return Result.success("/Users/ziyi2/Desktop/local-pro/files/"+ filename);
    }
}
