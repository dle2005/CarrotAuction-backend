package carrotauction.com.carrotauction.repository;


import carrotauction.com.carrotauction.model.entity.GalleryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryRepository extends JpaRepository<GalleryEntity, Long> {
}