package EverythingCheck.DBHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBHandler {
	private String DATABASE = "CheckEverything";
	private String USERNAME = "postgres";
	private String PASSWORD = "whd123";
	private Connection con;
	private Statement stat;
	
	public DBHandler()	{}
	
	public DBHandler(String DATABASE, String USERNAME, String PASSWORD)
	{
		this.DATABASE = DATABASE;
		this.USERNAME = USERNAME;
		this.PASSWORD = PASSWORD;
	}
			
	private void connectDB()
	{
		try {
			Class.forName("org.postgresql.Driver");
			this.con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + this.DATABASE, this.USERNAME, this.PASSWORD);
			con.setAutoCommit(false);
			stat = this.con.createStatement();
		} catch (SQLException e) {			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		} finally {
			
		}			
	}
	
	
	
	public int getNextID()
	{
		String sql = "select \"ID\" from \"HTMLs\" order by \"ID\" desc Limit 1";
		int nextId = -1;
		ResultSet rs = null;
		
		this.connectDB();
		
		try {
			rs = this.stat.executeQuery(sql);
			
			if (rs.next()) {		
		        nextId = rs.getInt(1) + 1;
		    } else {
		    	nextId = 1; //if the table is empty, then the first ID = 1
		    }	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally
		{
			try {
				stat.close();
			    rs.close();
			    con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return nextId;	    		
	}
	
	
	public void setColumnToTrue(int id, String columnName)
	{
		String sql = "Update \"HTMLs\" Set \"" + columnName + "\" = True Where \"ID\" = " + id ;
		this.connectDB();
		try {
			stat.executeUpdate(sql);			
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally
		{
			try {
				stat.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
	}
	
	
	public List<Integer> getIDs(int level)
	{
		String sql = "Select \"ID\" From \"HTMLs\" Where \"Level\" = " + level + " order by \"ID\"";
		ResultSet rs = null;
		List<Integer> IDs = new ArrayList<Integer>();
		this.connectDB();
		
		try {
			rs = this.stat.executeQuery(sql);
			
			while (rs.next()) {		
		        IDs.add(Integer.valueOf(rs.getString(1)));
		    } 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally
		{
			try {
				stat.close();
			    rs.close();
			    con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return IDs;
	}
	
	
	
	public List<String> getURLs(int level)
	{
		String sql = "Select \"URL\" From \"HTMLs\" Where \"Level\" = " + level + "order by \"ID\"";
		ResultSet rs = null;
		List<String> URLs = new ArrayList<String>();
		this.connectDB();
		
		try {
			rs = this.stat.executeQuery(sql);
			
			while (rs.next()) {		
		        URLs.add(rs.getString(1));
		    } 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally
		{
			try {
				stat.close();
			    rs.close();
			    con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return URLs;
	}
	
	
	
	public void saveSingleRecordInDB(int id, int level, String url)
	{
		String sql = "INSERT INTO \"HTMLs\" (\"ID\", \"Level\", \"URL\") VALUES ";
		
		this.connectDB();
		
		sql += "(" + id + " , " + level + " , '" + url + "'),";			
		
		
		sql = sql.substring(0, sql.length() - 1);
		
		try {
			stat.executeUpdate(sql);			
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally
		{
			try {
				stat.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
	}
	
	
	
	public void saveMultiRecordsInDB(int id, int level, List<String> URLs)
	{
		String sql = "INSERT INTO \"HTMLs\" (\"ID\", \"Level\", \"URL\") VALUES ";
		
		this.connectDB();
		
		for (String url : URLs) {
			sql += "(" + id + " , " + level + " , '" + url + "'),";			
		}
		
		sql = sql.substring(0, sql.length() - 1);
		
		try {
			stat.executeUpdate(sql);			
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally
		{
			try {
				stat.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
	}
	
	public void clearDBTable()
	{
		String sql = "Delete From \"" + "HTMLs" + "\"";
		this.connectDB();
		try {
			stat.executeUpdate(sql);			
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally
		{
			try {
				stat.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
	}
}
