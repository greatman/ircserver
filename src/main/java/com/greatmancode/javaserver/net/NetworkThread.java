package com.greatmancode.javaserver.net;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.greatmancode.javaserver.commands.CommandManager;
import com.greatmancode.javaserver.utils.Tools;

public class NetworkThread extends Thread {

	private final User user;
	private final Socket socket;
	public Socket getSocket() {
		return socket;
	}

	public NetworkThread(User user, Socket socket) {
		this.user = user;
		this.socket = socket;
	}
	
	public void run() {
		InetSocketAddress address = (InetSocketAddress) socket.getRemoteSocketAddress();
	    user.setHost(address.getAddress().getHostAddress());
	    System.out.println("Connection from host " + user.getHost());
	    InputStream socketIn;
		try {
			socketIn = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
		            socketIn));
		    String line;
		    while ((line = reader.readLine()) != null)
		    {
		    	System.out.println("Received this from + " + user.getNickname() + ": " + line);
		    	String cmd = Tools.makeNiceCommand(line);
		    	String[] args = Tools.makeNiceArguments(line);
		    	System.out.println("test");
		        CommandManager.run(user, cmd, args);
		    }
		} catch (Exception e) {
			e.printStackTrace();
			user.disconnect();
			System.out.println(user.getNickname() + " disconnected!");
		}
	    
	}
	
}
