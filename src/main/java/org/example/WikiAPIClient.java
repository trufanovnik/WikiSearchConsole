package org.example;

public class WikiAPIClient {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String API_URL = "https://en.wikipedia.org/w/api.php?action=query&list=search&srsearch=%s&format=json";

    protected static String createURL(String userRequest){
        return String.format(API_URL, userRequest.replace(" ", "%20"));
    }

}
