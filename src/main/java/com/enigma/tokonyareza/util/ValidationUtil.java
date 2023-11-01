package com.enigma.tokonyareza.util;

import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.xml.validation.Validator;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ValidationUtil {

    private final Validator validator;

    public <T> void validate(T obj) {
        Set<ConstraintViolation<T>> result = validator.validate(obj);
        if (result.size() != 0) {
            throw new ConstraintViolationException(result);
        }
    }
}
