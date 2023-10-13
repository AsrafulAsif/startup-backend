package com.example.startup.startup.fileSystem;

import com.example.startup.startup.fileSystem.response.ImageUrlResponseRest;
import com.google.cloud.storage.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.StorageClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Service
@Slf4j
public class FirebaseStorageService {
    String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/startup-2c5e6.appspot.com/o/%s?alt=media";


    private String upload(File file, String fileName){

        String folderName = "images";
        String fileNameWithFolder = folderName + "/" + fileName;
        Bucket bucket = StorageClient.getInstance(FirebaseApp.getInstance("StartUp")).bucket();
        BlobId blobId = BlobId.of(bucket.getName(), fileNameWithFolder);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        Storage storage = bucket.getStorage();

        try {
            storage.create(blobInfo, Files.readAllBytes(file.toPath()));
            file.delete();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return String.format(DOWNLOAD_URL, URLEncoder.encode(fileNameWithFolder, StandardCharsets.UTF_8));
    }

    private File convertToFile(MultipartFile multipartFile, String fileName){
        File tempFile = new File(fileName);
        try{
            FileOutputStream outputStream = new FileOutputStream(tempFile);
            outputStream.write(multipartFile.getBytes());
            outputStream.close();
        }catch (Exception e){
            log.error("Convert file error:::{}", e.getMessage());
        }
        return tempFile;
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }




    public ImageUrlResponseRest uploadImage(MultipartFile multipartFile,String appUserId) {


            String fileName = multipartFile.getOriginalFilename();
            if (fileName!=null){
                fileName = appUserId.concat(this.getExtension(fileName));
            }

            File file = this.convertToFile(multipartFile, fileName);
            String url = this.upload(file, fileName);
            ImageUrlResponseRest responseRest = new ImageUrlResponseRest();
            ImageUrlResponseRest.ImageUrlResponse data = ImageUrlResponseRest.ImageUrlResponse.builder().imageUrl(url).build();
            responseRest.setImageUrlResponse(data);
            return  responseRest;
        }
}
