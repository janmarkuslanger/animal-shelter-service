package com.janmarkuslanger.animalshelterapi.service;

import com.janmarkuslanger.animalshelterapi.model.Image;
import com.janmarkuslanger.animalshelterapi.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }

    public Image saveImage(Image image) {
        return imageRepository.save(image);
    }

}
