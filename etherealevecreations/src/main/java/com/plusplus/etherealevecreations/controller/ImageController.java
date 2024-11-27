package com.plusplus.etherealevecreations.controller;


import com.plusplus.etherealevecreations.dto.ImageDTO;
import com.plusplus.etherealevecreations.entity.Image;
import com.plusplus.etherealevecreations.response.ApiResponse;
import com.plusplus.etherealevecreations.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("${api.prefix}/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;


    @PostMapping("/uplod")
    public ResponseEntity<ApiResponse> saveImage(@RequestParam
    List<MultipartFile> files,@RequestParam Long productId){
        try {

            List<ImageDTO> imageDTOS=imageService.saveImages(files,productId);
            return ResponseEntity.ok(new ApiResponse("Upload Success",imageDTOS));

        }catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Upload Failed",e.getMessage()));


        }

    }

}
