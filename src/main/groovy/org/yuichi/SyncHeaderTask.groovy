package org.yuichi

import org.gradle.api.file.FileTree
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.tasks.TaskAction
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging

class SyncHeaderTask extends DefaultTask {
	Logger logger = Logging.getLogger('syncheader')
	String noBody = 'warn'
	String existingHeader = 'ask'
	String headerFile = 'HEADER'
	String src = 'src'
	
	boolean ask(File f, String header) {
		def msg = '\n'
		msg += 'File: ' + f + '\n'
		msg += 'Existing header found:\n'
		msg += header
		msg += '(o)verwite, (i)gnore, (s)top, or overwite (a)ll ? '
		def action
		while ((action != 'o') && (action != 'i') && (action != 's') &&
				(action != 'a')) {
			action = System.console().readLine(msg)
		}
		return action
	}

	void sync(File f, String newCopyright) {
		HeaderParser hp = new HeaderParser()
		hp.parse(f)
		
		if (hp.body == '') {
			switch (noBody) {
				case 'warn':
					logger.warn 'File ' + f + ' contains no body'
					break
				case 'error':
					throw new SyncFailedException(f, 'Contains no body')
					break
				default:
					throw new SyncHeaderException('Invalid value for ' +
						'noBody: ' + noBody)
			}
		}
		
		def overwite = true
		if (hp.header != '') {
			switch (existingHeader) {
				case 'error':
					throw new SyncFailedException(f, 'Header already exists')
					break;
				case 'ask':
					def action = ask(f, hp.header)
					switch (action) {
						case 'o':
							break
						case 'a':
							existingHeader = 'overwite'
							break;
						case 'i':
							overwite = false
							break;
						case 's':
							throw new SyncFailedException(f, 
									'Header already exists')
					}
					break
				case 'overwite':
					break;
				default:
					throw new SyncHeaderException( 
						'Unknown value for existingHeader: ' + existingHeader)
			}
		}

		if (overwite) {
			f.setText(newCopyright + hp.body)
			logger.debug String.format('File %s updated', f)
		}
	}

	@TaskAction
	void exec() {
		// Get headerfile 
		File hf = project.file(headerFile)
		if (!hf.exists())
			throw new SyncHeaderException(String.format(
					'Header file %s not found', hf))
		def header = '/*'
		hf.each{ line ->
			header += ' * ' + line + '\n'
		}
		header += ' */\n'

		// Traverse tree
		FileTree tree = project.fileTree(dir: src)
		tree.include '**/*.java'
		tree.include '**/*.groovy'
		tree.each{File f ->
			sync(f, header)
		}
	}
}
