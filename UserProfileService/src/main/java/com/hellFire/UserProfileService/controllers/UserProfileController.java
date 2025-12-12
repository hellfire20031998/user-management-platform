package com.hellFire.UserProfileService.controllers;


import com.hellFire.UserProfileService.exceptions.UserAlreadyExists;
import com.hellFire.UserProfileService.exceptions.UserNotFoundException;
import com.hellFire.UserProfileService.models.requests.SignUpRequest;
import com.hellFire.UserProfileService.models.requests.UpdateUserProfileRequest;
import com.hellFire.UserProfileService.services.UserProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return new ResponseEntity<>("User profile service running fine", HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<?> create(@Valid @RequestBody SignUpRequest signUpRequest) {
        try {
            return ResponseEntity.ok(
                    userProfileService.createUserProfile(signUpRequest)
            );

        } catch (UserAlreadyExists ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());

        }
        catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went wrong. Please try again.");
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserProfile(@PathVariable Long userId) {
        try {
            return new ResponseEntity<>(userProfileService.getUserProfileByUserId(userId), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("something went wrong try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<?> updateUserProfile( @RequestBody @Valid UpdateUserProfileRequest request, @PathVariable Long userId) {
        try {
            return new ResponseEntity<>(userProfileService.updateUserProfile(request, userId), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("something went wrong try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteUserProfile( @PathVariable Long userId) {
        try{
            return new ResponseEntity<>(userProfileService.deleteUserProfile(userId), HttpStatus.OK);
        }catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
