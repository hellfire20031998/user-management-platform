package com.hellFire.UserProfileService.repositories;

import com.hellFire.UserProfileService.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findByAppUser_Id(Long appUserId);
}
