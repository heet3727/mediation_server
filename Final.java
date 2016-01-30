//package config;

//Properties
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import java.io.File;
//import java.io.IOException;

//to convert string name into method
import java.lang.reflect.Method;


public class Final{

	public static boolean activity(String fName) throws Exception {
		
		Properties prop = new Properties();
		String filename = "config.properties";
		InputStream fileInput = Final.class.getClassLoader().getResourceAsStream(filename);
		if(fileInput==null){
				System.out.println("Sorry, unable to find " + filename);
			return false;
		}

		//load a properties file from class path, inside static method
		prop.load(fileInput);
		
		String inp = prop.getProperty("file_input");
		String suc = prop.getProperty("file_success");
		String inv = prop.getProperty("file_invalid");
		//String fName = prop.getProperty("file");
		
		fName = fName.substring(fName.indexOf('\\'), fName.length());
		//System.out.println(cool);
		
		inp = inp + "\\" + fName;//System.out.println(inp);
		suc = suc + "\\" + fName;//System.out.println(suc);
		inv = inv + "\\" + fName;//System.out.println(inv);
		
		int Condition = 0;
		
		String num = "1";//process begins
		int number = 0;
		boolean c = false;
		String par = "" ;
		String process = prop.getProperty(num);
		String className = null;
		//System.out.println("process " + process);
		
		while(process!=null)
		{
			//System.out.println("Entered into process " + num);
			Condition = Integer.parseInt(prop.getProperty(process));
			//System.out.println("process condition status " + Condition);
			className = "class" + num ;
			className = prop.getProperty(className) ;
			//System.out.println("className =  " + className);
			
			if(Condition>0){
				//Hard coded object
				//Main obj = new Main();
				
				Class myClass = Class.forName(className);
				Object obj = myClass.newInstance();
				//System.out.println(obj);
				
				java.lang.reflect.Method method;
				method = obj.getClass().getDeclaredMethod(process, String.class, String.class, String.class, String.class);
				c = (method.invoke(obj, inp, suc, inv, fName)!=0);
				//check = obj.parser(inp, suc, inv, fName);
				//System.out.println("success status " + check);
				
				File delF = new File(inp);
				boolean del = delF.delete();
				/*if(del){System.out.println("Deleted");}
				else{System.out.println("File not deleted...");}*/
				
				if(c){
					//System.out.println("Successfully completed process " + num);
				}else{
					System.out.println("Sorry...");
				}
					
			}else if(Condition==0){
				System.out.println("Process " + num + " is not given as activity");
			}else{
				System.out.println("Invalid process " + num + " input given from config.properties file");
			}
			
			number = Integer.parseInt(num);
			number++;
			num = Integer.toString(number);
			process = prop.getProperty(num);
		}
		
		fileInput.close();
	
		return true;
    }
	
}


