package org.yuichi
import org.junit.Test
import org.yuichi.HeaderParser

class HeaderParserTest {
	def body = """\
package org.yuichi;
import java.util.ArrayList;

public static void main(String args[]) {
	System.out.println("HelloWorld");
}
"""
	@Test
	void testNoHeader() {
		HeaderParser hp = new HeaderParser()
		hp.parse(body)
		assert hp.header == ''
		assert hp.body == body
	}

	@Test
	void testOneLinerHeader() {
		def header = """\
//
// Copyright(C) Somebody
//
"""
		HeaderParser hp = new HeaderParser()
		hp.parse(header + body)
		assert hp.header == header
		assert hp.body == body
	}
	
	@Test
	void testBlockHeader() {
		def header = """\
/*
 * Copyright (C) Somebody
 */
"""
		HeaderParser hp = new HeaderParser()
		hp.parse(header + body)
		assert hp.header == header
		assert hp.body == body
	}
	
	@Test
	void testOnlinerBlockHeader() {
		def header = """\
/* Copyright (C) Somebody */
"""
		HeaderParser hp = new HeaderParser()
		hp.parse(header + body)
		assert hp.header == header
		assert hp.body == body
	}
	
	@Test
	void testHeaderWithTab() {
		def header = """\
	/*
	 *
	 */
"""
		HeaderParser hp = new HeaderParser()
		hp.parse(header + body)
		assert hp.header == header
		assert hp.body == body
	}
	
	@Test
	void testMultipleHeader() {
		def header = """\
// Copyright (C) Somebody 

/*
 * The following header
 */
"""
		HeaderParser hp = new HeaderParser()
		hp.parse(header + body)
		assert hp.header == header
		assert hp.body == body
	}
}
