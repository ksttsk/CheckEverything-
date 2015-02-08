import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import EverythingCheck.DBHandler.DBHandler;
import EverythingCheck.Spider.PageDownloader;
import Utility.HTMLHandler;
import Utility.ReaderWriter;


public class App {

	public static void main(String[] args)
	{
		String http = "";
		String domain = "http://www.amazon.de";
		
		String address = http + domain;
		int levelStart = 1;
		int levelEnd = 2;
		List<String> links = new ArrayList<String>();	
		links.add(address);
		List<String> links2 = links;	
		
		DBHandler dbh = new DBHandler();
		PageDownloader pd;
		HTMLHandler hh;
		
		//save the level
		dbh.saveInDB(1, links);
		
		for(int i = levelStart; i<= levelEnd; i++)
		{
			links = links2;
								
			for(String link : links)
			{
				//download the level
				pd = new PageDownloader(link);		
				pd.download();	
				
				//extract the links 
				hh = new HTMLHandler(domain);
				links2 = hh.extractInfo(pd.getFilePath());
				
				//save the links2
				dbh.saveInDB(i + 1, links2);
			}			
		}		
	}
}
