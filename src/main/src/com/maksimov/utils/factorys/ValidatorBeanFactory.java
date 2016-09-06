package com.maksimov.utils.factorys;

import com.maksimov.utils.validators.DataValidator;

public class ValidatorBeanFactory {

    private static DataValidator validator = new DataValidator();

    public static DataValidator getValidator() {
        return validator;
    }
}
