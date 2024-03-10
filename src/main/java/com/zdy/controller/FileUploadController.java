package com.zdy.controller;

import com.zdy.pojo.Result;
//import com.zdy.utils.AliOssUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

import com.zdy.utils.GCSUtul;


@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file, HttpServletRequest request) throws Exception {
        //把文件的内容存储到本地磁盘上
        String originalFilename = file.getOriginalFilename();
        //保证文件的名字是唯一的,从而防止文件覆盖
        String filename = UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
//        file.transferTo(new File("C:\\Users\\User\\IdeaProjects\\springboot-project\\src\\main\\resources\\static\\files\\"+filename));

        /* 本地
        // 获取静态资源路径
        Resource resource = new ClassPathResource("static");
        File staticDir = resource.getFile();

        // 创建目录
        File filesDir = new File(staticDir, "files");
        if (!filesDir.exists()) {
            filesDir.mkdirs();
        }

        // 创建文件
        File destinationFile = new File(filesDir, filename);
        file.transferTo(destinationFile);
        */


        String url = GCSUtul.uploadFile(filename, file.getInputStream());
        return Result.success(url);

//        String url = AliOssUtil.uploadFile(filename,file.getInputStream());
//        return Result.success(url);

//        return Result.success("C:\\Users\\User\\IdeaProjects\\springboot-project\\src\\main\\resources\\static\\files\\"+filename);
//        return Result.success("http://127.0.0.1:8080/pic/"+filename);
//        return Result.success("https://i.imgur.com/Q1QFKuI.jpeg" );
                }
}
