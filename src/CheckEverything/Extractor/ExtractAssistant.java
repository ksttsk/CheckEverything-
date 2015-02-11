package CheckEverything.Extractor;

import java.util.List;

import EverythingCheck.DBHandler.DBHandler;

public class ExtractAssistant {
	private DBHandler dbh;
	private int nextId;
	private final String EXTRACTED_COL_NAME = "Extracted";
	
	public ExtractAssistant() {
		this.dbh = new DBHandler();
	}
	
	public void saveExtractedInfoInDB(int extractedId, List<String> URLs, int level)
	{
		dbh.setColumnToTrue(extractedId, EXTRACTED_COL_NAME);
		
		for(String url : URLs)
		{
			this.nextId = dbh.getNextID();
			dbh.saveSingleRecordInDB(this.nextId, level, url);		
		}		
	}
}
