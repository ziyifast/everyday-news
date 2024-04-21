package com.ziyi.anno;

import com.ziyi.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.validation.annotation.Validated;

import java.lang.annotation.*;

/**
 * @author zhouYi
 * @description TODO
 * @date 2024/4/21 18:11
 */


@Documented
@Constraint(
        validatedBy = {StateValidation.class} //指定根据什么规则校验
)
//只能用在属性上
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface State {

    //校验失败后的提示信息
    String message() default "{状态只能为[已发布]或[草稿]}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
