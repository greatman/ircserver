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
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Level;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.greatmancode.javaserver.Server;

public class PluginManager {

	private Map<String, Plugin> pluginList = new HashMap<String, Plugin>();
	public static final File PLUGIN_FOLDER = new File(".", "plugins");
	private URLClassLoader cl = null;

	public PluginManager() {
		PLUGIN_FOLDER.mkdirs();
		loadPlugins();
	}

	public Plugin getPlugin(String name) {
		return pluginList.get(name);
	}

	private void loadPlugins() {
		File[] fileList = PLUGIN_FOLDER.listFiles();
		for (File file : fileList) {
			if (file.getName().contains(".jar")) {
				try {
					URL url = file.toURI().toURL();
					URL[] urls = new URL[] { url };
					JarFile jarFile = new JarFile(file);
					ZipEntry entry = jarFile.getEntry("plugin.xml");
					if (entry != null) {
						//Loading the file
						DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
						DocumentBuilder db = dbf.newDocumentBuilder();
						Document doc = db.parse(jarFile.getInputStream(entry));
						doc.getDocumentElement().normalize();
						
						String pluginName = null, description = null, mainClass = null;
						
						NodeList nodeList = doc.getElementsByTagName("plugin");
						if (nodeList.getLength() == 1) {
							Node node = nodeList.item(0);
							if (node.getNodeType() == Node.ELEMENT_NODE) {
								Element element = (Element) node;
								NodeList internal = null;
								internal = element.getElementsByTagName("name");
								if (internal.getLength() == 1) {
									
									pluginName = internal.item(0).getChildNodes().item(0).getNodeValue().trim();
								}
								internal = element.getElementsByTagName("description");
								if (internal.getLength() == 1) {
									description = internal.item(0).getChildNodes().item(0).getNodeValue().trim();
								}
								internal = element.getElementsByTagName("mainClass");
								if (internal.getLength() == 1) {
									mainClass = internal.item(0).getChildNodes().item(0).getNodeValue().trim();
								}
							}
							if (pluginName != null && description != null && mainClass != null) {
								cl = URLClassLoader.newInstance(urls);
								Class<?> clazz = cl.loadClass(mainClass);
								if (Plugin.class.isAssignableFrom(clazz)) {
									Server.getServer().getLogger().info("Loading " + pluginName + ".");
									Plugin pl = (Plugin) clazz.newInstance();
									pl.name = pluginName;
									pl.description = description;
									pl.dataFolder = new File(".", "plugins" + File.separator + pl.name);
									pl.dataFolder.mkdirs();
									pl.onEnable();
									pluginList.put(pl.name, pl);
									Server.getServer().getLogger().info(pluginName + " loaded!");
								} else {
									Server.getServer().getLogger().log(Level.ERROR, "Invalid main class for the plugin " + file.getName() + "! It needs to extends Plugin!");
								}
								
							} else {
								Server.getServer().getLogger().log(Level.ERROR, "Missing information in the plugin configuration file for plugin " + file.getName());
							}
						} else {
							Server.getServer().getLogger().log(Level.ERROR, "Invalid plugin configuration for the plugin " + file.getName());
						}
					} else {
						Server.getServer().getLogger().log(Level.ERROR, "The file " + file.getName() + " doesn't have a plugin configuration file!");
					}
					jarFile.close();
				} catch (MalformedURLException e) {
					Server.getServer().getLogger().log(Level.ERROR, "Malformed URL for plugin : " + file.getName(), e.getCause());
				} catch (ParserConfigurationException e) {
					Server.getServer().getLogger().log(Level.ERROR, "Unable to parse the plugin.xml for plugin : " + file.getName(), e.getCause());
				} catch (SAXException e) {
					Server.getServer().getLogger().log(Level.ERROR, "SAX exception for plugin : " + file.getName(), e.getCause());
				} catch (IOException e) {
					Server.getServer().getLogger().log(Level.ERROR, "IO exception for plugin : " + file.getName(), e.getCause());
				} catch (ClassNotFoundException e) {
					Server.getServer().getLogger().log(Level.ERROR, "Main class for plugin : " + file.getName() + " not found!", e.getCause());
				} catch (InstantiationException e) {
					Server.getServer().getLogger().log(Level.ERROR, "Unable to instantiate the mainClass for plugin : " + file.getName(), e.getCause());
				} catch (IllegalAccessException e) {
					Server.getServer().getLogger().log(Level.ERROR, "Illegal access for plugin : " + file.getName(), e.getCause());
				} catch (Exception e) {
					Server.getServer().getLogger().log(Level.ERROR, "General exception for plugin : " + file.getName(), e.getCause());
				}

			}
		}

	}
}
