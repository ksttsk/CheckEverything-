/**
 * Purpose: the class downloads web pages from Internet
 * 
 */
package EverythingCheck.Downloader;

import java.util.List;

import Utility.ReaderWriter;

public class Downloader {	
	private ReaderWriter rw;
	private DownloadAssistant da;
	
	public Downloader() {
		this.rw = new ReaderWriter();
		this.da = new DownloadAssistant();
	}
	
	public void download(String folder, List<Integer> IDs, List<String> URLs, int level, boolean ifSaveDownloadInfo)
	{
		String pageContent = null;
		String filePath = null;
		String url = null;
		int id;
		
		for(int i=0; i<IDs.size(); i++)
		{
			url = URLs.get(i);
			id = IDs.get(i);
			filePath = da.getStoredPathOfURLPage(folder, id);
			try {
				Thread.sleep(6000);
				pageContent = rw.readURL(url);
				rw.write(pageContent, filePath);
				da.saveDownloadInfoInDB(id, level, url, ifSaveDownloadInfo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}				
		}
	}
	
	
}
