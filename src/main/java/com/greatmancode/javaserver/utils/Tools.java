package com.greatmancode.javaserver.utils;

public class Tools {

	public static createString(String[] string) {
		String ping = "";
		for (int i = 1; i < string.length; i++) {
			ping += string[i];
			if (i < string.length - 1) {
				ping += " ";
			}
		}
	}
}
