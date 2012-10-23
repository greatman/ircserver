package com.greatmancode.javaserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import com.greatmancode.javaserver.net.Connection;

/**
 * Hello world!
 *
 */
public class App 
{
	public static final HashMap<String, Channel> channelList = new HashMap<String, Channel>();
	public static void main( String[] args )
    {
		ServerSocket ss;
		try {
			ss = new ServerSocket(6667);
			while (true)
	        {
	            Socket s = ss.accept();
	            Connection jircs = new Connection(s);
	            Thread thread = new Thread(jircs);
	            thread.start();
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
}
