package com.andcelsode.util;

import com.andcelsode.exception.ValidationException;
import com.andcelsode.model.Actor;

public class ValidationUtils {
    public static void validateActor(Actor actor) throws ValidationException {
        if (actor.getFirstName() == null) {
            throw new ValidationException("First name is required");
        }
        if (actor.getFirstName().length() > 45) {
            throw new ValidationException("First name must be a maximum of 45 characters");
        }
        if (actor.getSecondName() == null) {
            throw new ValidationException("Second name is mandatory");
        }
        if (actor.getSecondName().length() > 45) {
            throw new ValidationException("The second must have a maximum of 45 characters");
        }
        if (actor.getLastName() == null) {
            throw new ValidationException("Last name is mandatory");
        }
        if (actor.getLastName().length() > 45) {
            throw new ValidationException("The surname must have a maximum of 45 characters");
        }
    }
}
