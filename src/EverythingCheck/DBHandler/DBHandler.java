package EverythingCheck.DBHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHandler {
	private String database = "CheckEverything";
	private String userName = "postgres";
	private String password = "whd123";
	private Connection con;
	
	public DBHandler()
	{
		this.connectDB();
	}
	
	public DBHandler(String database, String userName, String password)
	{
		this.database = database;
		this.userName = userName;
		this.password = password;
		this.connectDB();
	}
	
	private void connectDB()
	{
		try {
			this.con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + this.database, this.userName, this.password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	public void run()
	{
		try {
			Class.forName("org.postgresql.Driver");
			 Statement s = this.con.createStatement();
			 ResultSet rs = s.executeQuery("Select * from \"HTML\"");
		     if (rs.next()) {
		         System.out.println(rs.getString(3) );
		     }
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
