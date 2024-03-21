package com.example.server.services;

import com.example.server.models.ImageProduct;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageProductService {

    void create(ImageProduct req);

    List<ImageProduct> getAllImageProduct();

    ImageProduct getById(String id);

    String uploadPhoto(String id, MultipartFile file);

    void delete(String id);
}
