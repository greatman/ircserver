package com.greatmancode.javaserver.utils;

public class Tools {

	private Tools() {
		throw new UnsupportedOperationException();
	}
	
	public static String makeNiceCommand(String line) {

		String[] parts = line.split(":");
		return parts[0].split(" ")[0];
	}

	public static String[] makeNiceArguments(String line) {
		String[] parts = line.split(":", 2);
		String[] commandPart = parts[0].split(" ");
		String[] array1and2 = new String[commandPart.length];
		if (parts.length == 1) {
			array1and2 = new String[commandPart.length - 1];
		}
		System.arraycopy(commandPart, 1, array1and2, 0, commandPart.length - 1);
		if (parts.length > 1) {
			System.arraycopy(parts, 1, array1and2, commandPart.length - 1, 1);

		}
		return array1and2;
	}
}
