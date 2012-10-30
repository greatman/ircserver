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
package com.greatmancode.javaserver.user;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import com.greatmancode.javaserver.Server;
import com.greatmancode.javaserver.channel.Channel;
import com.greatmancode.javaserver.channel.ChannelQuitReasons;
import com.greatmancode.javaserver.commands.CommandManager;
import com.greatmancode.javaserver.event.events.user.UserAuthedEvent;
import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.net.codecs.IsSupportCodec;
import com.greatmancode.javaserver.net.codecs.MyInfoCodec;
import com.greatmancode.javaserver.net.codecs.ServerLaunchCodec;
import com.greatmancode.javaserver.net.codecs.WelcomeCodec;
import com.greatmancode.javaserver.net.codecs.YourHostCodec;

public class User {

	private final org.jboss.netty.channel.Channel network;
	private String nickname, realName, host;
	private boolean loggedIn = false;
	private final List<UserModes> userModes = new ArrayList<UserModes>();

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
		confLoggedIn();
	}

	private void confLoggedIn() {
		if (!loggedIn && this.nickname != null && this.host != null) {
			Server.getServer().getEventManager().callEvent(new UserAuthedEvent(this));
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

	public User(org.jboss.netty.channel.Channel channel) {
		this.network = channel;
		this.host = ((InetSocketAddress) channel.getRemoteAddress()).getHostName();
	}

	public void send(Codec content) {
		network.write(content.toSend());
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
		for (Channel channel : Server.getServer().getChannelHandler().getUserChannels(this)) {
				channel.removeUser(this, ChannelQuitReasons.DISCONNECT);
		}
		Server.getServer().getUserHandler().removeUser(this);
		network.close();
	}

	public List<UserModes> getUserModes() {
		return userModes;
	}

}
