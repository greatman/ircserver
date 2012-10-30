/*
 * This file is part of javaserver.
 *
 * Copyright (c) 2011-2012,
 * 							${project.organization.name} <${url}/>
 *
 * javaserver is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * javaserver is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with javaserver.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.greatmancode.javaserver.utils;

public final class Tools {

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
