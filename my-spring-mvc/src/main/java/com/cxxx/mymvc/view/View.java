package com.cxxx.mymvc.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Package ：com.cxxx.mymvc.view
 * @ClassName：View
 * @date ：2022/12/2021:30
 * @Description：TODO  先不写这个视图解析器 前后分离不需要
 */
public interface View {
    default String getContentTyp() {
        return null;
    }

    void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception;


}
