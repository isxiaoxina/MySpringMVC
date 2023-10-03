package com.cxxx.mymvc.handler.returnvalue;

import com.cxxx.mymvc.handler.ModelAndViewContainer;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Package ：com.cxxx.mymvc.handler.returnvalue
 * @ClassName：HandlerMethodReturnValueHandler
 * @date ：2022/11/1322:30
 * @Description：
 */
public interface HandlerMethodReturnValueHandler {
    /**
     * 是否支持处理方法的返回值  做判断
     *
     * @param returnType
     * @return true或者false
     */
    boolean supportsReturnType(MethodParameter returnType);

    /**
     * 处理返回值
     *
     * @param returnValue
     * @param returnType
     * @param modelAndViewContainer
     * @param request
     * @param response
     * @throws Exception
     */
    void handleReturnValue(@Nullable Object returnValue, MethodParameter returnType,
                           ModelAndViewContainer modelAndViewContainer,
                           HttpServletRequest request, HttpServletResponse response) throws Exception;
}
