package org.example.device;


import java.io.DataOutputStream;
import java.net.Socket;

public class ClinetDs {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 7777);
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());

            dout.writeUTF("Hello ");
            dout.flush();
            dout.close();
            s.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
