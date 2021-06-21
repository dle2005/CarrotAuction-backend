package carrotauction.com.carrotauction.service;

import carrotauction.com.carrotauction.model.entity.GalleryEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class GalleryDto {
    private Long id;
    private String title;
    private String filePath;

    public GalleryEntity toEntity(){
        GalleryEntity build = GalleryEntity.builder()
                .id(id)
                .title(title)
                .filePath(filePath)
                .build();
        return build;
    }

    @Builder
    public GalleryDto(Long id, String title, String filePath) {
        this.id = id;
        this.title = title;
        this.filePath = filePath;
    }
}