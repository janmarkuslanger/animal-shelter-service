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

    private static File saveBase64ToFile(String base64, String path) throws IOException {
        byte[] decodedBytes = Base64.getDecoder().decode(base64);
        File file = new File(path);

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(decodedBytes);
        } catch (IOException e) {
            logger.error(String.valueOf(e));
        }
        return file;
    }

    public Image upload(String base64, String fileName, String description) throws IOException {
        Image image = new Image();

        String type = getImageType(base64);
        String path = fileName + "." + type;
        saveBase64ToFile(base64, path);

        image.setDescription(description);
        image.setPath(path);

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
