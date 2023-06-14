package com.charlancodes.ChatApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread implements Runnable {

    private Socket socket;
    private PrintWriter out;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println(ThreadColor.ANSI_YELLOW + "Client Started");
            System.out.println(ThreadColor.ANSI_CYAN + "Write Message to be Sent: ");
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            String messageFromServer;
            while ((messageFromServer = in.readLine()) != null) {
                System.out.println(ThreadColor.ANSI_RED + "Server says: " + messageFromServer);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }
}
