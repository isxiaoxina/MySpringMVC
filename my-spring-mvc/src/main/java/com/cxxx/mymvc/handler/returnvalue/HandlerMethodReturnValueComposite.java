package com.cxxx.mymvc.handler.returnvalue;

import com.cxxx.mymvc.handler.ModelAndViewContainer;
import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.MethodParameter;

/**
 * @Package ：com.cxxx.mymvc.handler.returnvalue
 * @ClassName：HandlerMethodReturnValueComposite
 * @date ：2022/11/1322:31
 * @Description：
 */
public class HandlerMethodReturnValueComposite implements HandlerMethodReturnValueHandler {

    private ArrayList<HandlerMethodReturnValueHandler> returnValueHandler = new ArrayList<>();

    public void addHandlerMethodReturnHandler(HandlerMethodReturnValueHandler... valueHandler) {
        Collections.addAll(returnValueHandler, valueHandler);
    }

    public void addHandlerMethodReturnHandler(HandlerMethodReturnValueHandler valueHandler) {
        this.returnValueHandler.add(valueHandler);
    }

    public void clearHandlerMethodReturnHandler() {
        this.returnValueHandler.clear();
    }


    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return true;
    }

    public void handleReturnValue(Object resultValue, MethodParameter returnType,
            ModelAndViewContainer modelAndViewContainer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        for (HandlerMethodReturnValueHandler handlerMethodReturnValueHandler : this.returnValueHandler) {
            if (handlerMethodReturnValueHandler.supportsReturnType(returnType)) {
                handlerMethodReturnValueHandler.handleReturnValue(resultValue, returnType, modelAndViewContainer,
                        request, response);
            }
        }
    }
}
