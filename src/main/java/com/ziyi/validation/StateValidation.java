package com.ziyi.validation;

import com.ziyi.anno.State;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author zhouYi
 * @description 自定义State校验
 * @date 2024/4/21 18:14
 */
public class StateValidation implements ConstraintValidator<State, String> {
    @Override
    public boolean isValid(String state, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isEmpty(state)) {
            return false;
        }
        if (("已发布").equals(state) || ("草稿").equals(state)) {
            //返回true表示校验成功
            return true;
        }
        return false;
    }
}
