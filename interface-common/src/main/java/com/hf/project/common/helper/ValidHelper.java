package com.hf.project.common.helper;

import com.hf.project.common.entity.properties.RequiredProperties;
import com.hf.project.common.exception.FieldRequireException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description 检验帮助类
 * @Author fyq
 * @Date 2021/5/8 11:13
 **/

@Component
public class ValidHelper {

    @Autowired
    private RequiredProperties requiredProperties;

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public void valid(Object object) throws FieldRequireException {

        Set<ConstraintViolation<Object>> validate = validator.validate(object);

        if (validate.size() > 0){

            Set<String> stringSet = new HashSet<>();

            for (ConstraintViolation<Object> objectConstraintViolation : validate) {
                stringSet.add(objectConstraintViolation.getMessage());
            }

            throw new FieldRequireException(requiredProperties.getSysName(),stringSet.toString());

        }

    }
}
