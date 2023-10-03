package com.cxxx.mymvc.handler.mapping;

import com.cxxx.mymvc.handler.HandlerMethod;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Package ：com.cxxx.mymvc.handler.mapping
 * @ClassName：MappingRegisty
 * @date ：2022/10/2720:26
 * @Description： 映射注册中心
 */
public class MappingRegistry {
    //路径和请求方式的容器  记录注解元信息
    private Map<String, RequestMappingInfo> pathMappingInfo = new ConcurrentHashMap<>();
    //处理器方法和处理器的容器
    private Map<String, HandlerMethod> pathHandlerMethod = new ConcurrentHashMap<>();

    /**
     * 添加进容器
     * @param requestMappingInfo
     * @param handler  这是controller 的bean
     * @param method
     */
    public void register(RequestMappingInfo requestMappingInfo, Object handler, Method method) {
        pathMappingInfo.put(requestMappingInfo.getPath(), requestMappingInfo);
        HandlerMethod handlerMethod = new HandlerMethod(handler, method);
        pathHandlerMethod.put(requestMappingInfo.getPath(), handlerMethod);
    }


    public Map<String, RequestMappingInfo> getPathMappingInfo() {
        return pathMappingInfo;
    }

    public Map<String, HandlerMethod> getPathHandlerMethod() {
        return pathHandlerMethod;
    }

    /**
     * 根据方法url路径获取处理方法的映射信息
     * @param path
     * @return
     */
    public  RequestMappingInfo getMappingByPath(String path){
        return this.pathMappingInfo.get(path);
    }


    public  HandlerMethod getMethodByPath(String path){
        return  this.pathHandlerMethod.get(path);
    }



}
