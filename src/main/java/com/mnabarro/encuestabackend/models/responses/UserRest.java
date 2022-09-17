package com.mnabarro.encuestabackend.models.responses;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class UserRest {

    private long id;
    private String name;
    private String email;

}
