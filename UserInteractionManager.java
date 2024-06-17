package quiz5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import quiz6.SpecialCommunicationManager;


public class UserInteractionManager {
    private CommunicationManager commonCommunicationManager;
    private CommunicationManager specialCommunicationManager;
    private List<CommunicationManager.ChatMessage> chatHistory;

    public UserInteractionManager(CommunicationManager commonCommunicationManager, CommunicationManager specialCommunicationManager) {
        this.commonCommunicationManager = commonCommunicationManager;
        this.specialCommunicationManager = specialCommunicationManager;
        this.chatHistory = new ArrayList<>();
    }

    public void startChat() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Chatbot is ready. Type 'exit' to end the chat.");

        while (true) {
            System.out.print("User: ");
            String userMessage = scanner.nextLine();
            if (userMessage.equalsIgnoreCase("exit")) {
                break;
            }

            chatHistory.add(new CommunicationManager.ChatMessage("User", userMessage));

            // Check if user message contains "help" to decide which communication manager to use
            CommunicationManager currentCommunicationManager = userMessage.toLowerCase().contains("help")
                    ? specialCommunicationManager : commonCommunicationManager;

            String response = currentCommunicationManager.sendMessage(userMessage, chatHistory);

            System.out.println("Chatbot: " + response);
            chatHistory.add(new CommunicationManager.ChatMessage("Chatbot", response));
        }
        scanner.close();
    }

    public static void main(String[] args) {
        String commonServiceUrl = "http://common-service-url"; // Replace with actual URL
        String specialServiceUrl = "http://special-service-url"; // Replace with actual URL

        CommunicationManager commonCommunicationManager = new DummyCommunicationManager();
        CommunicationManager specialCommunicationManager = new SpecialCommunicationManager(commonServiceUrl, specialServiceUrl);

        UserInteractionManager userInteractionManager = new UserInteractionManager(commonCommunicationManager, specialCommunicationManager);
        userInteractionManager.startChat();
    }
}
