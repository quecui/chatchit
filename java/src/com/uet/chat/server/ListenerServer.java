package com.uet.chat.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by StormSpirit on 11/26/2016.
 * 23
 */
public class ListenerServer extends Thread {
    Socket socket;
    Model model;
    String clientID;

    public ListenerServer(Socket socket, Model model){
        this.socket = socket;
        this.model = model;
    }

    public void run(){
        boolean error = false;
        String message;

        try {
            boolean check = true;
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            clientID = br.readLine();
            if(model.getModelList().size() != 0){
                for(Model i:model.getModelList()){
                    if(i.getClientID().equals(clientID)){
                        bw.write("0");
                        bw.newLine();
                        bw.flush();
                        bw.close();
                        return;
                    }
                }
            }

            model.setClientID(clientID);
            model.setSocket(socket);
            model.addClient();

            String listUser = "0";
            for(Model i:model.getModelList()){
                listUser += " - " + i.getClientID();
            }

            bw.write(listUser);
            bw.newLine();
            bw.flush();
            message = "HAS CONNECTED!";

            while (true){
                if(check == false){
                    message = br.readLine();
                }
                check = false;
                model.sendMessage(message, clientID);
            }



        }catch (Exception e){
            System.out.println(e.getMessage());
            error = true;
        }finally {
            if(error){
                for(Model i:model.getModelList()){
                    if(i.getClientID().equals(clientID)){
                        model.getModelList().remove(i);
                        message = "HAS DISCONECTED!";

                        model.sendMessage(message, clientID);
                        break;
                    }
                }
            }
        }
    }
}
