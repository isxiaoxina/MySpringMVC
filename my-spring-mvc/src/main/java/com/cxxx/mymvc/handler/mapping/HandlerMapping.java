package com.cxxx.mymvc.handler.mapping;

import com.cxxx.mymvc.handler.HandlerExecutionChain;

import javax.servlet.http.HttpServletRequest;

public interface HandlerMapping {
    /**
     *  返回处理程序执行链  包含HandlerInterceptor和handler
     * @param request   传进来请求
     * @return
     * @throws Exception
     */
    HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception;
}
