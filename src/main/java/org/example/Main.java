package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        UserInput userInput = new UserInput();
        String userRequest;

        while (true){
            userRequest = userInput.getUserInput();
            if (userRequest.equalsIgnoreCase("exit")){break;}
            WikiAPIClient.setConnection(userRequest);
            WikiAPIClient.readResponse();
            WikiAPIClient.showResponse();
        }
    }
}