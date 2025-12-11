package com.hellFire.UserProfileService.repositories;

import com.hellFire.UserProfileService.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsernameAndDeleted(String username, Boolean deleted);
}
