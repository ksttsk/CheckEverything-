package Utility;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLHandler {
	ReaderWriter rw;
	private String domain;
	public List<String> extractInfo(String htmlFilePath)
	{
		List<String> Infos = new ArrayList<String>();
		String linkHref;
		String linkText;
		
		String htmlContent = rw.read(htmlFilePath);
		Document doc = Jsoup.parse(htmlContent);
		
		//Element content = doc.getElementById("content");
		Elements links = doc.getElementsByTag("a");
		for (Element link : links) {
			linkHref = link.attr("href");
			linkText = link.text();
			if(!linkText.isEmpty())
			{
				Infos.add(getCompleteHref(linkHref));
			}		  
		}		
		return Infos;
	}
	
	private String getCompleteHref(String href)
	{
		String completeHref;
		if(href.contains("http")) {
			completeHref = href;
		} else
		{
			completeHref = this.domain + href;
		}
		return completeHref;
	}
	
	public HTMLHandler(String domain) {
		// TODO Auto-generated constructor stub
		this.domain = domain;
		rw = new ReaderWriter();
	}
}
