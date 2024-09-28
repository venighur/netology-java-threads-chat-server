package ru.netology;

public class Main {
    public static void main(String[] args) {
        try {
            Server server = new Server();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}