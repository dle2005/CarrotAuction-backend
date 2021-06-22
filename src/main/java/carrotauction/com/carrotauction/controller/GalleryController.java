package carrotauction.com.carrotauction.controller;

import carrotauction.com.carrotauction.service.S3Service;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
public class GalleryController {
    private S3Service s3Service;

    @PostMapping("/gallery")
    public String execWrite(MultipartFile file) throws IOException {
        String file_path = s3Service.upload(file);
        System.out.println(file_path);
        return file_path;
    }
}
