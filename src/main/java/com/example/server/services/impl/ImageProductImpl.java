package com.example.server.services.impl;

import com.example.server.constants.Constants;
import com.example.server.models.ImageProduct;
import com.example.server.repo.ImageProductRepository;
import com.example.server.services.ImageProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@RequiredArgsConstructor
public class ImageProductImpl implements ImageProductService {

    private final ImageProductRepository repository;
    @Override
    public void create(ImageProduct req) {
        repository.save(req);
    }

    @Override
    public List<ImageProduct> getAllImageProduct() {
        return repository.findAll();
    }

    @Override
    public ImageProduct getById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public String uploadPhoto(String id, MultipartFile file) {
        ImageProduct image = getById(id);
        String photoUrl = photoFunction.apply(id,file);
        image.setImageUrl(photoUrl);
        repository.save(image);
        return photoUrl;
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    private final Function<String, String> fileExtension = filename -> Optional.of(filename).filter(name -> name.contains("."))
            .map(name -> "." + name.substring(filename.lastIndexOf(".") + 1)).orElse(".png");

    private final BiFunction<String, MultipartFile, String> photoFunction = (id, image) -> {
        String filename = id + fileExtension.apply(image.getOriginalFilename());
        try {
            Path fileStorageLocation = Paths.get(Constants.PHOTO_DIRECTORY).toAbsolutePath().normalize();
            if(!Files.exists(fileStorageLocation)) { Files.createDirectories(fileStorageLocation); }
            Files.copy(image.getInputStream(), fileStorageLocation.resolve(filename), REPLACE_EXISTING);
            return ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("image/" + filename).toUriString();
        }catch (Exception exception) {
            throw new RuntimeException("Unable to save image");
        }
    };
}
