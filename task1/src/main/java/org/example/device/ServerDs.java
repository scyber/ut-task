package org.example.device;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDs {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(7777);
            Socket s = ss.accept();
            DataInputStream din = new DataInputStream(s.getInputStream());
            String str = din.readUTF();
            //System.out.println(str);
        } catch (Exception e) {
            //ToDo logger better way
            System.out.println(e.getStackTrace());
        }
    }
}
