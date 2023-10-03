package com.cxxx.mymvc.handler.argument;

import com.cxxx.mymvc.handler.ModelAndViewContainer;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.ConversionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @Package ：com.cxxx.mymvc.handler.argument
 * @ClassName：HandlerMethodArgumentResolverCompostie
 * @date ：2022/11/1322:26
 * @Description：
 */
public class HandlerMethodArgumentResolverComposite implements HandlerMethodArgumentResolver {

    private List<HandlerMethodArgumentResolver> argumentResolvers = new ArrayList<>();

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return true;
    }


    /**
     * 解析处理器方法参数
     *
     * @param parameter
     * @param request
     * @param response
     * @param container
     * @return
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, HttpServletRequest request, HttpServletResponse response,
                                  ModelAndViewContainer container,
                                  ConversionService conversionService) throws Exception {
        for (HandlerMethodArgumentResolver argumentResolver : argumentResolvers) {
            if (argumentResolver.supportsParameter(parameter)) {
                //返回值类似
                return argumentResolver.resolveArgument(parameter, request, response, container, conversionService);
            }
        }
        throw new IllegalArgumentException("Unsupported parameter type [" +
                parameter.getParameterType().getName() + "]. supportsParameter should be called first.");
    }

    public void addResolver(HandlerMethodArgumentResolver resolver) {
        this.argumentResolvers.add(resolver);
    }

    public void addResolver(HandlerMethodArgumentResolver... resolvers) {
        Collections.addAll(this.argumentResolvers, resolvers);
    }

    public void addResolver(Collection<HandlerMethodArgumentResolver> resolvers) {
        this.argumentResolvers.addAll(resolvers);
    }

    public void clear() {
        this.argumentResolvers.clear();
    }
}
