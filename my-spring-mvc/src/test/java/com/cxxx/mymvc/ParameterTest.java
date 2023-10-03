package com.cxxx.mymvc;

import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodParameter;
import org.springframework.core.ParameterNameDiscoverer;
import sun.applet.Main;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @Package ：com.cxxx.mymvc
 * @ClassName：ParameterTest
 * @date ：2022/12/1419:09
 * @Description：
 */
public class ParameterTest {
    public static void main(String[] args) throws NoSuchMethodException {
        Method method = Main.class.getMethod("test1", String.class, Integer.class);
        MethodParameter nameParameter = new MethodParameter(method, 0);
        MethodParameter ageParameter = new MethodParameter(method, 1);

        // 打印输出：
        // 使用Parameter输出
        Parameter nameOriginParameter = nameParameter.getParameter();
        Parameter ageOriginParameter = ageParameter.getParameter();
        System.out.println("===================源生Parameter结果=====================");
        System.out.println(nameOriginParameter.getType() + "----" + nameOriginParameter.getName());
        System.out.println(ageOriginParameter.getType() + "----" + ageOriginParameter.getName());
        System.out.println("===================MethodParameter结果=====================");
        System.out.println(nameParameter.getParameterType() + "----" + nameParameter.getParameterName());
        System.out.println(ageParameter.getParameterType() + "----" + ageParameter.getParameterName());
        System.out.println("==============设置上ParameterNameDiscoverer后MethodParameter结果===============");
        ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
        nameParameter.initParameterNameDiscovery(parameterNameDiscoverer);
        ageParameter.initParameterNameDiscovery(parameterNameDiscoverer);
        System.out.println(nameParameter.getParameterType() + "----" + nameParameter.getParameterName());
        System.out.println(ageParameter.getParameterType() + "----" + ageParameter.getParameterName());
    }
    public Object test1(String name, Integer age) {
        String value = name + "---" + age;
        System.out.println(value);
        return value;
    }
}
