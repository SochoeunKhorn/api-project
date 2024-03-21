package com.example.server.controller;

import com.example.server.constants.Constants;
import com.example.server.models.ImageProduct;
import com.example.server.services.ImageProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequiredArgsConstructor
public class ImageProductController {
    private final ImageProductService service;

    @PostMapping("/api/image_product")
    public ResponseEntity<Object> create(@RequestBody ImageProduct req){
        service.create(req);
        return new ResponseEntity<>("Create successfully", HttpStatus.OK);
    }

    @GetMapping("/api/image_product")
    public ResponseEntity<Object> get(){
        var list = service.getAllImageProduct();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/api/image_product/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") String id){
        var imageID = service.getById(id);
        return new ResponseEntity<>(imageID,HttpStatus.OK);
    }

    @PutMapping("/api/image_product/photo")
    public ResponseEntity<Object> uploadPhoto(@RequestParam("id") String id, @RequestParam("file")MultipartFile file){
        service.uploadPhoto(id,file);
        return new ResponseEntity<>("upload successfully",HttpStatus.OK);
    }

    @GetMapping(path = "/image/{filename}", produces = { IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE })
    public byte[] getPhoto(@PathVariable("filename") String filename) throws IOException {
        return Files.readAllBytes(Paths.get(Constants.PHOTO_DIRECTORY + filename));
    }

    @GetMapping("/api/image_product/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id){
        service.delete(id);
        return new ResponseEntity<>("delete successfully",HttpStatus.OK);
    }
}
