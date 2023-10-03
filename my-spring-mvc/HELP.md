# Getting Started
可能大家对于Handler，HandlerMethod，HandlerMapping，HandlerAdapter有疑惑，到底有啥区别？
可以这样理解：
Handler是用来干活的工具；个人理解是controller类
HandlerMapping用于根据URL找到相应的干活工具； controller'类里面方法的各种处理映射 比如url和get或post请求方式
HandlerAdapter是使用工具干活的人；
在SpringMVC中Handler是一个抽象的统称，HandlerMethod只代表一种Handler

### 手写简易mvc库

1.客户端发请求到DispatcherServlet
2.DispatcherServlet 作为中间控制类给HandlerMapping发送url请求让HM找处理器（也就是我们业务的controller层代码）
3.返回HandlerExecutionChain（包含HandlerInterceptor和handler） 处理器执行链里面包含处理器拦截器和处理器
###首先我们需要先开发`HandlerMapping`，`HandlerMapping`的主要作用是根据请求的url查找`Handler`，其中涉及到的组件有
(1). HandlerMethod 1
(2). MappingRegistry 1
(3). HandlerInterceptor 1
(4). RequestMappingHandlerMapping 请求处理程序映射 就是把controller方法各种信息注册进容器

然后是开发HandlerAdapter，HandlerAdapter的主要作用是按照特定规则（HandlerAdapter要求的规则）去执行Handler，其中涉及到的组件有
(1). HandlerMethodArgumentResolver
(2). HandlerMethodReturnValueHandler
(3). ModelAndView
(4).RequestMappingHandlerAdapter