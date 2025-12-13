package com.hellFire.CommentService.repositories;

import com.hellFire.CommentService.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostIdAndDeleted(Long postId, boolean deleted);

    Optional<Comment> findByIdAndPostIdAndDeleted(Long id, Long postId, boolean deleted);
}
