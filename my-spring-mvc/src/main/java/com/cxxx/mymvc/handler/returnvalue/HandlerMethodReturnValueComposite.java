package com.cxxx.mymvc.handler.returnvalue;

import com.cxxx.mymvc.handler.ModelAndViewContainer;
import org.springframework.core.MethodParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Package ：com.cxxx.mymvc.handler.returnvalue
 * @ClassName：HandlerMethodReturnValueComposite
 * @date ：2022/11/1322:31
 * @Description：
 */
public class HandlerMethodReturnValueComposite implements HandlerMethodReturnValueHandler{
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return false;
    }

    public void handleReturnValue(Object resultValue, MethodParameter returnType, ModelAndViewContainer modelAndViewContainer, HttpServletRequest request, HttpServletResponse response) {


    }
}
