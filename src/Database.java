import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.joda.time.DateTime;

public class Database {

	static String test = "messagesstore";

	public static int intHours = 0;
	
	public static int intMins = 0;

	public static int intSecs = 0;

	public static void main(String message, String sentmessage, String messageOrigin) throws SQLException  {
		
		DateTime dt = new DateTime();  
		
		 intHours = dt.getHourOfDay();
		
		 intMins = dt.getMinuteOfHour();
		
		 intSecs = dt.getSecondOfMinute();
		

		try {
			
			
			
			Connection conn = null;

			Class.forName("org.postgresql.Driver");

			conn = connect();

			PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO "+messageOrigin+" (who, message, sentmessage, hours, mins, secs) VALUES (?,?,?,?,?,?)");



			preparedStatement.setString(1, "You");
			preparedStatement.setString(2, message);
			preparedStatement.setString(3, sentmessage);
			preparedStatement.setInt(4, intHours);
			preparedStatement.setInt(5, intMins);
			preparedStatement.setInt(6, intSecs);
			

			int row = preparedStatement.executeUpdate();

			// rows affected
			System.out.println("2 rows have been updated"); //1 new row created


		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void botMessage(String botmessage, String sentmessage, String messageOrigin) throws SQLException  {

		try {

			Connection conn = null;

			Class.forName("org.postgresql.Driver");

			conn = connect();

			PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO "+messageOrigin+" (who, message, sentmessage, hours, mins, secs) VALUES (?,?,?,?,?,?)");



			preparedStatement.setString(1, "Bot");
			preparedStatement.setString(2, botmessage);
			preparedStatement.setString(3, sentmessage);
			preparedStatement.setInt(4, intHours);
			preparedStatement.setInt(5, intMins);
			preparedStatement.setInt(6, intSecs);

			int row = preparedStatement.executeUpdate();

			// rows affected
			//1 new row created


		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static Connection connect() throws SQLException {
		return DriverManager.getConnection("jdbc:postgresql://localhost:1889/postgres", "postgres", "password");
	}

	public static void delete(String[] args){
		// create Connection con, and Statement stmt 
		Connection con;
		Statement stmt;
		try{
			Class.forName("org.postgresql.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:postgresql://localhost:1889/postgres", "postgres", "password");

			try{

				stmt = con.createStatement();
				String query = "DELETE FROM messagesstore";
				int deletedRows=stmt.executeUpdate(query);
				if(deletedRows>0){
					System.out.println("Deleted All Rows In messagesstore Successfully...");
				}else{
					System.out.println("messagesstore already empty."); 
				}

			} catch(SQLException s){
				System.out.println("Deleted All Rows In  Table Error. ");		
				s.printStackTrace();
			}

			try{

				stmt = con.createStatement();
				String query = "DELETE FROM messagesstore_1";
				int deletedRows=stmt.executeUpdate(query);
				if(deletedRows>0){
					System.out.println("Deleted All Rows In messagesstore_1 Successfully...");
				}else{
					System.out.println("messagesstore_1 already empty."); 
				}

			} catch(SQLException s){
				System.out.println("Deleted All Rows In  Table Error. ");		
				s.printStackTrace();
			}
			try{

				stmt = con.createStatement();
				String query = "DELETE FROM messagesstore_2";
				int deletedRows=stmt.executeUpdate(query);
				if(deletedRows>0){
					System.out.println("Deleted All Rows In messagesstore_2 Successfully...");
				}else{
					System.out.println("messagesstore_2 already empty."); 
				}

			} catch(SQLException s){
				System.out.println("Deleted All Rows In  Table Error. ");		
				s.printStackTrace();
			}
			// close Connection
			con.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
