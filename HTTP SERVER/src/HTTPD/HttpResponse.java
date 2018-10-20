/**
* Major functionality are dealt here
* MIME, Logger class call, Response generation and exception handler for bad and not found request
* @author  Vivek & anurag
* @version 1.0
* @since   2019-10-20 
*/


package HTTPD;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;


public class HttpResponse {

HttpRequest request;
	
	String response;
	String zip;
	String jpg;
	String html;
	String DefaultMimeType;
	String mimeType;
	//HttpRequest request;
	
	//String response;
	Date date = new Date();
	
	//String root = "C:/Users/vivek/Desktop/rep1";
	
	
	public HttpResponse(HttpRequest request,String root) throws IOException {
		// TODO Auto-generated constructor stub
		this.request = request;
		
//below code is for the MIME type identification and handling
		File MimeType = new File("MimeType.properties");
		
		InputStream inputStream = new FileInputStream(MimeType);
	    Properties props = new Properties();
	    props.load(inputStream);
	 
	    zip = props.getProperty(".zip");
	     html = props.getProperty(".html");
	     jpg = props.getProperty(".jpg");
	     DefaultMimeType=props.getProperty("DefaultMimeType");
	      	 
	    inputStream.close();
		
		File f = new File(root + request.filename);
		
		
		request.filename.endsWith("");
		 mimeType=DefaultMimeType;
		
	       if (request.filename.endsWith(".html") || request.filename.endsWith(".htm"))
          mimeType=html;
        else if (request.filename.endsWith(".jpg") || request.filename.endsWith(".jpeg"))
          mimeType=jpg;
        
        else if (request.filename.endsWith(".zip") )
            mimeType=zip;
        
        System.out.println("HTTP/1.0 200 OK\r\n"+
          "Content-type: "+mimeType+"\r\n\r\n");
        
        //ends MIME
        
        //HttpLogger class method call for logging info.
        HttpLog logger = new HttpLog();
        logger.add();
		
		try
		{
			//To read the file for sending response
			//FileInputStream fis = new FileInputStream(f);
			 if (request.filename.endsWith(".html") || request.filename.endsWith(".htm"))
		        {
		        	int length;
		        	length = request.filename.length();
		        	if (length != 10)
		        	{
		        		//go to exception block
		        	}
		        }
			DataInputStream dis = new DataInputStream(new FileInputStream(f));
			
			
			response = "HTTP/1.0 200 OK \r\n"; //version of http and 200 is status code which means all okay
			response = "Date:"+date.toString() + "\r\n";
			response  += "Server:JavaHttp/1.0 \r\n";
			response += "Content-Type:"+ mimeType+ "\r\n"; //response is in html format
			
			response += "\r\n"; //after blank line we have to append file data
			System.out.println(response);
			String s;
			//Reading Html File..........
			while ( (s = dis.readLine()) != null){
				response += s;
			}			
		}catch(FileNotFoundException e){
			//If we do not get the file then error 404 
			response = "HTTP / 1.0 404 Not found \r\n"; //version of http and 200 is status code which means all okay
			response = "Date:"+date.toString() + "\r\n";
			response  += "Server: JavaHttpd / 1.0 \r\n";
			response += "Content-Type:"+ mimeType+ "\r\n"; //response is in html format
			response += "\r\n"; //after blank line we have to append file data
			response += "<HEAD><TITLE>File not found </TITLE></HEAD>"; 
			response += "\r\n"; //after blank line we have to append file data
			response += "<BODY><H1>File not found </H1>";
			response += "\r\n"; //after blank line we have to append file data
			response += "The resource / rep1 / a_resource is not present on this server.";
			response += "\r\n"; //after blank line we have to append file data
			response += "</BODY>";
			response += "";
			System.out.println(response);	
			System.out.println("inside FileNotFoundException");
		}
		catch(Exception e)
		{
			//If any other error then 500 internal server error
			response = response.replace("200", "500");	
			System.out.println("Exception");
			//If we do not get the file then error 404 
			response = "HTTP/1.0 400 Bad Request \r\n"; //version of http and 200 is status code which means all okay
			response = "Date:"+date.toString() + "\r\n";
			response  += "Server: JavaHttpd / 1.0 \r\n";
			response += "Content-Type:"+ mimeType+ "\r\n"; //response is in html format
			response += "\r\n"; //after blank line we have to append file data
			response += "<HEAD><TITLE>Bad Request </TITLE></HEAD>"; 
			response += "\r\n"; //after blank line we have to append file data
			response += "<BODY><H1>Bad Request </H1>";
			response += "\r\n"; //after blank line we have to append file data
			response += "Votre navigateur Internet a envoyé une requête que ce serveur ne peut pas traiter.";
			response += "\r\n"; //after blank line we have to append file data
			response += "</BODY>";
			response += "";
			System.out.println(response);	
			System.out.println("outside FileNotFoundException");
		}
	}
}
