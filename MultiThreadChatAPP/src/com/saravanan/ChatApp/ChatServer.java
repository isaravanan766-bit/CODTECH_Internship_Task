package com.saravanan.ChatApp;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ChatServer {

	    private static List<ClientHandler> clientList = new ArrayList<>();

	    public static void main(String[] args) throws IOException {
	        ServerSocket serverSocket = new ServerSocket(1605);
	        System.out.println("✅ Server started on port 1605...");

	        while (true) {
	            Socket clientSocket = serverSocket.accept();
	            ClientHandler client = new ClientHandler(clientSocket);
	            clientList.add(client);
	            new Thread(client).start();
	        }
	    }

	    public static void broadcast(String message) {
	        for (ClientHandler client : clientList) {
	            client.sendMessage(message);
	        }
	    }

	    static class ClientHandler implements Runnable {
	        private Socket socket;
	        private BufferedReader in;
	        private PrintWriter out;
	        private String username;

	        public ClientHandler(Socket socket) {
	            this.socket = socket;
	        }

	        public void run() {
	            try {
	                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	                out = new PrintWriter(socket.getOutputStream(), true);

	                out.println("Enter your name: ");
	                username = in.readLine();
	                broadcast( username + " joined the chat!!");

	                String message;
	                while ((message = in.readLine()) != null) {
	                    String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
	                    String fullMessage = "[" + time + "] " + username + ": " + replaceEmojis(message);
	                    broadcast(fullMessage);
	                }
	            } catch (IOException e) {
	                System.out.println(" Connection lost with " + username);
	            } finally {
	                try {
	                    socket.close();
	                } catch (IOException e) {}
	                clientList.remove(this);
	                broadcast( username + " left the chat!!.");
	            }
	        }

	        public void sendMessage(String message) {
	            out.println(message);
	        }

	        private String replaceEmojis(String msg) {
	            Map<String, String> emojiMap = new HashMap<>();
	            emojiMap.put(":smile", "😄");
	            emojiMap.put(":heart", "❤️");
	            emojiMap.put(":thumbsup", "👍");
	            emojiMap.put(":fire", "🔥");
	            emojiMap.put(":clap", "👏");
	            emojiMap.put(":sad", "😢");
	            emojiMap.put(":laugh", "😂");
	            emojiMap.put(":angry", "😠");

	            for (String key : emojiMap.keySet()) {
	                msg = msg.replace(key, emojiMap.get(key));
	            }
	            return msg;
	        }
	    
	}

}
