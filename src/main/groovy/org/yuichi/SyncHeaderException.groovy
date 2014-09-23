/*
 * SyncHeader Plugin for Gradle
 * Copyright (C) 2014 Yuichi Murata
 */
package org.yuichi
import org.gradle.api.GradleScriptException

/**
 * Represents generic SyncHeader plugin exception 
 */
class SyncHeaderException extends GradleScriptException {
	/**
	 * Constructor
	 *
	 * @param message Exception message
	 * @param cause Root cause
	 */
	SyncHeaderException(String message, Throwable cause) {
		super(message, cause)
	}
	
	/**
	 * Constructor
	 *
	 * @param message Exception message
	 */
	SyncHeaderException(String message) {
		this(message, null)
	}
}

