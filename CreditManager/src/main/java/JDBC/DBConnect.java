package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

	private Connection conn;
	public Connection connectToOracle(){
		try{
			String url = "jdbc:oracle:thin:@LOCALHOST:1521:System";
			String user = "system";
			String pass = "Carlos";
			Class.forName("oracle.jdbc.OracleDriver"); //Loading Driver YOU HAVE TO ADD DRIVER FILE .JAR
			conn = DriverManager.getConnection(url,user,pass);
			System.out.println("Connection Established");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}