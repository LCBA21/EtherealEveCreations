package com.plusplus.etherealevecreations.service.image;

import com.plusplus.etherealevecreations.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    Image getImageById(Long  Id);
    void deleteImageById(Long  Id);
    Image saveImage(List<MultipartFile> files, Long productId);
    void updateImage(MultipartFile  file,Long productId);






}
