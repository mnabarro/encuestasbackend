package com.mnabarro.encuestabackend.services;

import com.mnabarro.encuestabackend.entities.UserEntity;
import com.mnabarro.encuestabackend.models.requests.UserRegisterRequestModel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public UserDetails loadUserByUsername ( String email);
    public UserEntity getUser(String email);
    public UserEntity createUser(UserRegisterRequestModel user);
}
