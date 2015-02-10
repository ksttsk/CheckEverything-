/**
 * Purpose: the class downloads web pages from Internet
 * 
 */
package EverythingCheck.Downloader;

import java.util.List;

import Utility.ReaderWriter;

public class PageDownloader {	
	private ReaderWriter rw;
	private DownloadAssistant da;
	
	public PageDownloader() {
		this.rw = new ReaderWriter();
		this.da = new DownloadAssistant();
	}
	
//	public void download()
//	{
//		this.fph = new FilePathHandler();
//		this.fph.setUrlAddress(this.urlAddress);
//		this.fph.setFolder("./HTMLs/");
//		//this.filePath = this.fph.getFileStorePath();
//		this.filePath = "./HTMLs/" + this.id + ".html";
//		
//		this.pageContent = rw.readURL(this.urlAddress);					
//		rw.write(this.pageContent, filePath);
//	}
	
	public void download(String folder, List<String> urls, int level)
	{
		String pageContent = null;
		String filePath = null;
		for(String url : urls)
		{
			filePath = da.getStoredPathOfURLPage(folder);
			pageContent = rw.readURL(url);
			rw.write(pageContent, filePath);
			da.saveDownloadInfoInDB(level, url);			
		}
	}
}
