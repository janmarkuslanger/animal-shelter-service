package com.janmarkuslanger.animalshelterapi.service;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VercelServiceTest {

    @Test
    void triggerDeploymentSuccessfully() {
        VercelService vercelService = new VercelService("https://www.google.de");
        assertDoesNotThrow(
                () -> {
                    vercelService.triggerDeployment();
                }
        );
    }

    @Test
    void triggerDeploymentWithInvalidUrl() {
        VercelService vercelService = new VercelService("invalid-url");
        assertThrows(Exception.class, vercelService::triggerDeployment);
    }

    @Test
    void triggerDeploymentWithNullUrl() {
        VercelService vercelService = new VercelService(null);
        assertThrows(Exception.class, vercelService::triggerDeployment);
    }
}