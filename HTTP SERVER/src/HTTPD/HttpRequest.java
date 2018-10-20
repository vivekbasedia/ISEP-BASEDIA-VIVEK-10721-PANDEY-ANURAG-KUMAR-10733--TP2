/**
* it manages the request
* @author  Vivek & anurag
* @version 1.0
* @since   2019-10-20 
*/



package HTTPD;
public class HttpRequest {
	
	String filename;

	public HttpRequest(String request) {
		// TODO Auto-generated constructor stub
		
		//First Spliting Level
		String lines[] = request.split("\n"); 
		
		System.out.println(lines[0]);
		
		//Second Splitting Level
		filename = lines[0].split(" ")[1];
		System.out.println("HttpRequest lines[0]" + lines[0]);
		
		System.out.println(filename);
		System.out.println("filename" + filename);
				
	}

}
