package com.hellFire.CommentService.commons;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Utils {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
