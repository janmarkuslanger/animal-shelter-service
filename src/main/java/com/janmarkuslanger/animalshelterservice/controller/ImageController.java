package com.janmarkuslanger.animalshelterservice.controller;

import com.janmarkuslanger.animalshelterservice.dto.ImageUploadRequest;
import com.janmarkuslanger.animalshelterservice.model.Image;
import com.janmarkuslanger.animalshelterservice.service.ImageService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v1/image", produces = {"application/json"})
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public Iterable<Image> list() {
        return imageService.list();
    }

    @GetMapping(value = "/{id}")
    public Image get(Long id) {
        return imageService.get(id);
    }

    @PostMapping
    public Image create(@RequestBody ImageUploadRequest imageUploadRequest) throws IOException {
        return imageService.upload(imageUploadRequest.getBase64(), imageUploadRequest.getFilename(), imageUploadRequest.getDescription());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        imageService.delete(id);
    }
}
