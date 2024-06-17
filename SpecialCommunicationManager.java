package quiz6;

import quiz5.CommunicationManager;
import quiz5.CommunicationManager.ChatMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class SpecialCommunicationManager extends CommunicationManager {
    private String commonServiceUrl;
    private String specialServiceUrl;

    public SpecialCommunicationManager(String commonServiceUrl, String specialServiceUrl) {
        this.commonServiceUrl = commonServiceUrl;
        this.specialServiceUrl = specialServiceUrl;
    }

    @Override
    public String sendMessage(String userMessage, List<ChatMessage> chatHistory) {
        try {
            // Check if user message or chat history contains "help"
            boolean isHelpRequested = userMessage.toLowerCase().contains("help");
            if (!isHelpRequested) {
                for (ChatMessage message : chatHistory) {
                    if (message.getMessage().toLowerCase().contains("help")) {
                        isHelpRequested = true;
                        break;
                    }
                }
            }

            // Determine the URL to use based on the presence of "help"
            String urlToUse = isHelpRequested ? specialServiceUrl : commonServiceUrl;

            // Make HTTP request using the determined URL
            URL url = new URL(urlToUse);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Send request
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read response
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            } else {
                return "Error: HTTP " + responseCode;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
