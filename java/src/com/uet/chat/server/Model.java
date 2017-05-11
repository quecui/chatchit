package com.uet.chat.server;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by StormSpirit on 11/26/2016.
 */
public class Model {
    String clientID;
    Socket socket;
    List<Model> modelList;

    public Model(String clientID, Socket socket) {
        this.clientID = clientID;
        this.socket = socket;
    }

    public Model(){
        this.clientID = "0";
        this.socket = null;
        modelList = new ArrayList<Model>();
    }


    public synchronized boolean addClient(){
        modelList.add(new Model(clientID, socket));
        return true;
    }

    public synchronized void sendMessage(String message, String ID){
        try {
            System.out.println("Client " + ID + " say:" + message);
            if(message.equals("HAS CONNECTED!") || message.equals("HAS DISCONECTED!")){
                message += getListUser();
            }

            for(Model i:modelList){
                if(!i.getClientID().equals(ID)){
                    int address = Integer.parseInt(i.getClientID()) + 2000;
                    Socket socketClient = new Socket("localhost",address);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
                    System.out.println("Sent to: " + i.getClientID()+ " at port: " + address);
                    bw.write("Client " + ID + ": " + message);
                    bw.newLine();
                    bw.flush();
                    bw.close();
                    socketClient.close();
                }
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private String getListUser(){
        String inLine = " |  ListUser: 0";
        for(Model i:modelList){
            inLine += " - " + i.getClientID();
        }
        return inLine;
    }
    public List<Model> getModelList() {
        return modelList;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
