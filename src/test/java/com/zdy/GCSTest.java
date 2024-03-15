package com.zdy;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.google.common.collect.Lists;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GCSTest {
    public static void main(String[] args) throws IOException {
        String filepath = uploadFile("測試", "springboot_project_bucket");
        System.out.println("公開訪問地址是：：：：" + filepath);
    }

    public static String uploadFile(String fileName,String bucketName) throws IOException {
        //要上傳的本地文件的絕對路徑
        String filePath = "C:\\Users\\User\\Desktop\\Imgur Album  Sakura\\1 - d5p4ldT.jpg";

        //讀取本地存儲的服務賬號的json密鑰，拿到該服務賬號的權限
        GoogleCredentials credentials= GoogleCredentials.fromStream(new FileInputStream("C:\\Users\\User\\Downloads\\grand-plasma-416711-f334dbf7ffb0.json"))
                .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));

        //創建服務賬號對應的操作對象
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

        // 將本地指定路徑的文件轉換為字節
        byte[] bytes = Files.readAllBytes(Paths.get(filePath));

        //上傳文件圖片到指定的存儲桶中
        BlobId blobId=BlobId.of(bucketName,fileName);
        BlobInfo blobInfo=BlobInfo.newBuilder(blobId).build();
        Blob blob = storage.create(blobInfo, bytes);

        //修改已經上傳的文件類型為 image/jpg
        blob.toBuilder().setContentType("image/jpg").build().update();

        //返回公開訪問的地址
        return "https://storage.googleapis.com/" + bucketName + "/" + fileName;
    }

}
