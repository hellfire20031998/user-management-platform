package com.hellFire.UserProfileService.models;


import jakarta.persistence.*;

@Entity
@Table(name = "app_user")
public class AppUser extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
}
