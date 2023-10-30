package com.cxxx.mymvc.view.resolver;

import com.silently9527.smartmvc.view.View;

public interface ViewResolver {
    View resolveViewName(String viewName) throws Exception;
}
