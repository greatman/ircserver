package com.greatmancode.javaserver.net;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;

import com.greatmancode.javaserver.App;
import com.greatmancode.javaserver.Channel;
import com.greatmancode.javaserver.net.codecs.MotdContentCodec;
import com.greatmancode.javaserver.net.codecs.MotdEndCodec;
import com.greatmancode.javaserver.net.codecs.MotdStartCodec;
import com.greatmancode.javaserver.net.codecs.MyInfoCodec;
import com.greatmancode.javaserver.net.codecs.ServerLaunchCodec;
import com.greatmancode.javaserver.net.codecs.WelcomeCodec;
import com.greatmancode.javaserver.net.codecs.YourHostCodec;

public class User {

	private final NetworkThread network;
	private String nickname, realName, host;
	private boolean loggedIn = false;
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
		confLoggedIn();
	}

	private void confLoggedIn() {
		if (!loggedIn && this.nickname != null && this.host != null) {
			this.send(new WelcomeCodec(this));
			this.send(new YourHostCodec(this));
			this.send(new ServerLaunchCodec(this)); 
			this.send(new MyInfoCodec(this));
			
			//TODO: Read motd cmd.
			this.send(new MotdStartCodec(this));
			this.send(new MotdContentCodec(this));
			this.send(new MotdEndCodec(this));
			loggedIn = true;
		}
	}
	public User(Socket socket) {
		network = new NetworkThread(this, socket);
		network.start();
	}

	
	
	public void send(Codec content) {
		try {
			System.out.println("Sending a packet to : " + this.nickname);	
			network.getSocket().getOutputStream().write(content.toSend());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getReprensentation() {
		return nickname + "!" + realName + "@" + host;
	}
	
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
		confLoggedIn();
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
	public void disconnect() {
		for (Map.Entry<String, Channel> channel : App.channelList.entrySet())
		{
			if (channel.getValue().getUserList().contains(this)) {
				channel.getValue().removeUser(this, true);
			}
		}
		App.connectionList.remove(this);
		try {
			network.getSocket().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
