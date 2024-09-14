package com.janmarkuslanger.animalshelterservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VercelService {
    private final String hookUrl;

    @Autowired
    public VercelService(@Value("vercel.url") String hookUrl) {
        this.hookUrl = hookUrl;
    }

    public void triggerDeployment() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForEntity(hookUrl, null, String.class);
    }
}
