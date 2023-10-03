package com.cxxx.mymvc.handler.returnvalue;

import com.cxxx.mymvc.handler.ModelAndViewContainer;
import org.springframework.core.MethodParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Package ：com.cxxx.mymvc.handler.returnvalue
 * @ClassName：ViewNameMethodReturnValueHandler
 * @date ：2022/12/2217:42
 * @Description：
 */
public class ViewNameMethodReturnValueHandler implements HandlerMethodReturnValueHandler {
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        Class<?> parameterType = returnType.getParameterType();
        return CharSequence.class.isAssignableFrom(parameterType);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer modelAndViewContainer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (returnValue instanceof CharSequence) {
            String viewName = returnValue.toString();
            modelAndViewContainer.setViewName(viewName);
        } else if (returnValue != null) {
            // should not happen
            throw new UnsupportedOperationException("Unexpected return type: " +
                    returnType.getParameterType().getName() + " in method: " + returnType.getMethod());
        }
    }
}
