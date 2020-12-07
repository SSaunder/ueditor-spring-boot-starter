package com.baidu.ueditor.util;

import com.baidu.ueditor.configuration.UEditorDirective;
import com.baidu.ueditor.exception.UEditorException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Arrays;
import java.util.Optional;

public class UEditorAssert {

	public static void notFoundUploadData(String data) throws UEditorException {
		if (data == null || data.isEmpty()) {
			throw UEditorException.build(UEditorMessageUtils.get("com.baidu.ueditor.not-found-upload-data.message"));
		}
	}
	public static void notFoundUploadData(MultipartFile file) throws UEditorException {
		if (file.isEmpty()) {
			throw UEditorException.build(UEditorMessageUtils.get("com.baidu.ueditor.not-found-upload-data.message"));
		}
	}

	public static void thanMaxSize(long allowSize, long size) throws UEditorException {
		if (allowSize < size) {
			throw UEditorException.build(UEditorMessageUtils.get("com.baidu.ueditor.max-size.message"));
		}
	}

	public static void notAllowFileType(String[] allowFiles, String extensionName) throws UEditorException {
		Arrays.asList(allowFiles).stream().filter(e -> e.equals(extensionName)).findAny().orElseThrow(
				() -> UEditorException.build(UEditorMessageUtils.get("com.baidu.ueditor.not-allow-file-type.message")));
	}

	public static void notDirective(UEditorDirective directive) throws UEditorException {
		Arrays.asList(
				UEditorDirective.FILE_UPLOAD,
				UEditorDirective.IMAGE_UPLOAD,
				UEditorDirective.VIDEO_UPLOAD,
				UEditorDirective.SCRAWL_UPLOAD,
				UEditorDirective.CONFIG,
				UEditorDirective.FILE_MANAGER,
				UEditorDirective.IMAGE_MANAGER
		).stream().filter(e -> e.getDirective().equals(directive.getDirective())).findAny()
				.orElseThrow(() -> UEditorException.build(UEditorMessageUtils.get("com.baidu.ueditor.invalid-action.message")));
	}

	public static int parseStartError(HttpServletRequest request) throws UEditorException {
		try {
			return Integer.parseInt(
					Optional.ofNullable(request.getParameter("start")).orElseThrow(() ->
							UEditorException.build(UEditorMessageUtils.get("com.baidu.ueditor.parse-request-error.message")))
			);
		} catch (NumberFormatException e) {
			throw UEditorException.build(UEditorMessageUtils.get("com.baidu.ueditor.parse-request-error.message"));
		}
	}

	public static void notExist(File file) throws UEditorException {
		if (!file.exists())
			throw UEditorException.build(UEditorMessageUtils.get("com.baidu.ueditor.not-exist.message"));

	}

	public static void notDirectory(File file) throws UEditorException {
		if (!file.isDirectory()) {
			throw UEditorException.build(UEditorMessageUtils.get("com.baidu.ueditor.not-directory.message"));
		}
 	}
}
