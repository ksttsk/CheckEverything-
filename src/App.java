import java.util.ArrayList;
import java.util.List;

import CheckEverything.Extractor.Extractor;
import EverythingCheck.DBHandler.DBHandler;
import EverythingCheck.Downloader.Downloader;
import Utility.ReaderWriter;


public class App {

	public static void main(String[] args)
	{
		String downloadFolder = "./HTMLs/Download/";
		String extractedFolder = "./HTMLs/Extracted/";
		
		//url must starts with "http://" or "https://"
		String url = "http://www.amazon.de/gp/site-directory";
		
		int levelStart = 1;
		int levelEnd = 2;
		
		//clear database table
		DBHandler dbh = new DBHandler();		
		dbh.clearDBTable();		
		
		//delete existing htmls in folders
		ReaderWriter rw = new ReaderWriter();
		rw.deleteDirectory(downloadFolder);
		rw.deleteDirectory(extractedFolder);
		
		//create folders if not existing
		if(!rw.isFolderExisting(downloadFolder))
		{
			rw.createFolder(downloadFolder);
		}
		
		if(!rw.isFolderExisting(extractedFolder))
		{
			rw.createFolder(extractedFolder);
		}
		

		List<String> URLs = new ArrayList<String>();
		List<Integer> IDs = new ArrayList<Integer>();

		Downloader dd = new Downloader();
		Extractor et = new Extractor(url);
		boolean ifSaveDownloadInfo = false;
		
		
		for(int i = levelStart; i <= levelEnd; i++)
		{
			if (i == levelStart)
			{
				URLs.add(url);
				IDs.add(1);
				ifSaveDownloadInfo = true; //at levelStart, the given link should be inserted into the database
			} else
			{
				URLs = dbh.getURLs(i);
				IDs = dbh.getIDs(i);
				ifSaveDownloadInfo = false;// at other levels, the links should not be inserted again, because the links are already there after extracting the link
			}
			
			dd.download(downloadFolder, IDs, URLs, i, ifSaveDownloadInfo);
						
			List<String> filePaths = rw.getFilesInFolder(downloadFolder, "html");
			for(String filePath: filePaths)
			{
				et.extractLinks(filePath, i + 1);	
				rw.moveFile(filePath, extractedFolder);
			}
		}		
	}
}
