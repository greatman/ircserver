package com.greatmancode.javaserver.utils;

import java.util.Arrays;

public class Tools {

	public static String makeNiceCommand(String line) {

		String[] parts = line.split(":");
		return parts[0].split(" ")[0];
	}
	
	public static String[] makeNiceArguments(String line) {
		String[] parts = line.split(":");
		String[] commandPart = parts[0].split(" ");
		String[] array1and2 = new String[commandPart.length];
		System.out.println(Arrays.toString(parts));
		System.out.println ("array1and2:" + array1and2.length);
		System.out.println("parts:" + parts.length);
		System.out.println("commandPart:" + commandPart.length);
		System.arraycopy(commandPart, 1, array1and2, 0, commandPart.length - 1);
		if (parts.length > 1) {
			System.arraycopy(parts, 1, array1and2, commandPart.length - 1, 1);
			
		}
		return array1and2;
	}
}
