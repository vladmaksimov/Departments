package com.maksimov.utils.validators;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 21.07.16.
 */
public class DataValidator {
    private Validator validator = new Validator();

    public Map<String, String> validate(Object o) {
        Map<String, String> errors = new HashMap<>();
        List<ConstraintViolation> violations = validator.validate(o);
        if (!violations.isEmpty()) {
            for (ConstraintViolation violation : violations) {
                String contextName = violation.getContext().toString();
                String errorName = contextName.substring(contextName.lastIndexOf(".") + 1);
                errors.put(errorName, violation.getMessage());
            }

        }
        return errors;
    }
}
