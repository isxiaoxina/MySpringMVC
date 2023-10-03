package com.cxxx.mymvc.handler.argument;

import com.cxxx.mymvc.handler.ModelAndViewContainer;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.ConversionService;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Package ：com.cxxx.mymvc.handler.argument
 * @ClassName：ServletRequestMethodArgumentResolver
 * @date ：2022/12/1820:41
 * @Description：
 */
public class ServletRequestMethodArgumentResolver implements  HandlerMethodArgumentResolver{
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> parameterType = parameter.getParameterType();
        //看这个前面的类是不是跟后面的类相同或者是后面类的父类
        return ServletRequest.class.isAssignableFrom(parameterType);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, HttpServletRequest request, HttpServletResponse httpServletResponse, ModelAndViewContainer container, ConversionService conversionService) throws Exception {
        return request;
    }
}
