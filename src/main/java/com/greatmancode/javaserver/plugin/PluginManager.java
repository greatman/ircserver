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

	private Map<String, Plugin> pluginList = new HashMap<String, Plugin>();
	public static final File PLUGIN_FOLDER = new File(".", "plugins");
	private URLClassLoader cl = null;

	public PluginManager() {
		System.out.println(PLUGIN_FOLDER.toString());
		PLUGIN_FOLDER.mkdirs();
		loadPlugins();
	}

	private void loadPlugins() {
		File[] fileList = PLUGIN_FOLDER.listFiles();
		for (File file : fileList) {
			if (file.getName().contains(".jar")) {
				try {
					System.out.println("ENTERING FILE:" + file.getName());
					URL url = file.toURI().toURL();
					URL[] urls = new URL[] { url };
					URLClassLoader tempClassLoader = URLClassLoader.newInstance(urls);
					InputStream configurationFile = tempClassLoader.getResourceAsStream("plugin.xml");
					if (configurationFile != null) {
						DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
						DocumentBuilder db = dbf.newDocumentBuilder();
						Document doc = db.parse(configurationFile);
						doc.getDocumentElement().normalize();
						String pluginName = null, description = null, mainClass = null;
						NodeList nodeList = doc.getElementsByTagName("*");
						if (nodeList.getLength() == 4) {
							for (int i = 0; i < nodeList.getLength(); i++) {
								System.out.println(nodeList.item(i).getNodeName());
								Node node = nodeList.item(i);
								if (node.getNodeName().equals("name")) {
									pluginName = node.getNodeValue();
								} else if (node.getNodeName().equals("description")) {
									description = node.getNodeValue();
								} else if (node.getNodeName().equals("mainClass")) {
									mainClass = node.getNodeValue();
								}
								System.out.println(node.getNodeValue());
							}
							if (pluginName != null && description != null && mainClass != null) {
								cl = URLClassLoader.newInstance(urls);
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
							System.out.println(nodeList.getLength());
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
