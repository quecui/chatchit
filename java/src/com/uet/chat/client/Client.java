package com.uet.chat.client;

import java.io.*;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by StormSpirit on 11/26/2016.
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1900);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        Random rd = new Random();
        int clientID = rd.nextInt(100);
        System.out.println("=========== Your ClientID : " + clientID + " ===========");
        bw.write(String.valueOf(clientID));
        bw.newLine();
        bw.flush();

        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String message = br.readLine();
        if(message.equals("0")){
            System.out.println("Deny access !");
        }
        else {
            System.out.println("List User: " + message);
            System.out.println("You can chat now!");
            new Listenner(clientID).start();
            Scanner input = new Scanner(System.in);
            while (true){
                message = input.nextLine();
                bw.write(message);
                bw.newLine();
                bw.flush();
            }
        }
    }
}
