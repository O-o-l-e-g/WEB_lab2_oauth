package oauth;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    public static String parseToken(String token) {
        String newToken;
        int index1 = token.indexOf("access_token");
        int index2 = token.indexOf("&expires");
        newToken = token.substring(index1+13, index2);
        return newToken;
    }

    public static List<String> parseJSON(StringBuilder responseJSON) {
        ArrayList<String> userData = new ArrayList<String>();
        String firstName;
        String lastName;
        int startIndexFName = responseJSON.toString().indexOf("first_name");
        int endIndexFName = responseJSON.toString().indexOf("\",\"id\"");
        int startIndexLName = responseJSON.toString().indexOf("last_name");
        int endIndexLName = responseJSON.toString().indexOf("\",\"home_town\"");
        firstName = responseJSON.substring(startIndexFName+13, endIndexFName);
        lastName = responseJSON.substring(startIndexLName+12, endIndexLName);
        userData.add(firstName);
        userData.add(lastName);
        return userData;
    }
}
