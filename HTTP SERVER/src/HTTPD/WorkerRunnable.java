/**
* For multi threaded http server
*  getting called from HttpConnection class. due to some issues, the call is commented 
* @author  Vivek & anurag
* @version 1.0
* @since   2019-10-19 
*/


package HTTPD;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**

 */
public class WorkerRunnable implements Runnable{
	ServerSocket serverSocket;

    protected Socket clientSocket = null;
    protected String serverText   = null;
    String rootdir;

    public WorkerRunnable(Socket clientSocket, String serverText,String rootdir) {
        this.clientSocket = clientSocket;
        this.serverText   = serverText;
        this.rootdir = rootdir;
    }

    public void run() {
    	while(true){
    	try {
        	Socket s = serverSocket.accept();
			
        	
           ConnectionHandler ch;
		try {
			ch = new ConnectionHandler(s,rootdir);
			ch.start();	} 
		catch (Exception e) 
		{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
			
					
        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }
}}