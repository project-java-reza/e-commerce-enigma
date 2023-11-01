package com.enigma.tokonyareza.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

// saat mengimport validation ada 2 yang kalian harus perhatikan
import javax.validation.*;
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

    // setelah ini kita buat service untuk AuthService
}
