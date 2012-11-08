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
package com.greatmancode.javaserver;

import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelException;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.greatmancode.javaserver.channel.ChannelHandler;
import com.greatmancode.javaserver.commands.CommandManager;
import com.greatmancode.javaserver.commands.main.JoinCommand;
import com.greatmancode.javaserver.commands.main.KickCommand;
import com.greatmancode.javaserver.commands.main.LUsersCommand;
import com.greatmancode.javaserver.commands.main.ListCommand;
import com.greatmancode.javaserver.commands.main.ModeCommand;
import com.greatmancode.javaserver.commands.main.MotdCommand;
import com.greatmancode.javaserver.commands.main.NickCommand;
import com.greatmancode.javaserver.commands.main.NoticeCommand;
import com.greatmancode.javaserver.commands.main.PartCommand;
import com.greatmancode.javaserver.commands.main.PingCommand;
import com.greatmancode.javaserver.commands.main.PrivMsgCommand;
import com.greatmancode.javaserver.commands.main.QuitCommand;
import com.greatmancode.javaserver.commands.main.TopicCommand;
import com.greatmancode.javaserver.commands.main.UserCommand;
import com.greatmancode.javaserver.commands.main.WhoCommand;
import com.greatmancode.javaserver.commands.main.console.StopCommand;
import com.greatmancode.javaserver.event.EventManager;
import com.greatmancode.javaserver.event.ServerEventListener;
import com.greatmancode.javaserver.event.Source;
import com.greatmancode.javaserver.net.Codec;
import com.greatmancode.javaserver.net.IRCServerPipelineFactory;
import com.greatmancode.javaserver.plugin.PluginManager;
import com.greatmancode.javaserver.user.UserHandler;

public final class Server implements Source{
	
	public static final int DEFAULT_PORT = 6667;
	
	private final EventManager eventManager = new EventManager();
	/**
	 * Contains the list of currently used channels.
	 */
	private final ChannelHandler channelHandler = new ChannelHandler();

	
	private final CommandManager commandManager = new CommandManager();
	/**
	 * The server logger.
	 */
	private final Logger log = Logger.getLogger("ircserver");

	/**
	 * The user handler.
	 */
	private final UserHandler userHandler = new UserHandler();
	
	/**
	 * The version of the server.
	 */
	//TODO: Make it modified by Maven.
	public static final String VERSION = "JIrcServer-0.1";
	
	/**
	 * Contains the time of when the server started.
	 */
	public static final String LAUNCH_DATE = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
	
	/**
	 * Contains the server name.
	 */
	@Parameter(names = { "-servername"}, description = "The server name")
	private String serverName = "irc.network.net";

	private PluginManager pluginManager = null;
	
	private static Server instance = null;
	
	@Parameter(names = { "-port", "-p" }, description = "Port to bind to")
	private int port = DEFAULT_PORT;
	
	private Server() {
		instance = this;
		new ServerEventListener();
		getCommandManager().registerCommand("USER", new UserCommand());
		getCommandManager().registerCommand("NICK", new NickCommand());
		getCommandManager().registerCommand("JOIN", new JoinCommand());
		getCommandManager().registerCommand("MODE", new ModeCommand());
		getCommandManager().registerCommand("WHO", new WhoCommand());
		getCommandManager().registerCommand("PRIVMSG", new PrivMsgCommand());
		getCommandManager().registerCommand("QUIT", new QuitCommand());
		getCommandManager().registerCommand("PING", new PingCommand());
		getCommandManager().registerCommand("PART", new PartCommand());
		getCommandManager().registerCommand("LUSERS", new LUsersCommand());
		getCommandManager().registerCommand("KICK", new KickCommand());
		getCommandManager().registerCommand("TOPIC", new TopicCommand());
		getCommandManager().registerCommand("MOTD", new MotdCommand());
		getCommandManager().registerCommand("NOTICE", new NoticeCommand());
		getCommandManager().registerCommand("LIST", new ListCommand());
		getCommandManager().registerCommand("/STOP", new StopCommand());
		pluginManager = new PluginManager();
	}

	public static void main(String[] args) {
		new Server();
		JCommander commands = new JCommander(getServer());
		commands.parse(args);
		getServer().getLogger().info("Launching " + VERSION + " for the server " + getServer().getServerName() + ". Please wait.");
		ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
		bootstrap.setPipelineFactory(new IRCServerPipelineFactory());

		try {
			bootstrap.bind(new InetSocketAddress(getServer().port));
			getServer().getLogger().info("Binded to port " + getServer().port + "! Happy IRC! CTRL + C to stop the server.");
		} catch (ChannelException e) {
			getServer().getLogger().log(Level.ERROR, "Unable to bind to port " + getServer().port + "!", e.getCause());
			System.exit(0);
		}
		new Terminal();
	}

	/**
	 * Retrieve the server name
	 * 
	 * @return The server name
	 */
	public String getServerName() {
		return serverName;
	}

	/**
	 * Retrieve the User handler.
	 * 
	 * @return The user handler.
	 */
	public UserHandler getUserHandler() {
		return userHandler;
	}

	/**
	 * Retrieve the logger associated with the server.
	 * 
	 * @return The logger.
	 */
	public Logger getLogger() {
		return log;
	}

	/**
	 * Retrieve the channel handler.
	 * @return The channel handler.
	 */
	public ChannelHandler getChannelHandler() {
		return channelHandler;
	}
	
	public static Server getServer() {
		return instance;
	}

	public EventManager getEventManager() {
		return eventManager;
	}

	//TODO: This
	@Override
	public String getReprensentation() {
		return "";
	}

	public PluginManager getPluginManager() {
		return pluginManager;
	}
	
	public CommandManager getCommandManager() {
		return commandManager;
	}

	@Override
	public void send(Codec content) {
		// TODO Auto-generated method stub
		getLogger().info(content.encode());
	}

	@Override
	public String getNickname() {
		return "Server";
	}
}
