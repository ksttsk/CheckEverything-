import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import EverythingCheck.DBHandler.DBHandler;
import EverythingCheck.Downloader.PageDownloader;
import Utility.HTMLHandler;
import Utility.ReaderWriter;


public class App {

	public static void main(String[] args)
	{
		String downloadFolder = "./HTMLs/Download";
		String extractedFolder = "./HTMLs/Extracted";
		
		
		String http = "";
		String domain = "http://www.amazon.de";
		
		String address = http + domain;
		List<String> URLs = new ArrayList<String>();
		URLs.add(address);
		PageDownloader pd = new PageDownloader();
		pd.download(downloadFolder, URLs, 1);
		
		
		int levelStart = 1;
		int levelEnd = 2;
			
		
		List<String> links2 = URLs;	
		
//		DBHandler dbh = new DBHandler();
//		
//		HTMLHandler hh;
//		int id;
//		
//		//save the level
//		
//		//dbh.saveInDB(1, links);
//		
//		for(int i = levelStart; i<= levelEnd; i++)
//		{
//			links = links2;
//								
//			for(String link : links)
//			{
//				//download the level
//				id = dbh.getNextID();
//				pd = new PageDownloader(link, id);		
//				pd.download();	
//				
//				//extract the links 
//				hh = new HTMLHandler(domain);
//				links2 = hh.extractInfo(pd.getFilePath());
//				
//				//save the links2
//				dbh.saveInDB(i + 1, links2);
//			}			
//		}		
	}
}
