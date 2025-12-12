package com.hellFire.PostService.services;

import com.hellFire.PostService.exceptions.UserNotFoundException;
import com.hellFire.PostService.models.responses.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class UserProfileService {

    @Autowired
    private RestTemplate restTemplate;

    public UserResponseDto getUserProfile(Long userId) throws UserNotFoundException {
        try {
            String url = "http://localhost:8081/api/user/" + userId;
            return restTemplate.getForObject(url, UserResponseDto.class);

        } catch (HttpClientErrorException.NotFound ex) {
            throw new UserNotFoundException("User not found with id " + userId);

        } catch (HttpClientErrorException ex) {
            throw new RuntimeException("Error from User Service: " + ex.getMessage());

        } catch (Exception e) {
            throw new RuntimeException("Internal error: " + e.getMessage());
        }
    }
}
