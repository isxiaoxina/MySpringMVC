package com.cxxx.mymvc.handler.mapping;


import com.cxxx.mymvc.annotation.RequestMapping;
import com.cxxx.mymvc.exception.NoHandlerFoundException;
import com.cxxx.mymvc.handler.HandlerExecutionChain;
import com.cxxx.mymvc.handler.HandlerMethod;
import com.cxxx.mymvc.handler.interceptor.HandlerInterceptor;
import com.cxxx.mymvc.handler.interceptor.MappedInterceptor;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Package ：com.cxxx.mymvc.handler.mapping
 * @ClassName：RequestMappingHandlerMapping
 * @date ：2022/10/2722:43
 * @Description：请求映射处理程序映射
 * 流程
 * 1先获取所有ioc里面的bean
 * 2然后取出bean里面的controller注解的类然后循环类
 * 3然后循环类controller取出里面所有的方法继续循环方法
 * 4然后根据方法获得注解返回元数据信息（路径这些）
 * 5最后把这些信息 this.mappingRegistry.register(requestMappingInfo, handler, method)
 * 一对一的存进注册中心
 */
public class RequestMappingHandlerMapping extends ApplicationObjectSupport implements HandlerMapping {
    private MappingRegistry mappingRegistry = new MappingRegistry();
    private List<MappedInterceptor> interceptors = new ArrayList<>();

    public RequestMappingHandlerMapping(List<MappedInterceptor> interceptors) {
        this.interceptors = interceptors;
    }

    public MappingRegistry getMappingRegistry() {
        return mappingRegistry;
    }

    /**
     * 属性设置之后
     *
     * @throws Exception
     */
    public void afterPropertiesSet() throws Exception {
        initialHandlerMethods();
    }

    private void initialHandlerMethods() {
        //返回object下所有的注册进ioc 的bean  类。
        Map<String, Object> beansOfMap = BeanFactoryUtils.beansOfTypeIncludingAncestors(obtainApplicationContext(), Object.class);

        beansOfMap.entrySet().stream().
                filter(entry -> this.isHandler(entry.getValue())//过滤出Controller的注解的类
                        //过滤出带有controller'注解的bean 然后循环解析  这个方法是循环类
                ).forEach(entry -> this.detectHandlerMethods(entry.getKey(), entry.getValue()));
    }

    /**
     * 解析出handler类中 所有被RequestMapping注解的方法
     * 循环添加方法到 映射注册中心
     *
     * @param beanName
     * @param handler
     */
    private void detectHandlerMethods(String beanName, Object handler) {
        Class<?> handlerClass = handler.getClass();
        //通过ioc里面的类  遍历取出所有方法和我们自定义的元数据类存进map
        Map<Method, RequestMappingInfo> methodsOfMap = MethodIntrospector.selectMethods(handlerClass,
                (MethodIntrospector.MetadataLookup<RequestMappingInfo>) method -> getRequestMappingInfo(method, handlerClass));//需要的元数据方法
        //循环把每个类里面的每个方法和请求映射信息注册到注册中心的容器里面
        methodsOfMap.forEach((method, requestMappingInfo) -> this.mappingRegistry.register(requestMappingInfo, handler, method));
    }

    /**
     * 收集元数据的方法
     *
     * @param method   传进来controller里面的方法
     * @param beanType 对应的controller
     * @return
     */
    private RequestMappingInfo getRequestMappingInfo(Method method, Class<?> beanType) {
        //每个方法对应RequestMapping注解
        RequestMapping requestMapping = AnnotatedElementUtils.findMergedAnnotation(method, RequestMapping.class);
        if (Objects.isNull(requestMapping)) {
            return null;
        }
        //获取controller类映射前缀
        String prefix = getPathPrefix(beanType);
        return new RequestMappingInfo(prefix, requestMapping);
    }

    /**
     * 获取controller类的全体映射前缀
     *
     * @param beanType
     * @return
     */
    private String getPathPrefix(Class<?> beanType) {
        RequestMapping requestMapping = AnnotatedElementUtils.findMergedAnnotation(beanType, RequestMapping.class);
        if (Objects.isNull(requestMapping)) {
            return null;
        }
        String path = requestMapping.path();
        return path;
    }

    /**
     * 类上有标记Controller的注解就是我们需要找的handler
     *
     * @param handler
     * @return
     */
    private boolean isHandler(Object handler) {
        Class<?> aClass = handler.getClass();
        return (AnnotatedElementUtils.hasAnnotation(aClass, Controller.class));
    }

    /**
     * 处理程序执行链
     * @param request   传进来请求
     * @return
     * @throws Exception
     */
    @Override
    public HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {
        String lookupPath = request.getRequestURI();
        //处理器方法
        HandlerMethod handler = mappingRegistry.getMethodByPath(lookupPath);
        if (Objects.isNull(handler)) {
            throw new NoHandlerFoundException(request);
        }
        return createHandlerExecutionChain(lookupPath, handler);
    }

    /**
     * 返回一个处理执行链 对象
     * 主要是遍历匹配拦截器 看看这个处理器controller能不能被拦截器支持
     *
     * @param lookupPath
     * @param handler
     * @return
     */
    private HandlerExecutionChain createHandlerExecutionChain(String lookupPath, HandlerMethod handler) {
        List<HandlerInterceptor> interceptors = this.interceptors.stream().//拦截器匹配支持的
                filter(mappedInterceptor -> mappedInterceptor.matches(lookupPath))
                .collect(Collectors.toList());
        return new HandlerExecutionChain(handler, interceptors);
    }
}
