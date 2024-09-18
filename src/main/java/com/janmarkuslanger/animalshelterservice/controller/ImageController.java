package com.janmarkuslanger.animalshelterservice.controller;

import com.janmarkuslanger.animalshelterservice.dto.ImageUploadRequest;
import com.janmarkuslanger.animalshelterservice.model.Image;
import com.janmarkuslanger.animalshelterservice.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    public ResponseEntity<Iterable<Image>> list() {
        return ResponseEntity.ok(imageService.list());
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    public ResponseEntity<Image> get(Long id) {
        return ResponseEntity.ok(imageService.get(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    public ResponseEntity<Image> create(@RequestBody ImageUploadRequest imageUploadRequest) throws IOException {
        return ResponseEntity.ok(imageService.upload(imageUploadRequest.getBase64(), imageUploadRequest.getFilename(), imageUploadRequest.getDescription()));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        imageService.delete(id);
        return ResponseEntity.ok("Image deleted");
    }
}
