/*
 * SyncHeader Plugin for Gradle
 * Copyright (C) 2014 Yuichi Murata
 */
package org.yuichi

class HeaderParser {
	def state
	def header
	def body
	
	def parse(text) {
		state = 'header'
		header = ''
		body = ''
		text.eachLine{line ->
			parseLine(line)
		}
	}

	def parseLine(line) {
		switch (state) {
			case 'header':
				if (isComment(line) || isBlank(line)) {
					header += line + "\n"
				} else if (isBlockStart(line)) {
					def m = line =~ /(^\s*\/\*)(.*)$/
					header += m[0][1]
					state = 'commentblock'
					parseLine(m[0][2])
				} else {
					state = 'body'
					body += line + "\n"
				}
				break

			case 'commentblock':
				if (isBlockEnd(line)) {
					def m = line =~ /(^.*\*\/)(.*)/
					header += m[0][1]
					state = 'header'
					parseLine(m[0][2])
				} else {
					header += line + "\n"
				}
				break

			case 'body':
				body += line + "\n"
				break
		}

	}

	def isComment(text) {
		return text ==~ /^\s*\/\/.*$/
	}
	
	def isBlank(text) {
		return text ==~ /^\s*$/
	}

	def isBlockStart(text) {
		return text ==~ /^\s*\/\*.*$/
	}

	def isBlockEnd(text) {
		return text ==~ /^.*\*\/.*$/
	}
}

