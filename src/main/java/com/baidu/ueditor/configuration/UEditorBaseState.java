package com.baidu.ueditor.configuration;

public class UEditorBaseState {

	private String state;

	public UEditorBaseState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public static UEditorBaseState build(String state) {
		return new UEditorBaseState(state);
	}

	@Override
	public String toString() {
		return "UEditorBaseState{" +
				"state='" + state + '\'' +
				'}';
	}
}
