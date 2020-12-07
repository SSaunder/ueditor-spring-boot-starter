package com.baidu.ueditor.configuration;

import java.util.Collections;
import java.util.List;

public class ListDirectoryState extends UEditorBaseState {

	private List<FileItem> list = Collections.emptyList();
	private int start;
	private int total;

	public ListDirectoryState(String state) {
		super(state);
	}

	public ListDirectoryState(String state, List<FileItem> list, int start, int total) {
		super(state);
		this.list = list;
		this.start = start;
		this.total = total;
	}

	public List<?> getList() {
		return list;
	}

	public int getStart() {
		return start;
	}

	public int getTotal() {
		return total;
	}

	@Override
	public String toString() {
		return "ListDirectoryState{" +
				"list=" + list +
				", start=" + start +
				", total=" + total +
				"} " + super.toString();
	}

	public static class FileItem {
		private String url;

		public FileItem(String url) {
			this.url = url;
		}

		public String getUrl() {
			return url;
		}
	}
}
