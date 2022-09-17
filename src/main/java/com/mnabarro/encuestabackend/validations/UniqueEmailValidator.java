package com.mnabarro.encuestabackend.validations;

import com.mnabarro.encuestabackend.annotations.UniqueEmail;
import com.mnabarro.encuestabackend.entities.UserEntity;
import com.mnabarro.encuestabackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        UserEntity user = userRepository.findByEmail(value);
        if (user == null) {
            return true;
        }
        return false;
    }
}
