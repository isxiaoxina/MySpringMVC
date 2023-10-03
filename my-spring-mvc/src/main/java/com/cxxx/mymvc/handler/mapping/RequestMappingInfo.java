package com.cxxx.mymvc.handler.mapping;

import com.cxxx.mymvc.annotation.RequestMapping;
import com.cxxx.mymvc.http.RequestMethod;

/**
 * @Package ：com.cxxx.mymvc.handler.mapping
 * @ClassName：RequestMappingInfo
 * @date ：2022/10/290:58
 * @Description： 请求映射信息  对应controller中的方法信息
 */
public class RequestMappingInfo {
    private  String path; //存controller每个方法完整的url路径
    private RequestMethod httpMethod; //存请求方式 get或者post

    public RequestMappingInfo(String prefix, RequestMapping requestMapping) {
        this.path = prefix+requestMapping.path();//类url前缀加方法url路径
        this.httpMethod = requestMapping.method();//get/post
    }

    public String getPath() {
        return path;
    }

    public RequestMethod getHttpMethod() {
        return httpMethod;
    }
}
