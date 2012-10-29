package com.greatmancode.javaserver;

import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelException;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.greatmancode.javaserver.channel.ChannelHandler;
import com.greatmancode.javaserver.event.EventManager;
import com.greatmancode.javaserver.event.ServerEventListener;
import com.greatmancode.javaserver.net.IRCServerPipelineFactory;
import com.greatmancode.javaserver.user.UserHandler;

public final class Server {
	
	public static final int DEFAULT_PORT = 6667;
	private final EventManager eventManager = new EventManager();
	/**
	 * Contains the list of currently used channels.
	 */
	private final ChannelHandler channelHandler = new ChannelHandler();

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

	
	private static Server instance = null;
	
	@Parameter(names = { "-port", "-p" }, description = "Port to bind to")
	private int port = DEFAULT_PORT;
	
	private Server() {
		instance = this;
		new ServerEventListener();
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
			getServer().getLogger().log(Level.SEVERE, "Unable to bind to port " + getServer().port + "!", e.getCause());
			System.exit(0);
		}
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
}
