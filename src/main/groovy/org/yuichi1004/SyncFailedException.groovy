/*
 * SyncHeader Plugin for Gradle
 * Copyright (C) 2014 Yuichi Murata
 */
package org.yuichi

/**
 * Represents sync task failure for some particular java/groovy file
 */
class SyncFailedException extends SyncHeaderException {
	/**
	 * Constructor
	 * 
	 * @param file File where exception happens
	 * @param message Exceptoin message
	 * @param cause Nested exception
	 */
	SyncFailedException(File file, String message, Throwable cause) {
		super(String.format('SyncHeader failed on: %s\n - Reason: %s', 
			file, message), cause)
	}
	
	/**
	 * Constructor
	 * 
	 * @param file File where exception happens
	 * @param message Exceptoin message
	 */
	SyncFailedException(File file, String message) {
		super(String.format('SyncHeader failed on: %s\n - Reason: %s', 
			file, message), null)
	}
}

