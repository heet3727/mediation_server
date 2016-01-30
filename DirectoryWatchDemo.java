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
 
public class DirectoryWatchDemo 
{
	public static File folder = new File("D:/take it");
	
    public static void main(String[] args) 
	{
        try 
		{
			WatchService watcher = FileSystems.getDefault().newWatchService();
            Path dir = Paths.get("D:\\take it");
            dir.register(watcher, ENTRY_CREATE, ENTRY_MODIFY);
             
            System.out.println("Watch Service registered for dir: " + dir.getFileName());
			
			System.out.println("How many files are already there "+ folder.getAbsolutePath());
			listFilesForFolder(folder);
            
             
            while (true) 
			{
                WatchKey key;
                try 
				{
                    key = watcher.take();
                } 
				catch (InterruptedException ex) 
				{
                    return;
                }

                 
                for (WatchEvent<?> event : key.pollEvents()) 
				{
                    WatchEvent.Kind<?> kind = event.kind();
                     
                    @SuppressWarnings("unchecked")
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    Path fileName = ev.context();
                    //String p = folder.getAbsolutePath()+ "\\" +fileName;
					if((fileName.toString()).contains("."))
					{
						System.out.println("snadkf");
					}
					else
					{		
						System.out.println(kind.name() + ": " + fileName);
					}
					//System.out.println("New file has been arrived: "+fileName);
					
				}
				
				/*if (kind == ENTRY_MODIFY && fileName.toString().equals("DirectoryWatchDemo.java")) 
					{
                        System.out.println("My source file has changed!!!");
                    }
                }*/
                 
                boolean valid = key.reset();
                if (!valid) 
				{
                    break;
                }
            }
        } 
		catch (IOException ex) 
		{
            System.err.println(ex);
        }
    }
	static String temp = "";
	public static void listFilesForFolder(final File folder) 
	{
		for (final File fileEntry : folder.listFiles()) 
		{
		  
			if (fileEntry.isDirectory()) 
			{
				
				System.out.println("Reading files under the folder "+ fileEntry);
				listFilesForFolder(fileEntry);
				
		  
			} 
			else 
			{
				if (fileEntry.isFile()) 
				{
					temp = fileEntry.getName();
					long difference  = System.currentTimeMillis() - fileEntry.lastModified();
					//System.out.println(difference);
					if(difference>7200000)
					{	
					
						if ((temp.substring(temp.lastIndexOf('.') + 1, temp.length()).toLowerCase()).equals("txt"))
						{	
							System.out.println("File= " + folder.getAbsolutePath()+ "\\" + fileEntry.getName());
						}	
					}		
				}

			}
		}
	}
}