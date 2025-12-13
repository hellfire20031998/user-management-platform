package com.hellFire.CommentService.services;

import com.hellFire.CommentService.models.responses.UserPostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserProfileAndPostService {

    @Autowired
    private RestTemplate restTemplate;

    public UserPostDto getUserPostById(Long postId) {
        try{
            String url = "http://localhost:8082/api/post/"+postId;
            return restTemplate.getForObject(url, UserPostDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
