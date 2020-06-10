package cn.yu.demo.client.common.valid.constraint;


import cn.yu.demo.client.common.valid.validator.IntegerRangeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

/**
 * integer range
 *
 * @author bin.yu
 * @create 2020-02-23 16:14
 * @info best wishes no bug
 **/
@Constraint(validatedBy = {IntegerRangeValidator.class})
@Target({FIELD, ANNOTATION_TYPE, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface IntegerRange {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int[] range() default {};
}
