package com.mnabarro.encuestabackend.services;

import com.mnabarro.encuestabackend.entities.UserEntity;
import com.mnabarro.encuestabackend.models.requests.UserRegisterRequestModel;
import com.mnabarro.encuestabackend.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImplementation implements UserService {

    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImplementation(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email)  throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByEmail(email);

        if(userEntity == null) {
            throw new UsernameNotFoundException(email);
        }

        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }

    @Override
    public UserEntity getUser(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity createUser(UserRegisterRequestModel user) {

        UserEntity uSerEntity = new UserEntity();
        BeanUtils.copyProperties(user, uSerEntity);

        uSerEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return userRepository.save(uSerEntity);
    }
}
