/**
 * Purpose: the class contains various functions which require reading from DB when downloading the web pages
 */
package EverythingCheck.Downloader;

import java.io.File;
import java.util.List;

import EverythingCheck.DBHandler.DBHandler;

public class DownloadAssistant {
	private DBHandler dbh;
	private int nextId;
	private final String DOWNLOADED_COL_NAME = "Downloaded";
	
	public DownloadAssistant() {		
		this.dbh = new DBHandler();
	}

	public String getStoredPathOfURLPage(String fileFolder)
	{	
		this.nextId = dbh.getNextID();
		String filePath = fileFolder + this.nextId + ".html";
		return filePath;
	}
	
	public void saveDownloadInfoInDB(int level, String url)
	{
		dbh.saveSingleRecordInDB(this.nextId, level, url);		
		dbh.setColumnToTrue(this.nextId, DOWNLOADED_COL_NAME);
	}
	
	
	public List<String> getFilesInFolder(String folderPath)
	{
		List<String> filePaths = null;
		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();
		String filePath;
		for(int i=0; i<listOfFiles.length; i++)
		{
			if(listOfFiles[i].isFile())				
			{
				filePath = listOfFiles[i].getPath();
				if(filePath.contains(".html"))
				{
					filePaths.add(filePath);	
				}				
			}
		}		
		return filePaths;
	}
	
}
