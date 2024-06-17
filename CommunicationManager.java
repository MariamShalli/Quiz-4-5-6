package quiz5;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public abstract class CommunicationManager {

    public static class ChatMessage {
        private String sender;
        private String message;

        public ChatMessage(String sender, String message) {
            this.sender = sender;
            this.message = message;
        }

        public String getSender() {
            return sender;
        }

        public String getMessage() {
            return message;
        }
    }

    public static class ChatRequest {
        private String userMessage;
        private List<ChatMessage> chatHistory;

        public ChatRequest(String userMessage, List<ChatMessage> chatHistory) {
            this.userMessage = userMessage;
            this.chatHistory = chatHistory;
        }

        public String getUserMessage() {
            return userMessage;
        }

        public List<ChatMessage> getChatHistory() {
            return chatHistory;
        }
    }

    public static class ChatResponse {
        private String response;

        public ChatResponse(String response) {
            this.response = response;
        }

        public String getResponse() {
            return response;
        }
    }

    public abstract String sendMessage(String userMessage, List<ChatMessage> chatHistory);
}
