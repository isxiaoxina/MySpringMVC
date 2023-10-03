package com.cxxx.mymvc.handler.interceptor;


import com.cxxx.mymvc.ModelAndView;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Package ：com.cxxx.mymvc.handler.interceptor
 * @ClassName：MappedInterceptor
 * @date ：2022/10/3021:48
 * @Description：映射拦截器
 */
public class MappedInterceptor implements HandlerInterceptor {
    private List<String> includePatterns = new ArrayList<>();
    private List<String> excludePatterns = new ArrayList<>();


    private HandlerInterceptor interceptor;

    public MappedInterceptor(HandlerInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    public HandlerInterceptor getInterceptor() {
        return interceptor;
    }

    public MappedInterceptor addIncludePatterns(String... patterns) {

        this.includePatterns.addAll(Arrays.asList(patterns));
        return this;
    }

    public MappedInterceptor addExcludePatterns(String... patterns) {
        this.excludePatterns.addAll(Arrays.asList(patterns));
        return this;
    }

    /**
     * 匹配拦截器
     *
     * @param lookupPath
     * @return
     */
    public boolean matches(String lookupPath) {
        //如果excludePatterns不是空的。
        if (!CollectionUtils.isEmpty(this.excludePatterns)) {
            //并且如果这个不支持的map里面的有存这个path
            if (excludePatterns.contains(lookupPath)) {
                return false;
            }
        }
        if (ObjectUtils.isEmpty(this.includePatterns)) {
              return true;
        }
        if (includePatterns.contains(lookupPath)){
            return true;
        }
        return false;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
