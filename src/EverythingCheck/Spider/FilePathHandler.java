/**
 * Purpose: the class generates the path where to store the HTML file which will be downloaded
 */
package EverythingCheck.Spider;

public class FilePathHandler {
	private String folder;
	private String urlAddress;
	private String[] parts;
	private String filePath;
	
	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	
	public FilePathHandler() {		
		
	}
	
	public String getUrlAddress() {
		return urlAddress;
	}

	public void setUrlAddress(String urlAddress) {
		this.urlAddress = urlAddress;
	}

	public String getFileStorePath()
	{					
		this.parts = this.urlAddress.split("/");
		this.filePath = this.folder + this.parts[this.parts.length - 1] + ".html";		
		return this.filePath;
	}

}
