package com.uet.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by StormSpirit on 11/26/2016.
 * 32323
 * sdsd
 * dfsdf
 * dsfsdf
 * sdfsdf
 * dfdfdf
 * fdfdfd
 * dsadsa
 * sadasd
 * asdasda
 * asdasd
 * fdsfds
 * fdfd
 * fdfffff
 */
public class ServerMain {
    //fsdfsdf
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1900);
        System.out.println("Server is listenning at port : 1900");
        System.out.println("Waiting connection from client...");
        Model model = new Model();

        while (true){
            Socket socket = serverSocket.accept();
            System.out.println("Welcome: " + socket.getInetAddress());
            new ListenerServer(socket, model).start();
        }
    }
}
