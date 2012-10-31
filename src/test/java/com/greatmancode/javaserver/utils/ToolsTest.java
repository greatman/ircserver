package com.greatmancode.javaserver.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class ToolsTest {

	@Test
	public void makeNiceCommand() {
		assertEquals(Tools.makeNiceCommand("cmd is really kewl"), "cmd");
		assertEquals(Tools.makeNiceCommand("cmd is rea\\lly :kewlds asdf"), "cmd");
		assertEquals(Tools.makeNiceCommand(null), null);
		assertEquals(Tools.makeNiceCommand(""), "");
	}
}
