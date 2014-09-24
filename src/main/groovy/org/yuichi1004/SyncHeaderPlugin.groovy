/*
 * SyncHeader Plugin for Gradle
 * Copyright (C) 2014 Yuichi Murata
 */
package org.yuichi

import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging

/**
 * SyncHeader plugin for gradle 
 */
class SyncHeaderPlugin implements Plugin<Project> {
	Logger logger = Logging.getLogger('syncheader')

	/**
	 * Apply SyncHeader plugin to the project
	 * @param project Project to apply the plugin
	 */
	void apply(Project project) {
		project.task('syncHeader', type: SyncHeaderTask) 
	}
}


