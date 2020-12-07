package com.baidu.ueditor.hanlder;

import com.baidu.ueditor.configuration.UEditorDirective;
import com.baidu.ueditor.configuration.UEditorProperties;

import javax.servlet.http.HttpServletRequest;

public class ConfigHandler implements UEditorHandler {

    private UEditorProperties properties;

    public ConfigHandler(UEditorProperties properties) {
        this.properties = properties;
    }

    @Override
    public Object doAction(HttpServletRequest request, UEditorDirective directive) {
        return properties.getConfig();
    }
}
