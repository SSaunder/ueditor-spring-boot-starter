package com.baidu.ueditor.hanlder;

import com.baidu.ueditor.configuration.UEditorDirective;
import com.baidu.ueditor.exception.UEditorException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class UEditorContextFactory {

    private Map<UEditorDirective, UEditorHandler> maps = new HashMap<>();

    public UEditorContextFactory(Map<UEditorDirective, UEditorHandler> maps) {
        this.maps = maps;
    }

    public Object doAction(HttpServletRequest request, UEditorDirective directive) throws UEditorException {
        return this.maps.get(directive).doAction(request, directive);
    }
}
