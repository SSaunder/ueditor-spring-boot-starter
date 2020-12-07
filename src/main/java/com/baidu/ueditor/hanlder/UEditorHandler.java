package com.baidu.ueditor.hanlder;

import com.baidu.ueditor.configuration.UEditorDirective;
import com.baidu.ueditor.exception.UEditorException;

import javax.servlet.http.HttpServletRequest;

public interface UEditorHandler {

    Object doAction(HttpServletRequest request, UEditorDirective directive) throws UEditorException;
}
