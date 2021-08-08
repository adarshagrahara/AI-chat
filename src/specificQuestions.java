import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class specificQuestions {

	public static int getMessageCount() {
		String SQL = "SELECT count(*) FROM messagesstore";
		int count = 0;

		try (Connection conn = Database.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(SQL)) {
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		return count;
	}

	public static int getMessageCount_1() {
		String SQL = "SELECT count(*) FROM messagesstore_1";
		int count = 0;

		try (Connection conn = Database.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(SQL)) {
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		return count;
	}  

	public static int getMessageCount_2() {
		String SQL = "SELECT count(*) FROM messagesstore_2";
		int count = 0;

		try (Connection conn = Database.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(SQL)) {
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		return count;
	}

	public static String firstrow(String whichTable, int presentHour, int presentMin, int presentSec) throws ClassNotFoundException {

		int hour = 0;

		int min = 0;

		int sec = 0;


		Connection c = null;

		Statement stmt = null;

		try {

			Class.forName("org.postgresql.Driver");

			c = Database.connect();

			//		     c.setAutoCommit(false);





			stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery( "select * from public."+whichTable+" ;" );

			while ( rs.next() ) {

				hour = rs.getInt("hours");

				min = rs.getInt("mins");

				sec  = rs.getInt("secs");



				break;

			}
			
			rs.close();

			stmt.close();

			c.close();

			int validHour = 0;

			int validMin = 0;

			int validSec = 0;

			if(hour <= presentHour && min <= presentMin && sec <= presentSec) {

				hour = presentHour - hour;

				min = presentMin - min;

				sec = presentSec - sec;

				return "As of now, we have spent a total time of "+hour+" hours and "+min+" minutes and "+ sec+" seconds chatting.";

				 

			} else {


				if (hour > presentHour) {

					hour = 60 - hour;

					validHour = hour + presentHour;

				}

				if (hour <= presentHour) {

					validHour = presentHour - hour;

				}
				
				if (min > presentMin) {

					min = 60 - min;

					validMin = min + presentMin;

				}

				if (min <= presentMin) {

					validMin = presentMin - min;

				}
				
				if (sec > presentSec) {

					sec = 60 - sec;

					validSec = sec + presentSec;

				}

				if (sec <= presentSec) {

					validSec = presentSec - sec;

				}
				
				return "As of now, we have spent a total time of "+validHour+" hours and "+validMin+" minutes and "+ validSec+" seconds chatting.";

				


			}



		} catch ( Exception e ) {

			System.err.println( e.getClass().getName()+": "+ e.getMessage() );

			System.exit(0);

		}

		System.out.println(" Data Retrieved Successfully ..");

		return null;


	}



}
