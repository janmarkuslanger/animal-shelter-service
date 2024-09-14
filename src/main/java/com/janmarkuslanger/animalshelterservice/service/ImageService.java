package com.janmarkuslanger.animalshelterservice.service;

import com.janmarkuslanger.animalshelterservice.model.Image;
import com.janmarkuslanger.animalshelterservice.repository.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    static Logger logger = LoggerFactory.getLogger(ImageService.class);

    public Iterable<Image> list() {
        return imageRepository.findAll();
    }

    private static String getImageType(String base64) {
        char firstChar = base64.charAt(0);

        if (firstChar == '/') {
            return "jpg";
        }

        if (firstChar == 'i') {
            return "png";
        }

        return null;
    }

    private static File saveBase64ToFile(String base64, String filename) throws IOException {
        byte[] decodedBytes = Base64.getDecoder().decode(base64);
        File file = new File(filename);
        String type = getImageType(base64);

        String path = file + "." + type;

        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(decodedBytes);
        } catch (IOException e) {
            logger.error(String.valueOf(e));
        }
        return file;
    }

    public Image upload(String base64, String fileName, String description) throws IOException {
        Image image = new Image();
        image.setDescription(description);
        saveBase64ToFile(base64, fileName);
        return imageRepository.save(image);
    }

    public void delete(Long id) {
        imageRepository.deleteById(id);
    }

    public Image create(Image image) {
        return imageRepository.save(image);
    }

    public Image get(Long id) {
        return imageRepository.findById(id).orElse(null);
    }
}
