/**
 * Purpose: the class downloads web pages from Internet
 * 
 */
package EverythingCheck.Spider;

import Utility.ReaderWriter;

public class PageDownloader {
	private String urlAddress;	
	private ReaderWriter rw;
	private FilePathHandler fph;
	private String pageContent;	
	private String filePath;
		
	public PageDownloader(String urlAddress) {
		this.urlAddress = urlAddress;	
		this.rw = new ReaderWriter();
	}
	
	public void download()
	{
		this.fph = new FilePathHandler();
		this.fph.setUrlAddress(this.urlAddress);
		this.fph.setFolder("./HTMLs/");
		this.filePath = this.fph.getFileStorePath();
		
		this.pageContent = rw.readURL(this.urlAddress);					
		rw.write(this.pageContent, filePath);
	}
}
