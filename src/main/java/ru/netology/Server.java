package ru.netology;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private final ArrayList<ClientHandler> clients = new ArrayList<>();

    public Server() {
        Config config = new Config();

        try (ServerSocket serverSocket = new ServerSocket(config.getPort())) {
            System.out.println("Сервер запущен!");
            while (!serverSocket.isClosed()) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler client = new ClientHandler(clientSocket, this);
                clients.add(client);
                new Thread(client).start();
                System.out.println("Новый пользователь подключился к чату");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendToAll(String msg) {
        for (ClientHandler client : clients) {
            client.send(msg);
        }
    }
}
