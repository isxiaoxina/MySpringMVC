package com.cxxx.mymvc.handler.interceptor;

import com.cxxx.mymvc.ModelAndView;
import org.springframework.lang.Nullable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//处理器拦截器

/**
 * preHandle()：在业务处理器处理请求之前被调用。预处理。
 * postHandle()：在业务处理器处理请求执行完成后，生成视图之前执行。后处理。
 * afterCompletion()：在DispatcherServlet完全处理完请求后被调用，可用于清理资源等。返回处理（已经渲染了页面）
 */
public interface HandlerInterceptor {
    /**
     * 前置处理  默认true
     * 方法在请求处理之前被调用。该方法在 Interceptor 类中最先执行，
     * 用来进行一些前置初始化操作或是对当前请求做预处理，也可以进行一些判断来决定请求是否要继续进行下去。
     * 该方法的返回至是 Boolean 类型，当它返回 false 时，表示请求结束，后续的 Interceptor 和 Controller 都不会再执行；
     * 当它返回为 true 时会继续调用下一个 Interceptor 的 preHandle 方法，
     * 如果已经是最后一个 Interceptor 的时候就会调用当前请求的 Controller 方法。
     */
    default boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    /**
     * 后置处理
     * 方法在当前请求处理完成之后，也就是 Controller 方法调用之后执行，
     * 但是它会在  DispatcherServlet  进行视图返回渲染之前被调用，
     * 所以我们可以在这个方法中对 Controller 处理之后的 ModelAndView 对象进行操作。
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    default void postHandle (HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    /**
     * 完成后
     * 方法顾名思义，该方法将在整个请求结束之后，
     * 也就是在 DispatcherServlet 渲染了对应的视图之后执行。此方法主要用来进行资源清理。
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    default void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                 @Nullable Exception ex) throws Exception {

    }
}
