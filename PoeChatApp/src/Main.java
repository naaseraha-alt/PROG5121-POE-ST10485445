import java.util.Scanner;

public class Main {
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
        System.out.println(user.registerUser());

        System.out.println("\n=== Login ===");
        System.out.print("Enter username: ");
        String loginUsername = input.nextLine();

        System.out.print("Enter password: ");
        String loginPassword = input.nextLine();

        System.out.print("Enter your first name: ");
        String firstName = input.nextLine();
        System.out.print("Enter your surname: ");
        String surname = input.nextLine();

        if (!user.loginUser(loginUsername, loginPassword)) {
            System.out.println("Username or password incorrect, please try again.");
            input.close();
            return;
        }

        // Show personalized welcome and app banner
        System.out.println(user.returnLoginStatus(loginUsername, loginPassword, firstName, surname));
        System.out.println("Welcome to QuickChat.");

        // Part 2: ask how many messages user wants to enter
        System.out.print("How many messages will you enter? ");
        int totalToEnter = 0;
        try {
            totalToEnter = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number. Exiting.");
            input.close();
            return;
        }

        int entered = 0;
        while (true) {
            System.out.println("\n1) Send Messages\n2) Show recently sent messages\n3) Quit");
            System.out.print("Choose option: ");
            String choice = input.nextLine();

            if ("1".equals(choice)) {
                if (entered >= totalToEnter) {
                    System.out.println("You have reached the allowed number of messages.");
                    continue;
                }
                entered++;
                System.out.print("Enter recipient cell number: ");
                String recipient = input.nextLine();
                System.out.print("Enter your message (max 250 characters): ");
                String text = input.nextLine();

                if (text.length() > 250) {
                    int over = text.length() - 250;
                    System.out.println("Message exceeds 250 characters by " + over + ", please reduce size.");
                    entered--; // don't count invalid
                    continue;
                } else {
                    System.out.println("Message ready to send.");
                }

                Message message = new Message(entered, recipient, text);
                if (message.checkRecipientCell() == 0) {
                    System.out.println("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
                    entered--;
                    continue;
                }

                System.out.println("Message ID generated: " + message.getId());
                System.out.println();
                System.out.println("Message Options:");
                System.out.println("1) Send Message");
                System.out.println("2) Disregard Message");
                System.out.println("3) Store Message to send later");
                System.out.print("Choose an option: ");
                String option = input.nextLine();

                String action;
                if ("1".equals(option)) {
                    action = "Send";
                } else if ("2".equals(option)) {
                    action = "Disregard";
                } else if ("3".equals(option)) {
                    action = "Store";
                } else {
                    System.out.println("Invalid option.");
                    entered--;
                    continue;
                }
                try {
                    javax.swing.JOptionPane.showMessageDialog(null,
                        "Message Details:\n" +
                        "MessageID: " + message.getId() + "\n" +
                        "Message Hash: " + message.getMessageHash() + "\n" +
                        "Recipient: " + recipient + "\n" +
                        "Message: " + text,
                        "Message",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
                } catch (Throwable t) {
                    // If GUI not available, ignore.
                }

                String result = message.sendMessage(action);
                System.out.println(result);
                if ("Disregard".equalsIgnoreCase(action)) {
                    // Ensure the exact line is printed regardless of return value path
                    if (!"Press 0 to delete message.".equals(result)) {
                        System.out.println("Press 0 to delete message.");
                    }
                }
                System.out.println();
                System.out.println("Total number of messages sent: " + Message.returnTotalMessages());

                if ("Send".equalsIgnoreCase(action)) {
                    try {
                        javax.swing.JOptionPane.showMessageDialog(null,
                            "Message Details:\n" +
                            "MessageID: " + message.getId() + "\n" +
                            "Message Hash: " + message.getMessageHash() + "\n" +
                            "Recipient: " + recipient + "\n" +
                            "Message: " + text,
                            "Message",
                            javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    } catch (Throwable t) {
                        // ignore if GUI not available
                    }
                } else if ("Store".equalsIgnoreCase(action)) {
                    try {
                        message.storeMessage("messages.jsonl");
                    } catch (Exception e) {
                        System.out.println("Failed to store message: " + e.getMessage());
                    }
                }
            } else if ("2".equals(choice)) {
                String recent = Message.printMessages();
                if (recent.isEmpty()) {
                    System.out.println("No messages sent yet.");
                } else {
                    System.out.println("\nRecently Sent Messages:\n" + recent);
                }
            } else if ("3".equals(choice)) {
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }

        System.out.println("Total messages sent: " + Message.returnTotalMessages());
        input.close();
    }
}
