package com.janmarkuslanger.animalshelterservice;

import com.janmarkuslanger.animalshelterservice.model.Image;
import com.janmarkuslanger.animalshelterservice.repository.ImageRepository;
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
