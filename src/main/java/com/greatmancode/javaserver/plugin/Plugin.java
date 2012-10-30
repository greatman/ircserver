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
package com.greatmancode.javaserver.plugin;

import java.io.File;

import com.greatmancode.javaserver.Server;

public abstract class Plugin {

	protected File dataFolder;
	protected String description, name;
	
	public abstract void onDisable();
	public abstract void onEnable();
	
	public File getDataFolder() {
		return dataFolder;
	}
	
	public Server getServer() {
		return Server.getServer();
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getName() {
		return name;
	}
}
