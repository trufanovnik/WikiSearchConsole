package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WikiAPIClient {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String API_URL = "https://ru.wikipedia.org/w/api.php?action=query&list=search&srsearch=%s&format=json";
    private static HttpsURLConnection connection;
    private static StringBuilder response;

    protected static String createURL(String userRequest){
        String encodedQuery = null;
        try {
            encodedQuery = URLEncoder.encode(userRequest, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return String.format(API_URL, encodedQuery);
    }

    protected static void setConnection(String userRequest){
        String url = createURL(userRequest);
        try {
            connection = (HttpsURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", USER_AGENT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected static void readResponse() throws IOException {
        if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                response = new StringBuilder();
                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);

            }
        }else {
            System.err.println("GET запрос недоступен");
        }
    }

    protected static void showResponse(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement jsonElement = JsonParser.parseString(response.toString());
        String prettyJsonString = gson.toJson(jsonElement);

        System.out.println(prettyJsonString);
    }
}
