package cn.yu.demo.service.common.util;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Validation util
 * 参数校验工具，手动执行校验
 * @author bin.yu
 * @create 2020-02-23 16:14
 * @info best wishes no bug
 **/
public class ValidationUtil {

    private ValidationUtil() {
    }

    public static <T> void validation(T t, Class<?>... groups) {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
//                .failFast(true) //快速失败返回
                .failFast(false) //普通模式
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Object>> violations = validator.validate(t, groups);
        if (CollectionUtils.isNotEmpty(violations)) {
            throw new ConstraintViolationException(violations);
        }
    }

}
