package com.cxxx.mymvc.handler.argument;

import com.cxxx.mymvc.handler.ModelAndViewContainer;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.ConversionService;
import org.springframework.ui.Model;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Package ：com.cxxx.mymvc.handler.argument
 * @ClassName：ModelMethodArgumentResolver
 * @date ：2022/12/1521:14
 * @Description：
 */
public class ModelMethodArgumentResolver implements HandlerMethodArgumentResolver{
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Model.class.isAssignableFrom(parameter.getParameterType());  //前面是后面的父类就返回true  一样也是
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, HttpServletRequest request, HttpServletResponse httpServletResponse, ModelAndViewContainer container, ConversionService conversionService) throws Exception {
        //表达式为false  就抛异常   true就过
        Assert.state(container!=null,"ModelAndViewContainer is required for model exposure");

        return container.getModel();
    }
}
