package com.cxxx.mymvc.handler.returnvalue;

import com.cxxx.mymvc.handler.ModelAndViewContainer;
import com.sun.javafx.collections.MappingChange;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Package ：com.cxxx.mymvc.handler.returnvalue
 * @ClassName：MapMethodReturnValueHandler
 * @date ：2022/12/1920:16
 * @Description：
 */
public class MapMethodReturnValueHandler implements HandlerMethodReturnValueHandler {
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return Map.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    @SuppressWarnings("unchecked")
    public void handleReturnValue(@Nullable Object returnValue, MethodParameter returnType,
                                  ModelAndViewContainer modelAndViewContainer, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
        if (returnValue instanceof Map) {
            modelAndViewContainer.getModel().addAllAttributes((Map) returnValue);
        } else if (returnValue != null) {//如果不是map的实例 并且不为空
            throw new UnsupportedOperationException("Unexpected return type:" + returnType.getParameterType().getName()
                    + "in method:" + returnType.getMethod());
        }
    }
}
