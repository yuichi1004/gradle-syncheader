gradle-syncheader
=================

SyncHeader is a Gradle Plugin to synchronize header texts on java/groovy source code.

Features
-----------------

 - Insert headers to java/groovy source code
 - Overwrite existing header with interactive mode


Example
-----------------

Example gradle script is the following.

    apply plugin: 'syncheader'
    
    buildscript {
    	repositories {
    		mavenCentral()
    		jcenter()
    	}
    	dependencies {
    		classpath group: 'org.yuichi1004', name: 'gradle-syncheader', version: '0.1'
    	}
    }

Then specify 'sycnHeader' task.

    gradle syncHeader


Configuration
-----------------

    syncHeader {
    	noBody = 'warn'		// Specify the action if no body found
							// 'warn' or 'error' (default: warn)
    	existingHeader = 'ask'	// Specify the action for existing headers
								// 'error', 'ask' or 'overwrite' (default: ask)
    	headerFile = 'HEADER' // Specify header file naem (default: HEADER)
    	src = 'src'		// Specify src directory (default: src)
    }

License
------------------

This software is available under MIT License.


Copyright (C) Yuichi Murata

