package com.zdy.validation;

import com.zdy.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StateValidation implements ConstraintValidator<State,String> {
    /**
     *
     * @param value 將來要校驗的資料
     * @param context context in which the constraint is evaluated
     *
     * @return 如果返回false,則校驗不通過,如果返回true,則校驗通過
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 提供校驗規則
        if (value == null){
            return false;
        }
        if (value.equals("已發布") || value.equals("草稿")){
            return true;
        }
        return false;
    }
}

