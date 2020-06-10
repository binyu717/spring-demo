package cn.yu.demo.client.common.valid.validator;


import cn.yu.demo.client.common.valid.constraint.IntegerRange;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * integer range validator
 *
 * @author bin.yu
 * @create 2020-02-23 16:14
 * @info best wishes no bug
 **/
public class IntegerRangeValidator implements ConstraintValidator<IntegerRange, Integer> {

    private int[] range;

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        for (Integer i : range) {
            if (i.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void initialize(IntegerRange constraintAnnotation) {
        range = constraintAnnotation.range();
    }
}
