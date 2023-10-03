package com.cxxx.mymvc.annotation;


import com.cxxx.mymvc.http.RequestMethod;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {

    String path();

    RequestMethod method() default RequestMethod.GET;
}
