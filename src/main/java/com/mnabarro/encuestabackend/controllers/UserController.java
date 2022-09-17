package com.mnabarro.encuestabackend.controllers;

import com.mnabarro.encuestabackend.entities.UserEntity;
import com.mnabarro.encuestabackend.models.requests.UserRegisterRequestModel;
import com.mnabarro.encuestabackend.models.responses.UserRest;
import com.mnabarro.encuestabackend.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping()
    public UserRest createUser(@RequestBody @Valid UserRegisterRequestModel userModel) {

        UserEntity user = userService.createUser(userModel);
        UserRest userRest = new UserRest();
        BeanUtils.copyProperties(user, userRest);

        return userRest;
    }

    @GetMapping
    public UserRest getUser(Authentication authentication) {

        String userEmail = authentication.getPrincipal().toString();

        UserRest userRest = new UserRest();

        UserEntity user = userService.getUser(userEmail);
        BeanUtils.copyProperties(user, userRest);

        return userRest;
    }
}
