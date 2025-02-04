package org.example;

public class Main {
    public static void main(String[] args) {
        UserInput userInput = new UserInput();
        String userRequest;

        while (true){
            userRequest = userInput.getUserInput();
            if (userRequest.equalsIgnoreCase("exit")){break;}
        }
    }
}