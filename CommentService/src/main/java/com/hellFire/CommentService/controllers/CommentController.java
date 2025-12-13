package com.hellFire.CommentService.controllers;

import com.hellFire.CommentService.models.requests.AddCommentRequest;
import com.hellFire.CommentService.services.CommentServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentServices commentServices;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<?> addComment(@Valid @RequestBody AddCommentRequest addCommentRequest,
                                        @PathVariable Long postId) {
        try{
            return new ResponseEntity<>(commentServices.addComment(addCommentRequest, postId), HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<?> getComments(@PathVariable Long postId) {
        try{
            return new ResponseEntity<>(commentServices.getAllComments(postId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId, @PathVariable Long postId) {
        try{
            return new ResponseEntity<>(commentServices.deleteComment(commentId, postId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
