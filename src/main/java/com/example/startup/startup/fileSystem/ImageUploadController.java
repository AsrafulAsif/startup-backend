package com.example.startup.startup.fileSystem;

import com.example.startup.startup.fileSystem.response.ImageUrlResponseRest;
import com.example.startup.startup.model.SimpleResponseRest;
import com.example.startup.startup.utils.MakingResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/image-file")
public class ImageUploadController {
    private final FirebaseStorageService firebaseStorageService;

    public ImageUploadController(FirebaseStorageService firebaseStorageService) {
        this.firebaseStorageService = firebaseStorageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<SimpleResponseRest> uploadFile(
            @RequestParam("file") MultipartFile multipartFile,
            @RequestParam("appUserId") String appUserId

    ){
        ImageUrlResponseRest response = firebaseStorageService.uploadImage(multipartFile,appUserId);
        return MakingResponse.makingResponse(response);
    }
}
