/**
 * Purpose: the class read and writes flat files, HTMLs
 */
package Utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

public class ReaderWriter {	
	private FileOutputStream fop = null;
	private FileInputStream fip = null;
	private InputStreamReader isr = null;
	private BufferedReader br = null;
	private File file;
	private URL url;
	private InputStream is = null;
	
	final static String ENCODING = "UTF-8";
	
	public void write(String content, String filePath)
	{
		file = new File(filePath);
		try {
			fop = new FileOutputStream(file);
			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			
			// get the content in bytes
			byte[] contentInBytes = content.getBytes();
 
			fop.write(contentInBytes);
			fop.flush();
			fop.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}
	
	
	
	public String readURL(String urlAddress)
	{
		String line = null;
		String content = null;
		try {
			this.url = new URL(urlAddress);
			this.is = url.openStream();
			this.br = new BufferedReader(new InputStreamReader(is));
			
			while ((line = br.readLine()) != null) {
				content += line;
	        }
		} catch (MalformedURLException e) {
			System.out.println(urlAddress);
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}   
		return content;
	}
	
	
	public String read(String filePath)
	{		
		String line = null;
		String content = null;		
		file = new File(filePath);
		
		try {
			fip = new FileInputStream(file);
			isr = new InputStreamReader(fip, ENCODING);
			br = new BufferedReader(isr);
			
			while ((line = br.readLine()) != null) {
		        content += line;
		    } 
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
		
			e.printStackTrace();
		} finally
		{			
			try {
				br.close();
				isr.close();
				fip.close();
			} catch (IOException e) {
		
				e.printStackTrace();
			}
		}
		
		return content;
	}	
}
