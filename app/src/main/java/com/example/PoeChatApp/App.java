package com.example.PoeChatApp;
import java.util.Scanner;
public class App {
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== Registration ===");
        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        System.out.print("Enter SA cell number (+27...): ");
        String cellNumber = input.nextLine();

        User user = new User(username, password, cellNumber);
        String registrationMessage = user.registerUser();
        System.out.println(registrationMessage);

        System.out.println("\n=== Login ===");
        System.out.print("Enter username: ");
        String loginUsername = input.nextLine();

        System.out.print("Enter password: ");
        String loginPassword = input.nextLine();

        System.out.print("Enter your first name: ");
        String firstName = input.nextLine();

        String loginMessage = user.returnLoginStatus(loginUsername, loginPassword, firstName);
        System.out.println(loginMessage);

        System.out.println("\n=== Send Message ===");
        System.out.print("Enter message text: ");
        String messageText = input.nextLine();

        Message msg = new Message(messageText);
        msg.send();
        msg.markAsRead();

        System.out.println("\nMessage Panel:");
        System.out.println(msg.getMessagePanel());

        input.close();
    }
}

