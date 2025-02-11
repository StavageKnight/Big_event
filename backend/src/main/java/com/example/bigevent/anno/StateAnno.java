package com.example.bigevent.anno;

import com.example.bigevent.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = {StateValidation.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface StateAnno {
    String message() default "state属性的值只能是草稿或已发布";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
