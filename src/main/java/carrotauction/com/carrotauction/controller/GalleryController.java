package carrotauction.com.carrotauction.controller;

import carrotauction.com.carrotauction.model.entity.ItemImage;
import carrotauction.com.carrotauction.repository.ItemImageRepository;
import carrotauction.com.carrotauction.repository.ItemRepository;
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
    private ItemRepository itemRepository;
    private ItemImageRepository itemImageRepository;

    @PostMapping("/gallery")
    public String execWrite(MultipartFile file) throws IOException {
        String file_path = s3Service.upload(file);
//        System.out.println(file_path);
//        ItemImage itemImage = ItemImage.builder()
//                .url(file_path)
//                .item(itemRepository.getOne(1L))
//                .build();
//        itemImageRepository.save(itemImage);
        return file_path;
    }
}
