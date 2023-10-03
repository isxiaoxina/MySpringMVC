package com.cxxx.mymvc.handler.argument;

import com.alibaba.fastjson.JSON;
import com.cxxx.mymvc.annotation.RequestBody;
import com.cxxx.mymvc.exception.MissingServletRequestParameterException;
import com.cxxx.mymvc.handler.ModelAndViewContainer;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.ConversionService;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

/**
 * @Package ：com.cxxx.mymvc.handler.argument
 * @ClassName：RequestBodyMethodArgumentResolver
 * @date ：2022/12/1522:00
 * @Description：
 */
public class RequestBodyMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestBody.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, HttpServletRequest request, HttpServletResponse httpServletResponse, ModelAndViewContainer container, ConversionService conversionService) throws Exception {
        String httpMessageBody = this.getHttpMessageBody(request);//获取http请求体里面的json
        if (!StringUtils.isEmpty(httpMessageBody)) {
            //如果http请求体不为空 就json转换成对象返回
            return JSON.parseObject(httpMessageBody, parameter.getParameterType());
        }
        //如果请求体里面为空
        RequestBody requestBody = parameter.getParameterAnnotation(RequestBody.class);
        //先判断这个注解存不存在  不存在就返回null
        if (Objects.isNull(requestBody)) {
            return null;
        }
        //如果有注解  加上请求体为空就 报这个servlet请求参数丢失异常
        if (requestBody.required()) {
            throw new MissingServletRequestParameterException(parameter.getParameterName(),
                    parameter.getParameterType().getName());
        }
        return null;
    }

    private String getHttpMessageBody(HttpServletRequest request) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = request.getReader();
        char[] chars = new char[1024];
        int len;
        while (((len = reader.read(chars)) != -1)) {
            stringBuilder.append(chars, 0, len);
        }
        return stringBuilder.toString();
    }
}
