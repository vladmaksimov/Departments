package com.maksimov.utils.validators;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.context.FieldContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 21.07.16.
 */
public class DataValidator {

    private Validator validator = new Validator();

    public Map<String, List<String>> validate(Object o) {
        Map<String, List<String>> errors = new HashMap<>();
        List<ConstraintViolation> violations = validator.validate(o);
        if (!violations.isEmpty()) {
            for (ConstraintViolation violation : violations) {
                String errorName = ((FieldContext) violation.getContext()).getField().getName();
                List<String> errorList = errors.get(errorName);
                if (errorList == null) {
                    errorList = new ArrayList<>();
                    errorList.add(violation.getMessage());
                    errors.put(errorName, errorList);
                } else {
                    errorList.add(violation.getMessage());
                }
            }
        }
        return errors;
    }
}
