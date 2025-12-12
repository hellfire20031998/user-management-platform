package com.hellFire.PostService.controllers;

import com.hellFire.PostService.exceptions.UserNotFoundException;
import com.hellFire.PostService.exceptions.UserPostNotFountException;
import com.hellFire.PostService.models.requests.CreatePostRequest;
import com.hellFire.PostService.models.requests.UpdatePostRequest;
import com.hellFire.PostService.services.UserPostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserPostController {

    @Autowired
    private UserPostService userPostService;

    @PostMapping("/post")
    private ResponseEntity<?> createUserPost(@Valid @RequestBody CreatePostRequest request){
        try{
            return new ResponseEntity<>(userPostService.createUserPost(request), HttpStatus.CREATED);
        }catch (UserNotFoundException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/posts/{userId}")
    private ResponseEntity<?> getAllUserPosts(@PathVariable Long userId){
        try{
            return new ResponseEntity<>(userPostService.getAllUserPosts(userId), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<?> deleteUserPost(@PathVariable Long id){
        try{
            return new ResponseEntity<>(userPostService.deleteUserPost(id), HttpStatus.OK);
        }catch (UserPostNotFountException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/post/{id}")
    public ResponseEntity<?> updateUserPost(@Valid @RequestBody UpdatePostRequest request, @PathVariable Long id){
        try{
            return new ResponseEntity<>(userPostService.updateUserPost(id, request), HttpStatus.OK);
        } catch (UserPostNotFountException e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<?> getUserPostById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(userPostService.getUserPost(id), HttpStatus.OK);
        } catch (UserPostNotFountException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
