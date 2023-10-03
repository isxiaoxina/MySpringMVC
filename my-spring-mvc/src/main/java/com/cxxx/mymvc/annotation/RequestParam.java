package com.cxxx.mymvc.annotation;

import java.lang.annotation.*;

/**
 * @Package ：com.cxxx.mymvc.annotation
 * @ClassName：RequestParam
 * @date ：2022/12/1814:15
 * @Description：
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestParam {
    String name();

    boolean required() default true;

    String defaultValue() default "";
}
