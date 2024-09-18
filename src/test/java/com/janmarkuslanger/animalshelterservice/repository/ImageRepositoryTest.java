package com.janmarkuslanger.animalshelterservice.repository;

import com.janmarkuslanger.animalshelterservice.model.Image;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ImageRepositoryTest {

    @Autowired
    private ImageRepository imageRepository;

    @Test
    void findByIdReturnsImageWhenIdExists() {
        Image image = new Image();
        image.setPath("http://example.com/image.jpg");
        image = imageRepository.save(image);

        Optional<Image> foundImage = imageRepository.findById(image.getId());

        assertThat(foundImage).isPresent();
        assertThat(foundImage.get().getPath()).isEqualTo("http://example.com/image.jpg");
    }

    @Test
    void findByIdReturnsEmptyWhenIdDoesNotExist() {
        Optional<Image> foundImage = imageRepository.findById(999L);

        assertThat(foundImage).isNotPresent();
    }

    @Test
    void savePersistsImage() {
        Image image = new Image();
        image.setPath("http://example.com/image2.jpg");

        Image savedImage = imageRepository.save(image);

        assertThat(savedImage.getId()).isNotNull();
        assertThat(savedImage.getPath()).isEqualTo("http://example.com/image2.jpg");
    }

    @Test
    void deleteRemovesImage() {
        Image image = new Image();
        image.setPath("http://example.com/image3.jpg");
        image = imageRepository.save(image);

        imageRepository.delete(image);
        Optional<Image> foundImage = imageRepository.findById(image.getId());

        assertThat(foundImage).isNotPresent();
    }
}