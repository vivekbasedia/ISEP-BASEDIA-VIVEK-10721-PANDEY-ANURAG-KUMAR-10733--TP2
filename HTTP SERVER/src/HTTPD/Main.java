/**
* THe main entrance for http server
* it calls connection halndler class
* @author  Vivek & anurag
* @version 1.0
* @since   2019-10-17 
*/


package HTTPD;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

public class Main 
{

	//This is a class in java which provides us functions to make a server on specific port address
	 ServerSocket serverSocket;
	 String Port;
	 String WebRoot;
	 String rootdir;
	
	//From this point our program begins
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		/*
		 *	This is equivalent to  
		 * Main main = new Main();
		 * main.runServer();
		*/
		
		new Main().runServer();	
		
	}
	
	public void runServer() throws Exception
	{
		// config.properties file contains fields like port number ,webroot , and the html file path
		File configFile = new File("config.properties");
	
		try
		{
		  
		    InputStream inputStream = new FileInputStream(configFile);
		    Properties props = new Properties();
		    props.load(inputStream);
		 
		     Port = props.getProperty("Port");
		     WebRoot = props.getProperty("WebRoot");
		     rootdir = props.getProperty("rootdir");
		 
		    System.out.print("Port name is: " + Port);
		    inputStream.close();
		    
		  //final ServerSocket server = new ServerSocket(Integer.parseInt(Port));
		System.out.println("\nServer Started");
		//serverSocket = new ServerSocket(8086);
		serverSocket	= new ServerSocket(Integer.parseInt(Port));
		
		//For 1.6 MultiTHreaded Server- call is commented due to issues
		//HttpConnection httpconn = new HttpConnection(Integer.parseInt(Port), rootdir);
		acceptRequests();
	
		
		}
		finally
		{
			//closing connection
		}
		}
	
	private void acceptRequests() throws Exception
	{
		while(true){
			Socket s = serverSocket.accept();
			
			ConnectionHandler ch = new ConnectionHandler(s,rootdir);
			
			ch.start();			
		}
	}
}
