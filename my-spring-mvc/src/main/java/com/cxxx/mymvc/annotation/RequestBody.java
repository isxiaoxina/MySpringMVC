package com.cxxx.mymvc.annotation;

import java.lang.annotation.*;

/**
 * @Package ：com.cxxx.mymvc.annotation
 * @ClassName：RequestbBody
 * @date ：2022/12/1522:02
 * @Description：
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestBody {


    boolean required() default true;
}
