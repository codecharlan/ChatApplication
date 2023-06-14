package com.charlancodes.ChatApp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerClass {
    private static final int PORT = 6690;
    private static List<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        System.out.println(ThreadColor.ANSI_YELLOW + "Server Started: Waiting for Client Requests");
        ServerSocket serverSocket = new ServerSocket(PORT);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println(ThreadColor.ANSI_PURPLE + "Connection Established on Port " + PORT);

            ClientHandler clientHandler = new ClientHandler(clientSocket);
            clients.add(clientHandler);
            Thread clientThread = new Thread(clientHandler);
            clientThread.start();
        }
    }

    public static void broadcastMessage(String message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }
}
