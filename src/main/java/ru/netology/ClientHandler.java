package ru.netology;

import java.io.*;
import java.net.Socket;
import java.util.Date;

public class ClientHandler implements Runnable {
    private final Socket socket;
    private final Server server;
    private final PrintWriter out;

    public ClientHandler(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.server = server;
        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            Logger logger = new Logger();
            String nickname = in.readLine();

            while (!socket.isClosed()) {
                String entry = in.readLine();
                if (entry.equals("exit")) {
                    break;
                }
                String str = "[" + nickname + "] " + entry;
                server.sendToAll(str);
                logger.writeLog(new Date() + " - " + str + "\n");
            }
            socket.close();
            out.close();
        } catch (IOException e) {
            System.out.println("run error");
            throw new RuntimeException(e);
        }
    }

    public void send(String msg) {
        out.println(msg);
    }
}
