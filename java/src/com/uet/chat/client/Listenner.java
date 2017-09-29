package com.uet.chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by StormSpirit on 11/26/2016.
 * test git commit
 */
public class Listenner extends Thread{
    int clientID;

    public Listenner(int clientID){
        this.clientID = clientID;
    }

    public void run(){
        try {
            ServerSocket serverSocket = new ServerSocket(clientID + 2000);
            while (true){
                Socket socket = serverSocket.accept();
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message = br.readLine();
                System.out.println(message);
                br.close();
                socket.close();
            }



        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
