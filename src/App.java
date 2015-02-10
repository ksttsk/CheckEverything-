import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import CheckEverything.Extractor.Extractor;
import EverythingCheck.DBHandler.DBHandler;
import EverythingCheck.Downloader.Downloader;
import Utility.HTMLHandler;
import Utility.ReaderWriter;


public class App {

	public static void main(String[] args)
	{
		String downloadFolder = "./HTMLs/Download/";
		String extractedFolder = "./HTMLs/Extracted/";
		DBHandler dbh = new DBHandler();
		
		ReaderWriter rw = new ReaderWriter();
		
		if(!rw.isFolderExisting(downloadFolder))
		{
			rw.createFolder(downloadFolder);
		}
		
		if(!rw.isFolderExisting(extractedFolder))
		{
			rw.createFolder(extractedFolder);
		}
		
		String http = "";
		String domain = "http://www.amazon.de";
		
		String address = http + domain;
		List<String> URLs = new ArrayList<String>();
		List<Integer> IDs = new ArrayList<Integer>();

		Downloader dd = new Downloader();
		int levelStart = 1;
		int levelEnd = 2;
		
		for(int i = levelStart; i <= levelEnd; i++)
		{
			if (i == levelStart)
			{
				URLs.add(address);
				IDs.add(1);
			} else
			{
				URLs = dbh.getURLs(i);
				IDs = dbh.getIDs(i);
			}
			
//			URLs_arr = URLs.toArray(URLs_arr);
//			IDs_arr = IDs.toArray(IDs_arr);
			
			dd.download(downloadFolder, IDs, URLs, i);
			
			Extractor et = new Extractor(domain);
			List<String> filePaths = rw.getFilesInFolder(downloadFolder, "html");
			for(String filePath: filePaths)
			{
				et.extractLinks(filePath, i + 1);	
				rw.moveFile(filePath, extractedFolder);
			}
		}
		
	}
}
