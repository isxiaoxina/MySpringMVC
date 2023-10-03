package com.cxxx.mymvc;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.servlet.http.HttpServlet;

/**
 * @Package ：com.cxxx.mymvc
 * @ClassName：DispatcherServlet
 * @date ：2022/10/2322:45
 * @Description： 中央调度服务程序
 */
public class DispatcherServlet extends HttpServlet implements ApplicationContextAware {


    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
