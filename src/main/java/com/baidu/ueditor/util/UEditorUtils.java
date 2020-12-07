package com.baidu.ueditor.util;

import com.baidu.ueditor.configuration.AbstractUEditorProperties;
import com.baidu.ueditor.configuration.UEditorBaseState;
import com.baidu.ueditor.configuration.UEditorDirective;
import com.baidu.ueditor.configuration.UEditorProperties;
import com.baidu.ueditor.configuration.UploadState;
import com.baidu.ueditor.exception.UEditorException;
import org.springframework.util.DigestUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class UEditorUtils {

	public static UEditorBaseState saveFile(UEditorProperties properties, MultipartFile multipartFile, UEditorDirective directive) throws UEditorException {
		UEditorAssert.notFoundUploadData(multipartFile);
		AbstractUEditorProperties config = properties.getProperties(directive);
		UEditorAssert.thanMaxSize(config.getMaxSize(), multipartFile.getSize());
		String extensionName = UEditorUtils.getExtensionName(multipartFile.getOriginalFilename());
		UEditorAssert.notAllowFileType(config.getAllowFiles(), extensionName);
		String homeDir = properties.getHomeDir();
		String baseUrl = properties.getBaseUrl();
		String relativeDir = config.getFilenameFormat();
		String targetDir = homeDir + relativeDir;
		UEditorUtils.createDirectory(targetDir);
		String md5Name = UEditorUtils.md5(multipartFile);
		String filename = targetDir + md5Name + extensionName;
		UEditorUtils.saveFile(multipartFile, new File(filename));
		return UploadState.build(
				UEditorMessageUtils.get("com.baidu.ueditor.success.message"),
				baseUrl + relativeDir + md5Name + extensionName,
				md5Name + extensionName,
				multipartFile.getOriginalFilename()
		);
	}

	public static UEditorBaseState saveFile(UEditorProperties properties, byte[] bytes, UEditorDirective directive, String extensionName) throws UEditorException {
		AbstractUEditorProperties config = properties.getProperties(directive);
		UEditorAssert.thanMaxSize(config.getMaxSize(), bytes.length);
		String homeDir = properties.getHomeDir();
		String baseUrl = properties.getBaseUrl();
		String relativeDir = config.getFilenameFormat();
		String targetDir = homeDir + relativeDir;
		UEditorUtils.createDirectory(targetDir);
		String md5Name = UEditorUtils.md5(bytes);
		String filename = targetDir + md5Name + extensionName;
		try {
			FileCopyUtils.copy(bytes, new File(filename));
		} catch (IOException e) {
			throw UEditorException.build(UEditorMessageUtils.get("com.baidu.ueditor.io-error.message"));
		}
		return UploadState.build(
				UEditorMessageUtils.get("com.baidu.ueditor.success.message"),
				baseUrl + relativeDir + md5Name + extensionName,
				md5Name + extensionName,
				md5Name + extensionName);

	}

	public static String getExtensionName(String originalFilename) {
		return originalFilename.substring(originalFilename.lastIndexOf("."));
	}

	public static void createDirectory(String filepath) {
		File file = new File(filepath);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	public static void saveFile(MultipartFile multipartFile, File file) throws UEditorException {
		if (!file.exists()) {
			try {
				multipartFile.transferTo(file);
			} catch (IOException e) {
				throw UEditorException.build(UEditorMessageUtils.get("com.baidu.ueditor.io-error.message"));
			}
		}
	}

	public static String md5(MultipartFile multipartFile) throws UEditorException {
		try {
			return DigestUtils.md5DigestAsHex(multipartFile.getBytes());
		} catch (IOException e) {
			throw UEditorException.build(UEditorMessageUtils.get("com.baidu.ueditor.io-error.message"));
		}
	}

	public static String md5(byte[] bytes) {
		return DigestUtils.md5DigestAsHex(bytes);
	}
}
