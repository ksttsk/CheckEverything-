/**
 * Purpose: the class gets information from the HTML files
 */

package Utility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import java.util.ArrayList;

import javax.swing.text.html.parser.ParserDelegator;
import javax.swing.text.html.HTMLEditorKit.ParserCallback;
import javax.swing.text.html.HTML.Tag;
import javax.swing.text.html.HTML.Attribute;
import javax.swing.text.MutableAttributeSet;

public class HTMLHandler {
	private static String domain;
	private static FileReader reader;
	
	public HTMLHandler(String domain, String filePath) {
		this.domain = domain;
		try {
			this.reader = new FileReader(filePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Purpose get the href links from the html page.
	 * @param reader
	 * @return
	 */
	public static List<String> extractLinks() {
		final ArrayList<String> list = new ArrayList<String>();

		ParserDelegator parserDelegator = new ParserDelegator();
		ParserCallback parserCallback = new ParserCallback() {
			public void handleText(final char[] data, final int pos) { }
			public void handleStartTag(Tag tag, MutableAttributeSet attribute, int pos) {
				if (tag == Tag.A) {
					String address = (String) attribute.getAttribute(Attribute.HREF);
					address = fixAddress(address);
					list.add(address);
				}
			}
			public void handleEndTag(Tag t, final int pos) {  }
			public void handleSimpleTag(Tag t, MutableAttributeSet a, final int pos) { }
			public void handleComment(final char[] data, final int pos) { }
			public void handleError(final java.lang.String errMsg, final int pos) { }
		};
		try {
			parserDelegator.parse(reader, parserCallback, false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	private static String fixAddress(String address)
	{
		String fixedAddress;
		if(address.contains("http"))
		{
			fixedAddress = address;
		} else
		{
			fixedAddress = domain + address;
		}
		
		return fixedAddress;
	}
		

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		HTMLHandler.domain = domain;
	}
}