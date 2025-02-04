package org.example;

import java.util.Scanner;

public class UserInput {
    private Scanner scanner;

    UserInput(){
        this.scanner = new Scanner(System.in);
    }

    public String getUserInput(){
        System.out.println("Введите ваш запрос: (exit - если хотите выйти)");
        return scanner.nextLine();
    }
}
