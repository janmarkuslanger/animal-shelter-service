package com.janmarkuslanger.animalshelterservice.service;

import com.janmarkuslanger.animalshelterservice.model.Image;
import com.janmarkuslanger.animalshelterservice.repository.ImageRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class ImageServiceTest {

    @Mock
    private ImageRepository imageRepository;

    @InjectMocks
    private ImageService imageService;

    public ImageServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listReturnsAllImages() {
        Iterable<Image> images = List.of(new Image(), new Image());
        when(imageRepository.findAll()).thenReturn((List<Image>) images);

        Iterable<Image> result = imageService.list();

        assertThat(result).isEqualTo(images);
    }

    @Test
    void uploadSavesAndReturnsImage() throws IOException {
        String base64 = "iVBORw0KGgoAAAANSUhEUgAAAAUA";
        String fileName = "testImage";
        String description = "Test Description";
        Image image = new Image();
        image.setDescription(description);
        image.setPath(fileName + ".png");
        when(imageRepository.save(any(Image.class))).thenReturn(image);

        Image result = imageService.upload(base64, fileName, description);

        assertThat(result.getDescription()).isEqualTo(description);
        assertThat(result.getPath()).isEqualTo(fileName + ".png");
    }

    @Test
    void uploadThrowsExceptionForInvalidBase64() {
        String base64 = "invalidBase64";
        String fileName = "testImage";
        String description = "Test Description";

        assertThatThrownBy(() -> imageService.upload(base64, fileName, description))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void deleteRemovesImageById() {
        doNothing().when(imageRepository).deleteById(1L);

        imageService.delete(1L);

        verify(imageRepository, times(1)).deleteById(1L);
    }

    @Test
    void createSavesAndReturnsImage() {
        Image image = new Image();
        when(imageRepository.save(image)).thenReturn(image);

        Image result = imageService.create(image);

        assertThat(result).isEqualTo(image);
    }

    @Test
    void getReturnsImageWhenIdExists() {
        Image image = new Image();
        when(imageRepository.findById(1L)).thenReturn(Optional.of(image));

        Image result = imageService.get(1L);

        assertThat(result).isEqualTo(image);
    }

    @Test
    void getReturnsNullWhenIdDoesNotExist() {
        when(imageRepository.findById(1L)).thenReturn(Optional.empty());

        Image result = imageService.get(1L);

        assertThat(result).isNull();
    }
}