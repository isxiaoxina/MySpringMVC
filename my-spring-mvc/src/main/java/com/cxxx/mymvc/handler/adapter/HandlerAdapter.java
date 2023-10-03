package com.cxxx.mymvc.handler.adapter;

import com.cxxx.mymvc.ModelAndView;
import com.cxxx.mymvc.handler.HandlerMethod;
import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HandlerAdapter {
    ModelAndView handle(HttpServletRequest request, HttpServletResponse response, HandlerMethod method) throws Exception;


}
