package org.example.device;

import org.json.JSONObject;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class JGenerator {
    private ServerSocket serverSocket;
    private int port;
    public static int clients = 0;

    public static void main(String[] args) throws IOException {
        //System.out.println("Hello Java");
        //try {
            ServerSocket ss = new ServerSocket(7777);

            Socket s = ss.accept();
            DataInputStream dataInputStream = new DataInputStream(s.getInputStream());
            String din = dataInputStream.readUTF();




    }
}
