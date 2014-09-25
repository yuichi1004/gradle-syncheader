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

License
------------------

This software is available under MIT License.
