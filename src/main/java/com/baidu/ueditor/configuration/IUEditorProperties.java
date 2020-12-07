package com.baidu.ueditor.configuration;

public interface IUEditorProperties {

	default boolean isBase64() {
		return false;
	}
	default String getFilenameFormat() {
		return null;
	}
	default String getUrlPrefix() {
		return null;
	}
	default String getFilename() {
		return null;
	}
	default String getFieldName() {
		return null;
	}
	default String[] getAllowFiles() {
		return new String[0];
	}
	default long getMaxSize() {
		return -1L;
	}
	default String[] getFilter() {
		return new String[0];
	}
	default int getSize() {
		return 20;
	}
	default String getDir() {
		return null;
	}
}
