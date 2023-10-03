package com.cxxx.mymvc.handler.returnvalue;

import com.alibaba.fastjson.JSON;
import com.cxxx.mymvc.annotation.ResponseBody;
import com.cxxx.mymvc.handler.ModelAndViewContainer;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Package ：com.cxxx.mymvc.handler.returnvalue
 * @ClassName：ResponseBodyMethodReturnValueHandler
 * @date ：2022/12/1921:31
 * @Description：
 */
public class ResponseBodyMethodReturnValueHandler implements HandlerMethodReturnValueHandler {
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return (AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ResponseBody.class) ||
                returnType.hasMethodAnnotation(ResponseBody.class));
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType,
                                  ModelAndViewContainer modelAndViewContainer, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
        //标记本次请求已经处理完成
        modelAndViewContainer.setRequestHandled(true);
        //响应输出json返回值
        outPutMessage(response, JSON.toJSONString(returnValue));
    }

    private void outPutMessage(HttpServletResponse response, String message) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            out.write(message);
        }
    }
}
