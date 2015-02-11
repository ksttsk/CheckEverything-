package CheckEverything.Extractor;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Utility.ReaderWriter;

public class Extractor {
	ReaderWriter rw;
	private String url;
	private ExtractAssistant ea;
	
	public Extractor(String url) {
		this.url = url;
		rw = new ReaderWriter();
		ea = new ExtractAssistant();
	}
	
	
	public void extractLinks(String htmlFilePath, int level)
	{
		List<String> Infos = new ArrayList<String>();
		String linkHref;
		String linkText;
		String fileNameWithoutExtension;
		String htmlContent = rw.read(htmlFilePath);
		Document doc = Jsoup.parse(htmlContent);
		
		Element content = doc.getElementById("shopAllLinks");
		Elements links = content.getElementsByTag("a");
		for (Element link : links) {
			linkHref = link.attr("href");
			linkText = link.text();
			if(!linkText.isEmpty())
			{
				Infos.add(getCompleteHref(linkHref));
			}		  
		}		
		fileNameWithoutExtension = rw.getFileName(htmlFilePath);
		ea.saveExtractedInfoInDB(Integer.valueOf(fileNameWithoutExtension), Infos, level);
	}
	
	
	private String getCompleteHref(String href)
	{
		String completeHref;
		if(href.contains("http")) {
			completeHref = href;
		} else
		{
			completeHref = getDomain(this.url) + href;
		}
		return completeHref;
	}
	
	
	private String getDomain(String url)
	{
		int pos1 = url.indexOf("//") + 2;
		int pos2 = url.indexOf("/", pos1);
		return url.substring(0, pos2);
	}	
}
