package com.hellFire.PostService.repositories;

import com.hellFire.PostService.models.UserPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<UserPost, Long> {
    List<UserPost> findByUserIdAndDeleted(Long userId, boolean deleted);
}
