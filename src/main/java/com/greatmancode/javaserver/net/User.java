package com.greatmancode.javaserver.net;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.greatmancode.javaserver.App;
import com.greatmancode.javaserver.Channel;
import com.greatmancode.javaserver.UserModes;
import com.greatmancode.javaserver.commands.CommandManager;
import com.greatmancode.javaserver.net.codecs.IsSupportCodec;
import com.greatmancode.javaserver.net.codecs.MyInfoCodec;
import com.greatmancode.javaserver.net.codecs.ServerLaunchCodec;
import com.greatmancode.javaserver.net.codecs.WelcomeCodec;
import com.greatmancode.javaserver.net.codecs.YourHostCodec;

public class User {

	private final NetworkThread network;
	private String nickname, realName, host;
	private boolean loggedIn = false;
	private final List<UserModes> userModes = new ArrayList<UserModes>();
	
	public User(Socket socket) {
		network = new NetworkThread(this, socket);
		network.start();
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
		confLoggedIn();
	}

	private void confLoggedIn() {
		if (!loggedIn && this.nickname != null && this.host != null) {
			network.setName("Thread for " + this.nickname);
			this.send(new WelcomeCodec(this));
			this.send(new YourHostCodec(this));
			this.send(new ServerLaunchCodec(this)); 
			this.send(new MyInfoCodec(this));
			this.send(new IsSupportCodec(this));
			CommandManager.run(this, "LUSERS", null);
			CommandManager.run(this, "MOTD", null);
			loggedIn = true;
		}
	}
	
	public void send(Codec content) {
		try {
			System.out.println("Sending a packet to : " + this.nickname);	
			network.getSocket().getOutputStream().write(content.toSend());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.disconnect();
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
		for (Map.Entry<String, Channel> channel : App.CHANNEL_LIST.entrySet())
		{
			if (channel.getValue().getUserList().containsKey(this)) {
				channel.getValue().removeUser(this, true);
			}
		}
		App.CONNECTION_LIST.remove(this);
		try {
			network.getSocket().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<UserModes> getUserModes() {
		return userModes;
	}

}
