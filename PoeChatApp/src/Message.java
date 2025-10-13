import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class Message {
    private static final Random RANDOM = new Random();
    private static final List<Message> SENT_MESSAGES = new ArrayList<>();

    private final String id; // 10-digit random id
    private final int sequenceNumber; // nth message in this run
    private final String recipient;
    private final String text;
    private final String messageHash;
    private boolean isSent;
    private boolean isStored;
    private boolean isRead;
    private LocalDateTime timeRecieved;

    public Message(int sequenceNumber, String recipient, String text) {
        this.id = generateTenDigitId();
        this.sequenceNumber = sequenceNumber;
        this.recipient = recipient;
        this.text = text;
        this.messageHash = createMessageHash(this.id, sequenceNumber, text);
        this.isSent = false;
        this.isStored = false;
        this.isRead = false;
        this.timeRecieved = LocalDateTime.now();
    }

    public static String generateTenDigitId() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            builder.append(RANDOM.nextInt(10));
        }
        return builder.toString();
    }

    // Part 2 required checks
    public boolean checkMessageID() {
        return id != null && id.length() <= 10;
    }

    public int checkRecipientCell() {
        // Accept international numbers like +27XXXXXXXXX (SA) or general +<10-14 digits>
        if (recipient == null) return 0;
        boolean matchesInternational = recipient.matches("^\\+\\d{10,14}$");
        return matchesInternational ? 1 : 0;
    }

    public static String createMessageHash(String id, int sequenceNumber, String text) {
        String firstTwoOfId = id.substring(0, Math.min(2, id.length()));
        String[] words = text.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 0 ? words[words.length - 1] : "";
        String hash = firstTwoOfId + ":" + sequenceNumber + ":" + firstWord + lastWord;
        return hash.toUpperCase(Locale.ROOT);
    }

    public String sendMessage(String action) {
        // action: "Send", "Disregard", "Store"
        if ("Send".equalsIgnoreCase(action)) {
            this.isSent = true;
            SENT_MESSAGES.add(this);
            return "Message successfully sent.";
        } else if ("Disregard".equalsIgnoreCase(action)) {
            return "Press 0 to delete message.";
        } else if ("Store".equalsIgnoreCase(action)) {
            this.isStored = true;
            return "Message successfully stored.";
        }
        return "Unknown action.";
    }

    public static String printMessages() {
        StringBuilder builder = new StringBuilder();
        for (Message m : SENT_MESSAGES) {
            builder.append("MessageID: ").append(m.id)
                   .append("\nMessage Hash: ").append(m.messageHash)
                   .append("\nRecipient: ").append(m.recipient)
                   .append("\nMessage: ").append(m.text)
                   .append("\n---\n");
        }
        return builder.toString();
    }

    public static int returnTotalMessages() {
        return SENT_MESSAGES.size();
    }

    public void storeMessage(String filePath) throws IOException {
        // Minimal JSON line per message
        String json = "{\"id\":\"" + id + "\",\"seq\":" + sequenceNumber + ",\"recipient\":\"" + recipient + "\",\"hash\":\"" + messageHash + "\",\"text\":\"" + text.replace("\"", "\\\"") + "\"}";
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(json + System.lineSeparator());
        }
    }

    public void markAsRead() {
        this.isRead = true;
    }

    public String getStatus() {
        if (!isSent)
            return "Not sent";
        if (isSent && !isRead)
            return "✅✅";
        if (isRead)
            return "✅✅ (blue)";
        return "Unknown";
    }

    public String getMessagePanel() {
        return "Message: " + text + "\nTime: " + timeRecieved + "\nStatus: " + getStatus();
    }

    public String getId() { return id; }
    public String getRecipient() { return recipient; }
    public String getText() { return text; }
    public String getMessageHash() { return messageHash; }
}
