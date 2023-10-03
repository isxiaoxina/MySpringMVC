package com.cxxx.mymvc.annotation;

import java.lang.annotation.*;

/**
 * @Package ：com.cxxx.mymvc.annotation
 * @ClassName：ResponseBody
 * @date ：2022/12/1923:09
 * @Description：
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseBody {
}
