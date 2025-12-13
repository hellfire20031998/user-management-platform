package com.hellFire.CommentService.services;

import com.hellFire.CommentService.exceptions.UserCommentNotFoundException;
import com.hellFire.CommentService.exceptions.UserPostNotFoundException;
import com.hellFire.CommentService.mappers.CommentMapper;
import com.hellFire.CommentService.models.Comment;
import com.hellFire.CommentService.models.dtos.CommentDto;
import com.hellFire.CommentService.models.requests.AddCommentRequest;
import com.hellFire.CommentService.models.responses.UserPostDto;
import com.hellFire.CommentService.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServices {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserProfileAndPostService userProfileAndPostService;

    public CommentDto addComment(AddCommentRequest request, Long postId) throws UserPostNotFoundException {

        UserPostDto userPostDto = userProfileAndPostService.getUserPostById(postId);
        if(userPostDto == null) {
            throw new UserPostNotFoundException("User post not found with id: " + postId);
        }
        Comment comment = commentMapper.requestToEntity(request, new Comment());
        comment.setPostId(postId);
        return commentMapper.toDto(commentRepository.save(comment));
    }

    public List<CommentDto> getAllComments(Long postId) {
        return commentMapper.toDtoList(commentRepository.findByPostIdAndDeleted(postId, false));
    }

    public CommentDto deleteComment(Long commentId, Long postId) throws UserCommentNotFoundException {

        UserPostDto userPostDto = userProfileAndPostService.getUserPostById(postId);
        if(userPostDto == null) {
            throw new UserCommentNotFoundException("User post not found with id: " + postId);
        }
        Optional<Comment> comment = commentRepository.findByIdAndPostIdAndDeleted(commentId, postId, false);
        if(comment.isPresent()) {
            comment.get().setDeleted(true);
            return commentMapper.toDto(commentRepository.save(comment.get()));
        }
        throw new UserCommentNotFoundException("Comment not found with id "+ commentId);
    }
}
