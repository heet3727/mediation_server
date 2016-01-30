import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
 
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.io.File;

import java.text.*;
import java.util.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class Server
{	
	public static File folder;// = new File(prop.getProperty("file_input"));
	public static String successPath = null;
	public static String invalidPath = null;
	public static String successCreateFolder = null;
	public static String invalidCreateFolder = null;
	
	public static void main(String [] args) throws Exception
	{
		System.out.println("Entered into Server.java");
		
		Properties propLocal = new Properties();
		String filename = "config.properties";
		InputStream fileInput = Final.class.getClassLoader().getResourceAsStream(filename);
		propLocal.load(fileInput);
		
		folder = new File(propLocal.getProperty("file_input"));
		successPath = propLocal.getProperty("file_success");
		invalidPath = propLocal.getProperty("file_invalid");
		
		//Existing files & folders
		//System.out.println("Input folder path "+ folder.getAbsolutePath());
		listFilesForFolder(folder);
		//listFilesForFolder(folder);
		
		/* Watch Service/Thread program goes here */
		/* Watch Service/Thread program goes here */
		/* Watch Service/Thread program goes here */
		/* Watch Service/Thread program goes here */
		
		
		fileInput.close();//){System.out.println("Main property file closed successful");}
		
		Watcher w  = new Watcher();
		Thread t = new Thread(w,"Watcher Thread");
		t.start();
		
		
		
		
		System.out.println("Main property file closed successful");
            	
	}
	
	static String temp = "";
	
	public static void listFilesForFolder(final File folder) throws Exception
	{
		//for property file
		Properties propLocal = new Properties();
		String filename = "config.properties";
		InputStream fileInput = Final.class.getClassLoader().getResourceAsStream(filename);
		propLocal.load(fileInput);
		
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory())
			{
				//System.out.println("Reading files under the folder .. " + fileEntry.getAbsolutePath() + " " + fileEntry.getName());
				
				File directoryName = new File(fileEntry.getName());
				
				successCreateFolder = fileEntry.getAbsolutePath().replaceAll("input", successPath);
				invalidCreateFolder = fileEntry.getAbsolutePath().replaceAll("input", invalidPath);
				
				//System.out.println(successCreateFolder);
				//System.out.println(invalidCreateFolder);
				
				if (!directoryName.exists()){
				   new File(successCreateFolder).mkdirs();
				   new File(invalidCreateFolder).mkdirs();
				}
				
				listFilesForFolder(fileEntry);
			}
			else
			{
				if (fileEntry.isFile())
				{
					temp = fileEntry.getName();
					long difference  = System.currentTimeMillis() - fileEntry.lastModified();
					//System.out.println(difference);
					String hours = propLocal.getProperty("hours");
					int hh = Integer.parseInt(hours);
					hh = hh*3;//1 hour = 360000 milliseconds
					
					if(difference>hh)
					{	
						if ((temp.substring(temp.lastIndexOf('.') + 1, temp.length()).toLowerCase()).equals("log"))
						{
							Final fin = new Final();
							try
							{
								boolean check = fin.activity(fileEntry.toString());
								if(check){System.out.println("Success");}
								else{System.out.println("Failure");}
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
							System.out.println("File= " + folder.getAbsolutePath()+ "\\" + fileEntry.getName());
						}
					}
				}
			}
		}
		
		fileInput.close();
		//System.out.println("Secondary property file closed successful");
		//System.out.println();
	}
	
	public static class Watcher implements Runnable
	{
		public void run() 
		{
			try
			{
				while(true)
				{	
					System.out.println("In Thread");
					listFilesForFolder(folder);
					
					Thread.sleep(6000);
					
					
				}	
			}
			catch(Exception e)
			{
				System.out.println("Thread interrupted.");
			}
			System.out.println("Exiting Thread");
		}
		
	}

}