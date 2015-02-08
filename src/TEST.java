import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import EverythingCheck.DBHandler.DBHandler;
import EverythingCheck.Spider.PageDownloader;
import Utility.HTMLHandler;
import Utility.ReaderWriter;


public class TEST {

	
	
	public static void main(String[] args)
	{
		
//		ReaderWriter rw = new ReaderWriter();
//		System.out.println(rw.read("./HTMLs/www.google.de.html"));
//		rw.write("this is me", "test.txt");
		
		String address = "http://www.realmadrid.com/en";
		PageDownloader pd = new PageDownloader(address);		
		pd.download();	
		
//		HTMLHandler hh = new HTMLHandler("www.google.de", "./HTMLs/www.google.de.html");
//		List<String> links;		
//		links = HTMLHandler.extractLinks();
//		for (String link : links) {
//			System.out.println(link);	
//		}
		
//		DBHandler dbh = new DBHandler();
//		dbh.run();
	}
}
