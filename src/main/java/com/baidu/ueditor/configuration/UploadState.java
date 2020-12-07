package com.baidu.ueditor.configuration;

/**
 * 上传返回值
 */
public class UploadState extends UEditorBaseState {

	private String url;
	private String title;
	private String original;

	public UploadState(String state, String url, String title, String original) {
		super(state);
		this.url = url;
		this.title = title;
		this.original = original;
	}

	public String getUrl() {
		return url;
	}

	public String getTitle() {
		return title;
	}

	public String getOriginal() {
		return original;
	}

	public static UploadState build(String state, String url, String title, String original) {
		return new UploadState(state, url, title, original);
	}

	@Override
	public String toString() {
		return "UploadState{" +
				"url='" + url + '\'' +
				", title='" + title + '\'' +
				", original='" + original + '\'' +
				"} " + super.toString();
	}
}
