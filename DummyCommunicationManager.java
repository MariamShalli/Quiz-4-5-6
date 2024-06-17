package quiz5;

import java.util.List;

public class DummyCommunicationManager extends CommunicationManager {

    @Override
    public String sendMessage(String userMessage, List<CommunicationManager.ChatMessage> chatHistory) {
        if (userMessage.equalsIgnoreCase("hello")) {
            return "Good day";
        } else if (userMessage.equalsIgnoreCase("what time is it?")) {
            return "9:00 AM";
        } else if (userMessage.equalsIgnoreCase("I should go!")) {
            return "Wait for me!";
        } else {
            return "I don't understand.";
        }
    }
}
