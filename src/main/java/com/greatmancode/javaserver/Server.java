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

import com.greatmancode.javaserver.channel.ChannelHandler;
import com.greatmancode.javaserver.net.IRCServerPipelineFactory;
import com.greatmancode.javaserver.user.UserHandler;

public final class Server {
	
	/**
	 * Contains the list of currently used channels.
	 */
	private static final ChannelHandler CHANNEL_HANDLER = new ChannelHandler();

	/**
	 * The server logger.
	 */
	private static final Logger log = Logger.getLogger("ircserver");

	/**
	 * The user handler.
	 */
	private static final UserHandler USER_HANDLER = new UserHandler();
	
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
	private static String serverName = "";

	private Server() {

	}

	public static void main(String[] args) {

		serverName = "irc.greatmancode.com";
		ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
		bootstrap.setPipelineFactory(new IRCServerPipelineFactory());

		try {
			bootstrap.bind(new InetSocketAddress(6667));
			getLogger().info("Binded to port 6667! Happy IRC! CTRL + C to stop the server.");
		} catch (ChannelException e) {
			getLogger().log(Level.SEVERE, "Unable to bind to port 6667!", e.getCause());
			System.exit(0);
		}
	}

	/**
	 * Retrieve the server name
	 * 
	 * @return The server name
	 */
	public static String getServerName() {
		return serverName;
	}

	/**
	 * Retrieve the User handler.
	 * 
	 * @return The user handler.
	 */
	public static UserHandler getUserHandler() {
		return USER_HANDLER;
	}

	/**
	 * Retrieve the logger associated with the server.
	 * 
	 * @return The logger.
	 */
	public static Logger getLogger() {
		return log;
	}

	/**
	 * Retrieve the channel handler.
	 * @return The channel handler.
	 */
	public static ChannelHandler getChannelHandler() {
		return CHANNEL_HANDLER;
	}
}
