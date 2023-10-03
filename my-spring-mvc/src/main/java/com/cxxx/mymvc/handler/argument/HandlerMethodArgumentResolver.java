package com.cxxx.mymvc.handler.argument;

import com.cxxx.mymvc.handler.ModelAndViewContainer;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.ConversionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HandlerMethodArgumentResolver {

    /**
     * 查看是否支持参数
     *
     * @param parameter
     * @return
     */
    boolean supportsParameter(MethodParameter parameter);

    Object resolveArgument(MethodParameter parameter, HttpServletRequest request,
                           HttpServletResponse httpServletResponse, ModelAndViewContainer container
            , ConversionService conversionService) throws Exception;
}
