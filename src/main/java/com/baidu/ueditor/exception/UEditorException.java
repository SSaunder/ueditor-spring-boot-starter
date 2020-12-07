package com.baidu.ueditor.exception;

import com.baidu.ueditor.configuration.UEditorBaseState;

public class UEditorException extends Exception {

	private UEditorBaseState state;

	public UEditorException(UEditorBaseState state) {
		this.state = state;
	}

	public UEditorException(String message, UEditorBaseState state) {
		super(message);
		this.state = state;
	}

	public UEditorException(String message, Throwable cause, UEditorBaseState state) {
		super(message, cause);
		this.state = state;
	}

	public UEditorException(Throwable cause, UEditorBaseState state) {
		super(cause);
		this.state = state;
	}

	public UEditorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, UEditorBaseState state) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.state = state;
	}

	public static UEditorException build(String message) {
		return new UEditorException(message, UEditorBaseState.build(message));
	}

	public static UEditorException build(String message, Throwable cause) {
		return new UEditorException(message, cause, UEditorBaseState.build(message));
	}

	public static UEditorException build(Throwable cause) {
		return new UEditorException(cause, UEditorBaseState.build(cause.getMessage()));
	}

	public UEditorBaseState getState() {
		return state;
	}
}
