package com.cxxx.mymvc.handler.returnvalue;

import com.cxxx.mymvc.handler.ModelAndViewContainer;
import com.cxxx.mymvc.view.View;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Package ：com.cxxx.mymvc.handler.returnvalue
 * @ClassName：ViewMethodReturnValueHandler
 * @date ：2022/12/2021:27
 * @Description：
 */
public class ViewMethodReturnValueHandler implements HandlerMethodReturnValueHandler {
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return View.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public void handleReturnValue(@Nullable Object returnValue, MethodParameter returnType,
                                  ModelAndViewContainer modelAndViewContainer, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
        if (returnValue instanceof View) {
            //如果返回值是view 的实例
            View view = (View) returnValue;
            modelAndViewContainer.setView(view);
        } else if (returnValue != null) {
            throw new UnsupportedOperationException("Unexpected return type:" +
                    returnType.getParameterType().getName() + "in method:" + returnType.getMethod());
        }
    }
}
