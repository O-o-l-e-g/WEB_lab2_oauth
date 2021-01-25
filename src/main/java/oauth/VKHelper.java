package oauth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class VKHelper {

    public static StringBuilder getUserData(String token) throws IOException {

        String url = "https://api.vk.com/method/account.getProfileInfo?access_token="+token+"&v=5.126";

        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder JSONresponse = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            JSONresponse.append(inputLine);
        }
        in.close();

        return JSONresponse;
    }
}
