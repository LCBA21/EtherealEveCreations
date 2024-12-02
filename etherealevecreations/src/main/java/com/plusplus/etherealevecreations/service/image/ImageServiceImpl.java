package com.plusplus.etherealevecreations.service.image;

import com.plusplus.etherealevecreations.dto.ImageDTO;
import com.plusplus.etherealevecreations.entity.Image;
import com.plusplus.etherealevecreations.entity.Product;
import com.plusplus.etherealevecreations.exceptions.ResourceNotFoundException;
import com.plusplus.etherealevecreations.repository.ImageRepository;
import com.plusplus.etherealevecreations.service.product.ProductService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService{

    private final  ImageRepository imageRepository;
    private final ProductService productService;




    @Override
    public Image getImageById(Long Id) {
        return imageRepository.findById(Id)
                .orElseThrow(()-> new ResourceNotFoundException("No Image found with id "+Id));
    }

    @Override
    public void deleteImageById(Long Id) {
        imageRepository.findById(Id).ifPresentOrElse(imageRepository::delete,
                ()->{throw new ResourceNotFoundException("No Image found with id "+Id);});


    }

    @Override
    public List<ImageDTO> saveImages(List<MultipartFile> files, Long productId) {
        Product product=productService.getProductById(productId);
        List<ImageDTO> savedImageDTO= new ArrayList<>();
        for (MultipartFile file:files){
         try {

             Image image= new Image();
             image.setFileName(file.getOriginalFilename());
             image.setFileType(file.getContentType());
             image.setImage(new SerialBlob((file.getBytes())));
             image.setProduct(product);

             String builddownloadUrl="/api/v1/images/image/download/";
             String downloadUrl=builddownloadUrl +image.getId() ;
             image.setDownloadUrl(downloadUrl);
            Image savedImage=  imageRepository.save(image);
            savedImage.setDownloadUrl(builddownloadUrl +savedImage.getId());
             imageRepository.save(savedImage);

             ImageDTO imageDTO= new ImageDTO();
             imageDTO.setId(savedImage.getId());
             imageDTO.setImageName(savedImage.getFileName());
             imageDTO.setDownloadUrl(savedImage.getDownloadUrl());
             savedImageDTO.add(imageDTO);
         }catch (IOException | SQLException e){
             throw  new RuntimeException(e.getMessage());



         }

        }


        return savedImageDTO;
    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) {
        Image image=getImageById(imageId);
        try {
            image.setFileName(file.getOriginalFilename());
            image.setFileName(file.getOriginalFilename());
            image.setImage(new SerialBlob((file.getBytes())));
            imageRepository.save(image);

        }catch (IOException | SQLException e){
            throw new RuntimeException(e.getMessage());

        }


    }
}
