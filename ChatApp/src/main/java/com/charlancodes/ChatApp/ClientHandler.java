package com.charlancodes.ChatApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private PrintWriter out;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            String messageFromClient;
            while ((messageFromClient = in.readLine()) != null) {
                if (messageFromClient.equalsIgnoreCase("exit"))
                    break;

                System.out.println(ThreadColor.ANSI_RED + "Client says: " + messageFromClient);
                ServerClass.broadcastMessage(messageFromClient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }
}
