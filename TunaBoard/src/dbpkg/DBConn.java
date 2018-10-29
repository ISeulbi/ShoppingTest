package dbpkg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConn {
	
	
	public static Connection getConnecion(){
		Connection con = null;
		try{
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe", "hr", "hr");
			
		} catch (Exception e){
			e.printStackTrace();
		}
		return con;
	}
	
	public static void close(Connection conn, PreparedStatement ps, ResultSet rs){
		if(rs != null){
			try{ rs.close(); }catch(Exception e){}
		}
		if(ps != null){
			try{ ps.close(); }catch(Exception e){}
		}
		if(conn != null){
			try{ conn.close(); }catch(Exception e){}
		}
	}
}
