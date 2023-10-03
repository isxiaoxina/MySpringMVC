package com.cxxx.mymvc.exception;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * @Package ：com.cxxx.mymvc.handler.exception
 * @ClassName：NoHandlerFoundException
 * @date ：2022/11/30:49
 * @Description：
 */
public class NoHandlerFoundException extends ServletException {


    private String httpMethod;
    private String requestURL;


    public NoHandlerFoundException(HttpServletRequest request) {

        this.httpMethod = request.getMethod();
        this.requestURL = request.getRequestURI().toString();
    }


    public String getHttpMethod() {
        return httpMethod;
    }

    public String getRequestURL() {
        return requestURL;
    }
}
