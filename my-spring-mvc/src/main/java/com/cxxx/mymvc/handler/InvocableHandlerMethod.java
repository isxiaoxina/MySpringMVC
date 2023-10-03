package com.cxxx.mymvc.handler;

import com.cxxx.mymvc.handler.argument.HandlerMethodArgumentResolver;
import com.cxxx.mymvc.handler.argument.HandlerMethodArgumentResolverComposite;
import com.cxxx.mymvc.handler.returnvalue.HandlerMethodReturnValueComposite;
import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodParameter;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.convert.ConversionService;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Package ：com.cxxx.mymvc.handler
 * @ClassName：InvocableHandlerMethod
 * @date ：2022/11/1321:32
 * @Description：
 */
public class InvocableHandlerMethod extends HandlerMethod {
    ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
    private HandlerMethodArgumentResolverComposite argumentResolver;
    private HandlerMethodReturnValueComposite returnValueHandler;
    private ConversionService conversionService;


    /**
     * adapter  里面调用的构造器
     *
     * @param handlerMethod
     */
    public InvocableHandlerMethod(HandlerMethod handlerMethod,
                                  HandlerMethodArgumentResolverComposite argumentResolver,
                                  HandlerMethodReturnValueComposite returnValueHandler,
                                  ConversionService conversionService) {
        super(handlerMethod);
        this.argumentResolver = argumentResolver;
        this.returnValueHandler = returnValueHandler;
        this.conversionService = conversionService;

    }

    /**
     * 用于getExceptionHandlerMethod
     *
     * @param bean
     * @param method
     */
    public InvocableHandlerMethod(Object bean, Method method) {
        super(bean, method);
    }

    /**
     * 调用和处理
     *
     * @param request
     * @param response
     * @param modelAndViewContainer
     */
    public void invokeAndHandle(HttpServletRequest request, HttpServletResponse response, ModelAndViewContainer modelAndViewContainer, Object... providedArgs) {
        try {
            //TODO  返回的这个args还没搞明白   获取参数解析之后的参数值（可能）
            List<Object> args = this.getMethodArgumentValues(request, response, modelAndViewContainer, providedArgs);
            Object resultValue = doInvoke(args);  //***做方法调用之后返回结果值
            if (Objects.isNull(resultValue)) {   //做调用之后如果返回为空 ***
                if (response.isCommitted()) {//看响应是否已经成功提交
                    modelAndViewContainer.setRequestHandled(true);
                } else {
                    throw new IllegalStateException("Controller handler return value is null");
                }
            }
            //如果返回不为空 继续执行
            modelAndViewContainer.setRequestHandled(false);
            //断言 如果returnValueHandler没有值  就抛异常
            Assert.state(this.returnValueHandler!=null,"No return value handler");
            //如果想要得到方法的返回值类型 就传-1
            MethodParameter returnType = new MethodParameter(this.getMethod(), -1);
            //***核心 处理返回值。
            this.returnValueHandler.handleReturnValue(resultValue,returnType,modelAndViewContainer,request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /** 做方法调用
     * @param args 处理方法参数
     * @return
     */
    private Object doInvoke(List<Object> args) throws InvocationTargetException, IllegalAccessException {
       return this.getMethod().invoke(this.getBean(),args.toArray());

    }

    private List<Object> getMethodArgumentValues
            (HttpServletRequest request, HttpServletResponse response,
             ModelAndViewContainer modelAndViewContainer,
             Object... providedArgs) throws Exception {
        Assert.notNull(argumentResolver, "HandlerMethodArgumentResolver can not null");

        List<MethodParameter> parameters = this.getParameters();
        List<Object> args = new ArrayList<>(parameters.size());
        for (MethodParameter parameter : parameters) {
            //初始化参数名发现  可以获取方法参数名的key    调用这个方法之后  就可以获取到形参的key
            parameter.initParameterNameDiscovery(this.parameterNameDiscoverer);
            //arg 有可能为空  里面是循环判断providedArgs是不是这个参数类型的实例
            Object arg = findProvidedArgument(parameter, providedArgs);
            //如果arg不是空    就把arg添加进
            if (Objects.nonNull(arg)) {
                args.add(arg);
                continue;//强制下次循环
            }
            //主要执行！  参数解析之后 添加进这个list
            args.add(argumentResolver.resolveArgument(parameter, request, response, modelAndViewContainer, conversionService));
        }
        return args;
    }

    protected static Object findProvidedArgument(MethodParameter parameter, Object... providedArgs) {
        if (!ObjectUtils.isEmpty(providedArgs)) {
            for (Object providedArg : providedArgs) {
                //如果这个提供参数  是方法参数类型的实例  就返回真
                if (parameter.getParameterType().isInstance(providedArgs)) {
                    return providedArg;
                }
            }
        }
        return null;
    }
}
