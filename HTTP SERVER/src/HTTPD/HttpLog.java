/**
* logger class generates a log file, info can be saved, at initial stage. file is getting generated.
* @author  Vivek & anurag
* @version 1.0
* @since   2019-10-19 
*/


package HTTPD;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class HttpLog 

{
	
	  public HttpLog()
	  {
		//constructor
	  }
	


//public void add (String address, String request, int status)
public void add ()
{
	//Creating the logfile log.txt 
	

	DateFormat date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 

	Calendar calender = Calendar.getInstance();

	Logger logger = Logger.getLogger("MyLog");  
	FileHandler fh;  

	try {  

	// This block configure the logger with handler and formatter  
	fh = new FileHandler("MyLogFile.log");  
	logger.addHandler(fh);
	SimpleFormatter formatter = new SimpleFormatter();  
	fh.setFormatter(formatter);  

	// the following statement is used to log any messages  
	logger.info("Mutli threaded user log");  


	} catch (SecurityException e) {  
	e.printStackTrace();  
	} catch (IOException e) {  
	e.printStackTrace();  
	}  

	//for testing 
	logger.info("Test for logger info"); 
}
}
