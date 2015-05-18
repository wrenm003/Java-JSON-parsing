import java.net.*;
import java.util.ArrayList;
import java.io.*;
import org.json.*;
import java.lang.*; 

public class Main{


	public static void main(String [] args){
		ArrayList<String> titles = new ArrayList<String>();
		//getHTML("https://www.reddit.com/r/DotA2.json?limit=10");
		System.out.println(parse(getHTTP("https://www.reddit.com/r/DotA2.json?limit=10")));
	}
	public static String getHTTP(String urlToRead) {

		URL url;
		HttpURLConnection conn;
		BufferedReader rd;
		String line;
		String result = "";
		boolean response = false;
		while (!response)
		{
			try {
				url = new URL(urlToRead);
				conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				while ((line = rd.readLine()) != null) {
					result += line;
				}
				rd.close();
				response = true;
			} catch (IOException e) {
				e.printStackTrace();
				try {
					Thread.sleep(600);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		return result;
	}
	public static ArrayList<String> parse(String httpResponse)
    {
        ArrayList<String> titles = new ArrayList<String>();
        try {
            JSONObject obj = new JSONObject(httpResponse);
            //JSONObject posts = obj.getJSONObject("kind");
            JSONObject posts1 = obj.getJSONObject("data");
            JSONArray posts2 = posts1.getJSONArray("children");


            for (int i = 0; i < posts2.length(); i++) {
                JSONObject test = posts2.getJSONObject(i);
                JSONObject test1 = test.getJSONObject("data");
                Object test2 = test1.get("title");
                titles.add(test2.toString());
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return titles;
    }
}