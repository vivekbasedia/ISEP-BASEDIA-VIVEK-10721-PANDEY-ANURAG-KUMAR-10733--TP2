/**
* connection handler makes a call to http request and response for generating browser response
* @author  Vivek & anurag
* @version 1.0
* @since   2019-10-18 
*/


package HTTPD;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ConnectionHandler extends Thread {
	
	Socket s;
	String rootdir;
	PrintWriter pw;
	
	BufferedReader br;
	
	public ConnectionHandler(Socket s,String rootdir) throws Exception{
		this.s = s;
		this.rootdir =rootdir;
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		pw = new PrintWriter(s.getOutputStream());
	}
	
	public void run(){
		try{
		
			String reqS = "";
		
			while(br.ready() || reqS.length() == 0){
				reqS += (char)br.read();
			}
			
			System.out.println(reqS);
			System.out.println("request from connection handler"+reqS);
			//Passing the request String to Request class for processing
			HttpRequest req = new HttpRequest(reqS);
			System.out.println("request from connection handler"+reqS);
			//Sending Request Object to HttpResponse Class
			HttpResponse res = new HttpResponse(req,rootdir);
			
			//write the final output to pw
			
			pw.write(res.response.toString());
			
			pw.flush();
			pw.close();
			br.close();
			s.close();
			
		}catch(Exception e)
		{
			System.out.println("ConnectionHandler Exception");	
		}
		
	}	

}
