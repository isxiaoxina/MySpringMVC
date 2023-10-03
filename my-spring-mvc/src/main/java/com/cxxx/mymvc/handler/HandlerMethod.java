package com.cxxx.mymvc.handler;

import org.springframework.core.MethodParameter;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Package ：com.cxxx.mymvc.handler.returnvalue
 * @ClassName：handlerMethod
 * @date ：2022/10/2720:10
 * @Description： 处理器方法 （实体对象）
 */
public class HandlerMethod {
    private Object bean;  //处理器 controller 的bean
    private Class<?> beanType;
    private Method method;

    private List<MethodParameter> parameters;  //方法参数

    /**
     * 初始化    这里取到方法形参
     * @param bean
     * @param method  获取到执行链中已经跟请求路径匹配过的方法
     */
    public HandlerMethod(Object bean, Method method) {
        this.bean = bean;
        this.beanType = bean.getClass();
        this.method = method;
        //对象创建的时候给方法参数容器实例化
        this.parameters = new ArrayList<>();

        int parameterCount = method.getParameterCount();

        for (int index = 0; index < parameterCount;index++) {
            //给这个存方法参数的集合添加方法参数  第二个index是循环索引多个参数
            parameters.add(new MethodParameter(method,index));
        }
    }

    public HandlerMethod(HandlerMethod handlerMethod) {
        //（断言）验证参数是不是null   不是就报错提示
        Assert.notNull(handlerMethod,"HandlerMethod is required");
        this.bean=handlerMethod.bean;
        this.beanType=handlerMethod.beanType;
        this.method=handlerMethod.method;
        this.parameters=handlerMethod.parameters;

    }

    public Object getBean() {
        return bean;
    }

    public Class<?> getBeanType() {
        return beanType;
    }

    public Method getMethod() {
        return method;
    }

    public List<MethodParameter> getParameters() {
        return parameters;
    }
}
