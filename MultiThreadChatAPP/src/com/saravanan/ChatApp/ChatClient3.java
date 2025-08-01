package com.saravanan.ChatApp;
import java.io.*;
import java.net.*;

public class ChatClient3 {

	    public static void main(String[] args) throws IOException {
	        Socket socket = new Socket("localhost", 1605);
	        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

	        System.out.println("✅ Client3 Connected to the chat server.");

	        // this thread will receive messages
	        new Thread(() -> {
	            String serverMsg;
	            try {
	                while ((serverMsg = in.readLine()) != null) {
	                    System.out.println(serverMsg);
	                }
	            } catch (IOException e) {
	                System.out.println("⚠️ Client3 Disconnected from server.");
	            }
	        }).start();

	        //  thread for sending input given by user
	        String userMsg;
	        while ((userMsg = input.readLine()) != null) {
	            out.println(userMsg);
	        }
	    }
	}
