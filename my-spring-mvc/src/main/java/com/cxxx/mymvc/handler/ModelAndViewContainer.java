package com.cxxx.mymvc.handler;

import com.cxxx.mymvc.http.HttpStatus;
import lombok.Data;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import java.util.Objects;

/**
 * @Package ：com.cxxx.mymvc.handler.returnvalue
 * @ClassName：ModelAndViewContainer
 * @date ：2022/11/623:09
 * @Description：模型和视图容器
 */
@Data
public class ModelAndViewContainer {


    private Object view;
    private Model model;
    private HttpStatus status;
    private boolean requestHandled = false;


    public Object getView() {
        return view;
    }

    public String getViewName() {
        return this.view instanceof String ? (String) this.view : null;
    }

    public void setViewName(String viewName) {
        this.view = viewName;
    }

    public void setView(Object view) {
        this.view = view;
    }

    public Model getModel() {

        if (Objects.isNull(this.model)) {
            model = new ExtendedModelMap();
        }
        return model;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public HttpStatus getStatus() {
        return this.status;
    }


    public boolean isRequestHandled() {
        return requestHandled;
    }

    public void setRequestHandled(boolean requestHandled) {
        this.requestHandled = requestHandled;
    }
}
