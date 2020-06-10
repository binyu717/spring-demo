package cn.yu.demo.client.common.valid.validator;


import cn.yu.demo.client.common.valid.constraint.IntegerContainRange;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * integer contain range validator
 *
 * @author bin.yu
 * @create 2020-02-23 16:14
 * @info best wishes no bug
 **/
public class IntegerContainRangeValidator implements ConstraintValidator<IntegerContainRange, Collection> {

    private int[] range;

    @Override
    public boolean isValid(Collection value, ConstraintValidatorContext context) {
        List<Integer> collect = Arrays.stream(range).boxed().collect(Collectors.toList());
        if (value != null && collect.containsAll(value)) {
            return true;
        }
        return false;
    }

    @Override
    public void initialize(IntegerContainRange constraintAnnotation) {
        range = constraintAnnotation.range();
    }
}
