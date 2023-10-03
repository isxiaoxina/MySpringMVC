package com.cxxx.mymvc.handler.argument;

import com.cxxx.mymvc.annotation.RequestParam;
import com.cxxx.mymvc.exception.MissingServletRequestParameterException;
import com.cxxx.mymvc.handler.ModelAndViewContainer;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.ConversionService;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @Package ：com.cxxx.mymvc.handler.argument
 * @ClassName：RequestParamMethodArgumentResolver
 * @date ：2022/12/1814:14
 * @Description：
 */
public class RequestParamMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //查看方法参数是不是带有这个注解声名
        return parameter.hasParameterAnnotation(RequestParam.class);
    }

    /**
     * 两种情况 如果参数有值  就转换类型并且赋值返回
     *
     * @param parameter
     * @param request
     * @param httpServletResponse
     * @param container
     * @param conversionService
     * @return 返回给形参赋值之后的对象
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, HttpServletRequest request, HttpServletResponse httpServletResponse, ModelAndViewContainer container, ConversionService conversionService) throws Exception {
        RequestParam param = parameter.getParameterAnnotation(RequestParam.class);
        //双重判断 如果这个为空 上面那个support已经判断过一次
        if (Objects.isNull(param)) {
            return null;
        }
        String value = request.getParameter(param.name());  //请求带来的参数值
        if (StringUtils.isEmpty(value)) {
            //如果请求体中的参数值是空  就给上默认值“”
            value = param.defaultValue();
        }
        if (!StringUtils.isEmpty(value)) {//改成else 也可以
            //把参数值类型 转换成参数的类型（Class）并且带值直接返回
            return conversionService.convert(value, parameter.getParameterType());
        }
        // 如果参数值为空并且没有设置这个required注解为false就抛异常
        if (param.required()) {
            throw new MissingServletRequestParameterException(parameter.getParameterName(),//参数名称如：name
                    parameter.getParameterType().getName());//参数类型名称：如integer
        }


        return null;
    }
}
