package com.greatmancode.javaserver;

import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelException;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import com.greatmancode.javaserver.net.IRCServerPipelineFactory;
import com.greatmancode.javaserver.net.UserHandler;

public final class App {
	public static final Map<String, Channel> CHANNEL_LIST = new HashMap<String, Channel>();
	private static final UserHandler USER_HANDLER = new UserHandler();
	public static final String VERSION = "JIrcServer-0.1";
	public static final String LAUNCH_DATE = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
	private static String serverName = "";

	private App() {

	}

	public static void main(String[] args) {

		serverName = "irc.greatmancode.com";
		ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
		bootstrap.setPipelineFactory(new IRCServerPipelineFactory());

		try {
			bootstrap.bind(new InetSocketAddress(6667));
			System.out.println("Binded to port 6667! Happy IRC! CTRL + C to stop the server.");
		} catch (ChannelException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public static String getServerName() {
		return serverName;
	}

	public static UserHandler getSessionHandler() {
		return USER_HANDLER;
	}
}
