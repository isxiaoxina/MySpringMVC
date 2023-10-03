package com.cxxx.mymvc.view;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

/**
 * @Package ：com.cxxx.mymvc.view
 * @ClassName：InternalResourceView
 * @date ：2022/12/2219:01
 * @Description：
 */

public class InternalResourceView extends AbstractView {
    private String url;

    public InternalResourceView(String url) {
        this.url = url;
    }

    @Override
    public String getContentTyp() {
        return "text/html";
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        exposeModelAsRequestAttributes(model, request);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(this.url);
        requestDispatcher.forward(request, response);

    }

    private void exposeModelAsRequestAttributes(Map<String, Object> model, HttpServletRequest request) {
        model.forEach((name, value) -> {
            if (Objects.nonNull(value)) {
                request.setAttribute(name, value);
            } else {
                request.removeAttribute(name);
            }
        });
    }

    public String getUrl() {
        return url;
    }




}
