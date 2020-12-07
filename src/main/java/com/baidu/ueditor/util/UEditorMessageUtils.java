package com.baidu.ueditor.util;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class UEditorMessageUtils {

	private static MessageSource messageSource;

	public UEditorMessageUtils(MessageSource messageSource) {
		UEditorMessageUtils.messageSource = messageSource;
	}

	/**
	 * 获取单个国际化翻译值
	 */
	public static String get(String msgKey) {
		try {
			return messageSource.getMessage(msgKey, null, Locale.getDefault());
		} catch (Exception e) {
			return msgKey;
		}
	}
}
