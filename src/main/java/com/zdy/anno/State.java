package com.zdy.anno;

import com.zdy.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented // 元註解
@Target({ FIELD }) // 元註解
@Retention(RUNTIME) // 元註解
@Constraint(validatedBy = { StateValidation.class }) // 指定提供校驗規則的類
public @interface State {
    // 提供校驗失敗後的提示信息
    String message() default "state參數的值只能是已發布或者草稿";
    // 指定分組
    Class<?>[] groups() default {};
    // 負載  獲取到State註解的附加信息
    Class<? extends Payload>[] payload() default {};
}

