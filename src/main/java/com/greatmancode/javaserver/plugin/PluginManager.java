package com.greatmancode.javaserver.plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
		System.out.println(PLUGIN_FOLDER.toString());
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
									Server.getServer().getLogger().severe("Invalid main class for the plugin " + file.getName() + "! It needs to extends Plugin!");
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
					jarFile.close();
				} catch (MalformedURLException e) {
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
