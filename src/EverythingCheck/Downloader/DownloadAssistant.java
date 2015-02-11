/**
 * Purpose: the class contains various functions which require reading from DB when downloading the web pages
 */
package EverythingCheck.Downloader;

import EverythingCheck.DBHandler.DBHandler;

public class DownloadAssistant {
	private DBHandler dbh;
	private final String DOWNLOADED_COL_NAME = "Downloaded";
	
	public DownloadAssistant() {		
		this.dbh = new DBHandler();
	}

	public String getStoredPathOfURLPage(String fileFolder, int id)
	{	
		String filePath = fileFolder + id + ".html";
		return filePath;
	}
	
	public void saveDownloadInfoInDB(int id, int level, String url, boolean ifSaveDownloadInfo)
	{
		if(ifSaveDownloadInfo)
		{
			dbh.saveSingleRecordInDB(id, level, url);	
		}				
		dbh.setColumnToTrue(id, DOWNLOADED_COL_NAME);
	}
	
}
