package com.cxxx.mymvc.handler;

import com.cxxx.mymvc.ModelAndView;
import com.cxxx.mymvc.handler.interceptor.HandlerInterceptor;
import com.cxxx.mymvc.handler.interceptor.MappedInterceptor;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Package ：com.cxxx.mymvc.handler.returnvalue
 * @ClassName：HandlerExecutionChain
 * @date ：2022/10/2718:50
 * @Description： 处理器执行链
 */
public class HandlerExecutionChain {
    /**
     * 这是处理方法
     */
    private HandlerMethod handler;
    private List<HandlerInterceptor> interceptors = new ArrayList<>();//拦截器容器
    private int interceptorIndex = -1;


    public HandlerExecutionChain(HandlerMethod handler, List<HandlerInterceptor> interceptors) {
        this.handler = handler;
        //这里的拦截器容器如果有传进来的 就用传进来的  没有就用我们自己上面写的
        if (!CollectionUtils.isEmpty(interceptors)) {
            this.interceptors = interceptors;
        }
    }

    public void applyPostHandle(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) throws Exception {
        if (CollectionUtils.isEmpty(interceptors)) {
            return;
        }
        for (int i = interceptors.size() - 1; i >= 0; i--) {
            HandlerInterceptor handlerInterceptor = interceptors.get(i);
            handlerInterceptor.postHandle(request, response, handler, modelAndView);
        }
    }

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public boolean applyPreHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (CollectionUtils.isEmpty(interceptors)) {
            return true;
        }
        for (int i = 0; i < interceptors.size(); i++) {
            HandlerInterceptor interceptor = interceptors.get(i);
            //如果拦截器前置处理返回false  就请求结束并执行下面方法
            if (!interceptor.preHandle(request, response, this.handler)) {
                triggerAfterCompletion(request, response, null);
                return false;
            }
            this.interceptorIndex = i;
        }
        return true;
    }

    /**
     * 完成后触发
     *
     * @param request
     * @param response
     * @param ex
     */
    private void triggerAfterCompletion(HttpServletRequest request, HttpServletResponse response, Exception ex) throws Exception {
        if (CollectionUtils.isEmpty(interceptors)) {
            return;
        }
        for (int i = 0; i < this.interceptorIndex; i++) {
            HandlerInterceptor interceptor = interceptors.get(i);
            interceptor.afterCompletion(request, response, this.handler, ex);

        }


    }

}
