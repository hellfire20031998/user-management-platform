package com.hellFire.PostService.services;

import com.hellFire.PostService.exceptions.UserNotFoundException;
import com.hellFire.PostService.exceptions.UserPostNotFountException;
import com.hellFire.PostService.mappers.UserPostMapper;
import com.hellFire.PostService.models.UserPost;
import com.hellFire.PostService.models.dtos.UserPostDto;
import com.hellFire.PostService.models.requests.CreatePostRequest;
import com.hellFire.PostService.models.requests.UpdatePostRequest;
import com.hellFire.PostService.models.responses.UserResponseDto;
import com.hellFire.PostService.repositories.PostRepository;
import org.mapstruct.control.MappingControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserPostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserPostMapper userPostMapper;

    @Autowired
    private UserProfileService userProfileService;

    public UserPostDto createUserPost(CreatePostRequest request) throws UserNotFoundException {
        UserResponseDto userResponseDto = userProfileService.getUserProfile(request.getUserId());
        if(userResponseDto == null) {
            throw new UserNotFoundException("User not found with id " + request.getUserId());
        }
        return userPostMapper.toDto(postRepository.save(userPostMapper.createUserPost(request)));
    }

    public List<UserPostDto> getAllUserPosts(Long userId) throws UserNotFoundException {
        UserResponseDto userResponseDto = userProfileService.getUserProfile(userId);
        if(userResponseDto == null) {
            throw new UserNotFoundException("User not found with id " + userId);
        }
        return userPostMapper.toListDto(postRepository.findByUserIdAndDeleted(userId, false));
    }

    public UserPostDto updateUserPost(Long id, UpdatePostRequest request) throws UserPostNotFountException {
        Optional<UserPost> userPost = postRepository.findById(id);
        if(userPost.isPresent()) {
            userPostMapper.updateUserPost(userPost.get(), request);
            return userPostMapper.toDto(postRepository.save(userPost.get()));
        }
        throw new UserPostNotFountException("User post not found with id " + id);
    }

    public UserPostDto deleteUserPost(Long id) throws UserPostNotFountException {
        Optional<UserPost> userPost = postRepository.findById(id);
        if(userPost.isPresent()) {
            userPost.get().setDeleted(true);
            return userPostMapper.toDto(postRepository.save(userPost.get()));
        }
        throw new UserPostNotFountException("User post not found with id " + id);

    }

    public UserPostDto getUserPost(Long id) throws UserPostNotFountException {
        Optional<UserPost> userPost = postRepository.findById(id);
        if(userPost.isPresent()) {
            return userPostMapper.toDto(userPost.get());
        }
        throw new UserPostNotFountException("User post not found with id " + id);
    }


}
