package com.mnabarro.encuestabackend.models.requests;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class UserLoginRequestModel {

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String password;

}
