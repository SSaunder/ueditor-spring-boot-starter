package com.baidu.ueditor.hanlder;

import com.baidu.ueditor.configuration.AbstractUEditorProperties;
import com.baidu.ueditor.configuration.UEditorDirective;
import com.baidu.ueditor.configuration.UEditorProperties;
import com.baidu.ueditor.exception.UEditorException;
import com.baidu.ueditor.util.UEditorUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

public class FileUploadHandler implements UEditorHandler {

    private final UEditorProperties properties;

    public FileUploadHandler(UEditorProperties properties) {
        this.properties = properties;
    }

    @Override
    public Object doAction(HttpServletRequest request, UEditorDirective directive) throws UEditorException {
        AbstractUEditorProperties config = this.properties.getProperties(directive);
        StandardMultipartHttpServletRequest multipartHttpServletRequest = (StandardMultipartHttpServletRequest) request;
        MultipartFile file = multipartHttpServletRequest.getFile(config.getFieldName());
        return UEditorUtils.saveFile(properties, file, directive);
    }
}
