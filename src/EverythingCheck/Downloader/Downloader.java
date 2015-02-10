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
	private String downloadedFilePath;
	
	public Downloader() {
		this.rw = new ReaderWriter();
		this.da = new DownloadAssistant();
	}
	
	public void download(String folder, List<Integer> IDs, List<String> URLs, int level)
	{
		String pageContent = null;
		String filePath = null;
		String url = null;
		for(int i=0; i<IDs.size(); i++)
		{
			url = URLs.get(i);
			filePath = da.getStoredPathOfURLPage(folder, IDs.get(i));
			try {
				Thread.sleep(2000);
				pageContent = rw.readURL(url);
				rw.write(pageContent, filePath);
				da.saveDownloadInfoInDB(level, url);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}
	}
	
	
}
