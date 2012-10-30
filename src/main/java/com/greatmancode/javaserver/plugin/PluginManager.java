package com.greatmancode.javaserver.plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.greatmancode.javaserver.Server;

public class PluginManager {

	private Map<String, Plugin> pluginList = new HashMap<String,Plugin>();
	public static final File PLUGIN_FOLDER = new File(".", "plugins");
	private URLClassLoader cl = null;

	public PluginManager() {
		PLUGIN_FOLDER.mkdirs();
		loadPlugins();
	}

	private void loadPlugins() {
		File[] fileList = PLUGIN_FOLDER.listFiles();
		for (File file : fileList) {
			if (file.getName().contains(".jar")) {
				try {
					URL url = file.toURI().toURL();
					URL[] urls = new URL[] { url };
					if (cl == null) {
						cl = URLClassLoader.newInstance(urls);
					}
					InputStream configurationFile = cl.getResourceAsStream(url.toString());
					if (configurationFile != null){
						DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
						DocumentBuilder db = dbf.newDocumentBuilder();
						Document doc = db.parse(configurationFile);
						doc.getDocumentElement().normalize();
						String pluginName = null, description = null, mainClass = null;
						NodeList nodeList = doc.getChildNodes();
						if (nodeList.getLength() == 4) {
							for (int i = 0; i < nodeList.getLength(); i++ ) {
								Node node = nodeList.item(i);
								if (node.getNodeName().equals("name")) {
									pluginName = node.getNodeValue();
								} else if (node.getNodeName().equals("description")) {
									description = node.getNodeValue();
								} else if (node.getNodeName().equals("mainClass")) {
									mainClass = node.getNodeValue();
								}
							}
							if (pluginName != null && description != null && mainClass != null) {
								Class<?> clazz = cl.loadClass(mainClass);
								if (Plugin.class.isAssignableFrom(clazz)) {
									Plugin pl = (Plugin) clazz.newInstance();
									pl.name = pluginName;
									pl.description = description;
									pl.dataFolder = new File(".", "plugins" + File.separator + pl.name);
									pl.dataFolder.mkdirs();
									pl.onEnable();
									pluginList.put(pl.name, pl);
								} else {
									
								}
								
							} else {
								Server.getServer().getLogger().severe("Missing information in the plugin configuration file for plugin " + file.getName());
							}
						} else {
							Server.getServer().getLogger().severe("Invalid plugin configuration for the plugin " + file.getName());
						}
					} else {
						Server.getServer().getLogger().severe("The file " + file.getName() + " doesn't have a plugin configuration file!");
					}

				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}

		
	}
}
