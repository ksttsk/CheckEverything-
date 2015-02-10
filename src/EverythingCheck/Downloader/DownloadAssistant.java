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

	public String getStoredPathOfURLPage(String fileFolder, int id)
	{	
//		this.nextId = dbh.getNextID();
		String filePath = fileFolder + id + ".html";
		return filePath;
	}
	
	public void saveDownloadInfoInDB(int level, String url)
	{
		this.nextId = dbh.getNextID();
		dbh.saveSingleRecordInDB(this.nextId, level, url);		
		dbh.setColumnToTrue(this.nextId, DOWNLOADED_COL_NAME);
	}
	
//	public List<String> getURLsToDownload(int level)
//	{
//		return dbh.getURLs(level);
//	}
	
	
}
