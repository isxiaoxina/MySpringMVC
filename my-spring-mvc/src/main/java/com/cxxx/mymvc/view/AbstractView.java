package com.cxxx.mymvc.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Package ：com.cxxx.mymvc.view
 * @ClassName：AbstractView
 * @date ：2022/12/2218:24
 * @Description：
 */
public abstract class AbstractView implements View {

    @Override
    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
           this.prepareResponse(request,response);
           this.renderMergedOutputModel(model,request,response);
    }

    protected void prepareResponse(HttpServletRequest request, HttpServletResponse response) {

    }

    protected abstract void renderMergedOutputModel(
            Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception;


}
