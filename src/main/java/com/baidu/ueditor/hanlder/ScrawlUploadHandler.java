package com.baidu.ueditor.hanlder;

import com.baidu.ueditor.configuration.AbstractUEditorProperties;
import com.baidu.ueditor.configuration.UEditorDirective;
import com.baidu.ueditor.configuration.UEditorProperties;
import com.baidu.ueditor.exception.UEditorException;
import com.baidu.ueditor.util.UEditorAssert;
import com.baidu.ueditor.util.UEditorUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

public class ScrawlUploadHandler implements UEditorHandler {

	private static final String JPEG = ".jpg";

	private final UEditorProperties properties;

	public ScrawlUploadHandler(UEditorProperties properties) {
		this.properties = properties;
	}

	@Override
	public Object doAction(HttpServletRequest request, UEditorDirective directive) throws UEditorException {
		AbstractUEditorProperties config = this.properties.getProperties(directive);
		String fieldName = config.getFieldName();
		String fileData = request.getParameter(fieldName);
		UEditorAssert.notFoundUploadData(fileData);
		byte[] data = Base64.getDecoder().decode(fileData);
		return UEditorUtils.saveFile(properties, data, directive, JPEG);
	}
}
