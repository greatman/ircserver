package com.greatmancode.javaserver.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.greatmancode.javaserver.commands.CommandManager;

public class Connection extends Thread {

	private Socket socket;
	private String nickname, realName, host;
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Connection(Socket s) {
		this.socket = s;
		this.start();
	}

	public void run() {
		InetSocketAddress address = (InetSocketAddress) socket
	            .getRemoteSocketAddress();
	    String hostname = address.getAddress().getHostAddress();
	    System.out.println("Connection from host " + hostname);
	    InputStream socketIn;
		try {
			socketIn = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
		            socketIn));
		    String line;
		    while ((line = reader.readLine()) != null)
		    {
		    	String[] split = line.split(" ");
		        String cmd = split[0];
		        String[] args = new String[split.length - 1];
		        System.arraycopy(split, 1, args, 0, split.length - 1);
		        CommandManager.run(this, cmd, args);
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	public void send(Codec content) {
		try {
                /*content = content.replace("\n", "").replace("\r", "");
                content = content + "\r\n";*/
				
                socket.getOutputStream().write(content.toSend());
                socket.getOutputStream().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

}
