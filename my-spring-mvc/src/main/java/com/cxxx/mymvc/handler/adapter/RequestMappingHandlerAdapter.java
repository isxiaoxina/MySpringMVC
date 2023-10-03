package com.cxxx.mymvc.handler.adapter;

import com.cxxx.mymvc.ModelAndView;

import com.cxxx.mymvc.handler.HandlerMethod;
import com.cxxx.mymvc.handler.InvocableHandlerMethod;
import com.cxxx.mymvc.handler.ModelAndViewContainer;
import com.cxxx.mymvc.handler.argument.HandlerMethodArgumentResolver;
import com.cxxx.mymvc.handler.argument.HandlerMethodArgumentResolverComposite;
import com.cxxx.mymvc.handler.returnvalue.HandlerMethodReturnValueComposite;
import com.cxxx.mymvc.handler.returnvalue.HandlerMethodReturnValueHandler;
import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.convert.ConversionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Package ：com.cxxx.mymvc.handler.adapter
 * @ClassName：RequestMappingHandlerAdapter
 * @date ：2022/11/1316:07
 * @Description： 处理器适配器
 */
public class RequestMappingHandlerAdapter implements HandlerAdapter, InitializingBean {


    private List<HandlerMethodArgumentResolver> handlerMethodArgumentResolverList;
    private HandlerMethodArgumentResolverComposite handlerMethodArgumentResolverComposite;

    private List<HandlerMethodReturnValueHandler> handlerMethodReturnValueHandlerList;
    private HandlerMethodReturnValueComposite returnValueComposite;


    private ConversionService conversionService;

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws Exception {
        //创建可调用的处理器方法
        InvocableHandlerMethod invocableHandlerMethod = createInvocableHandlerMethod(handlerMethod);
        ModelAndViewContainer modelAndViewContainer = new ModelAndViewContainer();

        //调用处理器方法
        invocableHandlerMethod.invokeAndHandle(request, response, modelAndViewContainer);

        return getModelAndView(modelAndViewContainer);
    }

    /**
     * 创建一个可调用的程序处理方法
     *
     * @param handlerMethod
     * @return
     */
    private InvocableHandlerMethod createInvocableHandlerMethod(HandlerMethod handlerMethod) {
        return new InvocableHandlerMethod(handlerMethod,
                this.handlerMethodArgumentResolverComposite,
                this.returnValueComposite,
                this.conversionService
        );


    }

    private ModelAndView getModelAndView(ModelAndViewContainer modelAndViewContainer) {
        if (modelAndViewContainer.isRequestHandled()) {//先判断请求处理了没
            //本次请求已经处理完成
            return null;
        }


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setStatus(modelAndViewContainer.getStatus());
        modelAndView.setModel(modelAndViewContainer.getModel());
        modelAndView.setView(modelAndViewContainer.getView());
        return modelAndView;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
