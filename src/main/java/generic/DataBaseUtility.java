package generic;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DataBaseUtility {
	Connection conn;
	
	public void getConnection(String url, String un, String pwd) {
	try {
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		conn=DriverManager.getConnection(url, un, pwd);
		
	}catch(Exception e) {}
	}
	
	public void closeConnection() {
		try {
			conn.close();
		}catch(Exception e) {}
	}
	
	public ResultSet selectQuery(String query) throws Exception {
		ResultSet r=null;
		try {
		Statement stat=conn.createStatement();
		r=stat.executeQuery(query);
		
	}catch(Exception e) {}
		return r;

}
	public int nonselectQuery(String query) {
		int r=0;
		try {
		Statement stat=conn.createStatement();
		r=stat.executeUpdate(query);
		
	}catch(Exception e) {}
		return r;
		
	}
	

}
