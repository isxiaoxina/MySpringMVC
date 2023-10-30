package com.cxxx.mymvc;

import com.cxxx.mymvc.exception.HandlerExceptionResolver;
import com.cxxx.mymvc.handler.adapter.HandlerAdapter;
import com.cxxx.mymvc.handler.mapping.HandlerMapping;
import com.cxxx.mymvc.view.resolver.ViewResolver;
import java.util.Collection;
import javax.servlet.http.HttpServlet;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Package ：com.cxxx.mymvc
 * @ClassName：DispatcherServlet
 * @date ：2022/10/2322:45
 * @Description： 中央调度服务程序
 */
public class DispatcherServlet extends HttpServlet implements ApplicationContextAware {


    private ApplicationContext applicationContext;
    private HandlerMapping handlerMapping;//做方法解析  把方法参数拆开全部注册进容器  然后跟请求匹配的操作
    private HandlerAdapter handlerAdapter; // 把匹配好的方法做调用 调用里面1
    // 1对参数处理  参数解析器里面用了策略模式和组合模式
    // 2对返回值的处理  返回值处理器里面也用了策略和组合模式
    // 3  处理完成之后返回modelAndView：里面有是否完成请求或者其他带给前端的参数 TODO
    private ViewResolver viewResolver;
    private Collection<HandlerExceptionResolver> interceptorList;


    public void init() {
        this.handlerMapping = this.applicationContext.getBean(HandlerMapping.class);
        this.viewResolver = this.applicationContext.getBean(ViewResolver.class);
        this.handlerAdapter = this.applicationContext.getBean(HandlerAdapter.class);
        this.interceptorList = this.applicationContext.getBeansOfType(HandlerExceptionResolver.class).values();
    }

    public  void  DoDispatcher(){

    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
