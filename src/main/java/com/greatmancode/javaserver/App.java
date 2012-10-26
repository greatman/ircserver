package com.greatmancode.javaserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.greatmancode.javaserver.net.User;

/**
 * Hello world!
 *
 */
public class App 
{
	public static final Map<String, Channel> channelList = new HashMap<String, Channel>();
	public static final List<User> connectionList = new ArrayList<User>();
	public static final String version = "JIrcServer-0.1";
	public static final String launchDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
	private static String serverName = "";
	
	public static void main( String[] args )
    {
		
		serverName = "irc.greatmancode.com";
		ServerSocket ss;
		try {
			ss = new ServerSocket(6667);
			while (true)
	        {
	            Socket s = ss.accept();
	            User jircs = new User(s);
	            connectionList.add(jircs);
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public static String getServerName() {
		return serverName;
	}
	
	public static User getUser(String username) {
		User conn = null;
		for (User entry: connectionList) {
			if (entry.getNickname().contains(username)) {
				conn = entry;
				break;
			}
		}
		return conn;
	}
}
