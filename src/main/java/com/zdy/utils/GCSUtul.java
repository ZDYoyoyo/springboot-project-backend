package com.zdy.utils;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.*;
import com.google.common.collect.Lists;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GCSUtul {
    private static final String BUCKET_NAME = "springboot_project_bucket";

    public static String uploadFile(String fileName, InputStream in) throws IOException {
        //要上传的本地文件的绝对路径
//        String filePath = "C:\\Users\\User\\Desktop\\Imgur Album  Sakura\\1 - d5p4ldT.jpg";

        //读取本地存储的服务账号的json密钥，拿到该服务账号的权限
//        GoogleCredentials credentials= GoogleCredentials.fromStream(new FileInputStream("C:\\Users\\User\\Downloads\\grand-plasma-416711-f334dbf7ffb0.json"))
//                .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
        //直接將密鑰寫死
        GoogleCredentials credentials = ServiceAccountCredentials.fromStream(
                new ByteArrayInputStream(
                        // 將JSON內容作為字節數組直接寫在程式碼中
                        ("{" +
                                "  \"type\": \"service_account\"," +
                                "  \"project_id\": \"grand-plasma-416711\"," +
                                "  \"private_key_id\": \"f334dbf7ffb0b64364295309911d97112b98201e\"," +
                                "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC5qu0Ycx47TXc9\\nTVU2U8/Nv4iQj2Pkc32/3f6s53e4gBk2gk2jlzE5Uqs7uzKFz+0kYzesD/3IjMEm\\nwdXIrogLh2Zbw5ojT+het6PYH5cLBDt+R5xSGdhh0rnQBPQkXTfICG2g69cOvNwQ\\n3e4IFcV5CO4W6jpJhdgCMQCBTzLggOo4L5HRxoU13BYMQJcnZRaDmTsmwNEKLJPC\\n1Y/VwKg5KUZAKEmYd/uaaqdQUwA14hniNfvOWmb99+AnSStCoh/QueR7pY+odpD5\\n+mn7yFWJ3rqwmk852yiEBzW+ORtAz3pbi3DUFla2KfJj7MMNF8Z/Dzq7rV/7WCio\\n4WFTIGjjAgMBAAECggEAR9+tLNvThi10zVxUclV7ijBtE1kO2G8E7PNpFVH9fITK\\nPdmvaHas7X3dH6BV7IsTSBTIhmkiWzJxyykaMzetHplpvHg6ARV75L6+RHnmJLOc\\nvEPIddffde73U1m3kSQs5ditZ7AkbmYZ4sWm0aZr7XDRijleGvwfJKoHggdAsLtO\\nG6+HuH03tj1o7uF5WpzT/a20e/7Wv9SR3t2ahBotzw9HO8XYH/7oMfv+E7ImbJJU\\n2SZ3zZhzY3wCNDwVwRgDdgmcE0saoRryC95eBSSTcE4GsMnr4tlprqF1MbATgmXo\\nMUNzMQrWML8aQkn/vYAi31iYmPXc6Y9fVk3X8zuLEQKBgQDeiQMhqjYMYF1AThFd\\nzpW1JbhEfm9dVtRbsew9VTrmcbar7yWyHi8/EI9hHWD2BZJAef1wws8P9KufJ1I4\\nWhlNWVWJIuZyshiTms9DBuzTujQpxd/ky4U6Mrin7eE0lmFWpIyXfyYVzZfrQFTw\\naSgoGI6Zl2zID+NhhU67LRJbzQKBgQDVlp3XyRDiOe0x2PYB8sWBbgG3VOAQ2IFZ\\n/x3mnZ3vYFm8iInJkb6dFka4C4JGQt8ATT7PVokXBZcwtgmd31/eX3I4Yi3zH9gT\\nOEuQgnvh0zQST7BrT6rJunr5jOJ0MUmlTrO6wIuEIvt3Eh0KFWBeNXeb5iEsIx0F\\nPXlHTkgHbwKBgAFovrNU35i+d3izxdphN4N5JKySXGQr65ZVDxjoVEInUC/QiN2S\\n4Vu14CKOTBHiwSVVYGx5X1RLiVatqhCfSW+69Ba1ON5bEFrG7S8QeSlyEWh+T0ek\\nnkhbD95IXFe36A/jZNyEupoCvkaIFsGa+N0gIq8ITXEK4La6YGBBDc+1AoGBAI1V\\n0HclFuYMXNf6cLfTVTyTddFB+c9L5TSPa9LaA9OOF3N5imv25IKAZiWNgBRMVdKW\\nSvTal3bDfUt/ehec44x77lI/LZbPQKVfQCmNHrrNjox7bQtluWcp3gxaXNN0df5M\\neQQgO3Ihz/L36n1srctJaC+iddsGFUMNbb5wn5tDAoGAWg3rkUoGo7KXbiZtnMEi\\n3jS3Zpl6I8rVdWtelRHFMxuDM6SaLsc7YhSRTHsr0euJTxxbrSjj+AXY/R7qUwD8\\nmOKdxtRazGQtRUUxsSAX7mUYECu9mmQ/fX35DWgUktRgycpWOglRvBPoGI0zZrXE\\nFGmrOqVLD+VS88VlHnyadQ0=\\n-----END PRIVATE KEY-----\\n\"," +
                                "  \"client_email\": \"zdy-666@grand-plasma-416711.iam.gserviceaccount.com\"," +
                                "  \"client_id\": \"116190786560076280659\"," +
                                "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\"," +
                                "  \"token_uri\": \"https://oauth2.googleapis.com/token\"," +
                                "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\"," +
                                "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/zdy-666%40grand-plasma-416711.iam.gserviceaccount.com\",\n" +
                                "\"universe_domain\": \"googleapis.com\"" +
                                "}").getBytes()
                )
        ).createScoped("https://www.googleapis.com/auth/cloud-platform");

        //创建服务账号对应的操作对象
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

        // 将本地指定路径的文件转换为字节
//        byte[] bytes = Files.readAllBytes(Paths.get(filePath));

        //上传文件图片到指定的存储桶中
        BlobId blobId=BlobId.of(BUCKET_NAME,fileName);
//        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("text/plain").build();
        BlobInfo blobInfo=BlobInfo.newBuilder(blobId).build();
        Blob blob = storage.create(blobInfo, in);

        //修改已经上传的文件类型为 image/jpg
        blob.toBuilder().setContentType("image/jpg").build().update();

        //返回公开访问的地址
        return "https://storage.googleapis.com/" + BUCKET_NAME + "/" + fileName;
    }
}
