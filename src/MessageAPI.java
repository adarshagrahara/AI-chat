import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;




public class MessageAPI {

	public static String main(String message) throws Exception {


		String result = null;

		try {
			result = URLEncoder.encode(message, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result = result.replace("+", "%20");

		// the error is because the string has multiple " " for the response to the word joke, i need to replace " with something else but i keep getting error

		HttpResponse<JsonNode> response1 = Unirest.get("https://acobot-brainshop-ai-v1.p.rapidapi.com/get?bid=178&key=sX5A2PcYZbsN5EY6&uid=mashape&msg="+result)
				.header("x-rapidapi-key", "b7696740bfmshfdc15e0e4fecdf9p1120f3jsna47b2eebda59")
				.header("x-rapidapi-host", "acobot-brainshop-ai-v1.p.rapidapi.com")
				.asJson();

		Gson gson1 = new GsonBuilder()
				.setPrettyPrinting()
				.disableHtmlEscaping()
				.create();
		JsonParser jp1 = new JsonParser();
		JsonElement je1 = jp1.parse(response1.getBody().toString());
		String finalResponse1 = gson1.toJson(je1);

		String jsonString1 = finalResponse1 ; //assign your JSON String here
		JSONObject obj1 = new JSONObject(jsonString1);
		finalResponse1 = obj1.getString("cnt");

		finalResponse1 = finalResponse1.replace("Bicha", "So");

		finalResponse1 = finalResponse1.replace("Louise", "So");

		finalResponse1 = finalResponse1.replace("Vinay", "So");

		finalResponse1 = finalResponse1.replace("Ela", "So");

		finalResponse1 = finalResponse1.replace("Aco", "the Boogeyman");
		
		finalResponse1 = finalResponse1.replace("->", "is");
		
		finalResponse1 = finalResponse1.replace("(translations by Microsoft translator)", "");

		finalResponse1 = finalResponse1.replace("\"","\\\"");


		return finalResponse1;









	}

}
