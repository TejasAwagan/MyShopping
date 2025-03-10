package com.devtejas.myshopping.controller;

import com.devtejas.myshopping.dto.ImageDto;
import com.devtejas.myshopping.exception.ResourceNotFoundException;
import com.devtejas.myshopping.models.Image;
import com.devtejas.myshopping.response.ApiResponse;
import com.devtejas.myshopping.service.image.IImageService;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
@RequestMapping("${api.prefix}/images")
public class ImageController {

    @Autowired
    IImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> saveImages(@RequestParam List<MultipartFile> files, @RequestParam Long productId) {
        try {
            List<ImageDto> imageDtos  = imageService.saveImages(productId,files);
            return ResponseEntity.ok(new ApiResponse("Upload Success !", imageDtos));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Upload failed!", e.getMessage()));
        }

    }
    @GetMapping("/image/download/{imageId}")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long imageId) throws SQLException {
        Image image = imageService.getImageById(imageId);
        ByteArrayResource resource = new ByteArrayResource(image.getImage().getBytes(1, (int) image.getImage().length()));
        return  ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +image.getFileName() + "\"")
                .body(resource);
    }

    @PutMapping("/image/{imageId}/update")
    public ResponseEntity<ApiResponse> updateImage(@RequestParam MultipartFile file, @RequestParam Long imageId){
        try {
            Image image = imageService.getImageById(imageId);

            if(image != null){
                imageService.updateImage(file, imageId);
                return ResponseEntity.ok(new ApiResponse("Update Success !", null));
            }
        }
        catch(ResourceNotFoundException e){
            ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }

       return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Update failed !", INTERNAL_SERVER_ERROR));
    }

    public ResponseEntity<ApiResponse> deleteImage( @RequestParam Long imageId){
        try {
            Image image = imageService.getImageById(imageId);

            if(image != null){
                imageService.deleteImageById(imageId);
                return ResponseEntity.ok(new ApiResponse("Deleted Successfuly !", null));
            }
        }
        catch(ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse( "Delete failed!",INTERNAL_SERVER_ERROR));
    }
}

























