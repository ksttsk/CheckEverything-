package EverythingCheck.DBHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DBHandler {
	private String database = "CheckEverything";
	private String userName = "postgres";
	private String password = "whd123";
	private Connection con;
	private Statement s;
	
	public DBHandler()
	{
		this.connectDB();
	}
	
	public DBHandler(String database, String userName, String password)
	{
		this.database = database;
		this.userName = userName;
		this.password = password;		
	}
	
	private void connectDB()
	{
		try {
			Class.forName("org.postgresql.Driver");
			this.con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + this.database, this.userName, this.password);
			con.setAutoCommit(false);
			s = this.con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
//	private void disconnectDB()
//	{
//		try {
//			this.con.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public int getNextID()
	{
		this.connectDB();
		String sql = "select \"ID\" from \"HTMLs\" order by \"ID\" desc Limit 1";
		int nextId = 0;
		ResultSet rs;
		try {
			rs = this.s.executeQuery(sql);
			if (rs.next()) {
		        //System.out.println(rs.getString(1));
		        nextId = rs.getInt(1) + 1;
		    }
			s.close();
		    rs.close();
		    con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nextId;	    		
	}
	
	
	
	public void saveInDB(int level, List<String> Records)
	{
		this.connectDB();
		
		String sql = "INSERT INTO \"HTMLs\" (\"Level\", \"URL\") VALUES ";
		
		for (String record : Records) {
			sql += "(" + level + " , '" + record + "'),";			
		}
		
		sql = sql.substring(0, sql.length() - 1);
		
		try {
			s.executeUpdate(sql);
			s.close();
			con.commit();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		this.connectDB();
		
		try {			
			ResultSet rs = this.s.executeQuery("Select * from \"HTMLs\"");
		    if (rs.next()) {
		        System.out.println(rs.getString(3) );
		    }
		    s.close();
		    rs.close();
		    con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}
		
	}
}
