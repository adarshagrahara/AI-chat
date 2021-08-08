import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;

public class VoiceToVoice extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<String> voicetovoicemessage = new ArrayList<String>();

	private List<String> wordBotResponses = new ArrayList<String>();
	
	public int number = 0;
	
	public static String timeTalked = null;

	public static int intHours = 0;

	public static int intMins = 0;

	public static int intSecs = 0;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String message = request.getParameter("output");
		
		number = 0;

		if(message.equals("How many messages have we sent?") || message.equals("how many messages have we sent?") || message.equals("how many messages have we sent") || message.equals("How many messages have we sent")) {
			
			number = specificQuestions.getMessageCount_2();
			
			
		}
		
		if(message.equals("How long have we been talking?") || message.equals("How long have we been talking") || message.equals("how long have we been talking?") || message.equals("how long have we been talking")) {

			String TextToText = "messagesstore_2";

			DateTime dt = new DateTime();  

			intHours = dt.getHourOfDay();

			intMins = dt.getMinuteOfHour();

			intSecs = dt.getSecondOfMinute();

			try {

				timeTalked = specificQuestions.firstrow(TextToText,intHours,intMins,intSecs);


			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}

		String type = "VoiceToVoicechat.jsp";

		String messageOrigin = "messagesstore_2";

		String botResponse = null;

		String wordBotResponse = null;

		try {

			botResponse = MessageAPI.main(message);




		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}

		wordBotResponse = botResponse.replace("\\\"","\"");
		
		if (number > 0) {

			botResponse = "You have sent a total of "+ (number+2)/2 +" messages and I have sent a total of "+ (number+2)/2 +" messages and a total of "+ (number+2) +" messages together including this message.";

			wordBotResponse = "You have sent a total of "+ (number+2)/2 +" messages and I have sent a total of "+ (number+2)/2 +" messages and a total of "+ (number+2) +" messages together including this message.";
		}
		
		if(timeTalked != null) {

			botResponse = timeTalked;

			wordBotResponse = timeTalked;

		}

		wordBotResponses.add(wordBotResponse);

		voicetovoicemessage.add(message);
		
		try {
			Database.main(message, type, messageOrigin);
			Database.botMessage(wordBotResponse, type, messageOrigin);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("botResponse",botResponse);

		request.getRequestDispatcher("VoiceToVoicechat.jsp").forward(request, response);




	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		for (int i = 0; i < voicetovoicemessage.size() && i < wordBotResponses.size(); i++) {



			response.getOutputStream().println("<p>");
			response.getOutputStream().println("<div class=\"message sag mtLine\">\n"
					+ "      <div class=\"messageTextsend\" data-time=\"\">\n" + voicetovoicemessage.get(i) + "\n"
					+ "      </div><div class=\"resimsend\" style=\"background-image: url('https://fbcdn-sphotos-c-a.akamaihd.net/hphotos-ak-prn2/t1/1393075_669686723071617_1541630705_n.jpg');\"></div>\n"
					+ "    </div>");
			response.getOutputStream().println("</p>");



			response.getOutputStream().println("<p>");
			response.getOutputStream().println("<div class=\"message sol mtLine\">\n"
					+ "      <div class=\"resim\" style=\"background-image: url('https://fbcdn-sphotos-d-a.akamaihd.net/hphotos-ak-frc3/t1/q71/1422532_452755621503523_1504727417_n.jpg');\"></div><div class=\"messageText\" data-time=\"\">\n"
					+ "        " +wordBotResponses.get(i)+ "\n"
					+ "      </div>\n"
					+ "    </div>");
			response.getOutputStream().println("</p>");

		}
	}

}