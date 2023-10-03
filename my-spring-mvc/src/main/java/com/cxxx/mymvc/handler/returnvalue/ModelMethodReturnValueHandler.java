package com.cxxx.mymvc.handler.returnvalue;

import com.cxxx.mymvc.handler.ModelAndViewContainer;
import org.springframework.core.MethodParameter;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Package ：com.cxxx.mymvc.handler.returnvalue
 * @ClassName：ModelMethodReturnValueHandler
 * @date ：2022/12/1920:48
 * @Description：
 */
public class ModelMethodReturnValueHandler implements HandlerMethodReturnValueHandler {
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return Model.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer modelAndViewContainer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (returnValue == null) {
            return;
        } else if (returnValue instanceof Model) {
            //把model转换成map
            modelAndViewContainer.getModel().addAllAttributes(((Model) returnValue).asMap());
        } else {
            throw new UnsupportedOperationException("Unexpected return type:" + returnType.getParameterType().getName()
                    + "in method" + returnType.getMethod());
        }
    }
}
