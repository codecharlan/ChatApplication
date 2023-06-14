package com.charlancodes.ChatApp;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientClass {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 6690;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        ClientThread clientThread = new ClientThread(socket);
        Thread thread = new Thread(clientThread);
        thread.start();

        Scanner userInput = new Scanner(System.in);
        String message;

        while (true) {
            message = userInput.nextLine();
            clientThread.sendMessage(message);
            if (message.equalsIgnoreCase("exit"))
                break;
        }

        socket.close();
    }
}
