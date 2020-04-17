package com.adrianpratik.control;

import com.adrianpratik.model.Packet;
import javafx.application.Platform;
import javafx.scene.image.Image;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameClient extends Thread{
    public static final String hostname = "127.0.0.1";
    public static final int port = 20200;
    boolean continueConnected = true;
    private GameWindow gameWindow;

    public GameClient(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    public void run() {
        Socket socket;
        ObjectInputStream in;
        ObjectOutputStream out;

        try {
            System.out.println("[CONNECTION] Connecting...");

            socket = new Socket(InetAddress.getByName(hostname), port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            // Enviamos un paquete para saber si la comunicacion con el servidor ha sido satisfactoria.
            Packet connectionResponse = (Packet) in.readObject();
            if (connectionResponse.responseCode != 200) socket.close();

            System.out.println("[CONNECTION] Sucessful");
            gameWindow.getMainMenu().setConectionStatus(new Image(GameWindow.imageURI+"waitplayers.png"));

            while(continueConnected){
                //enviament el número i els intents
                connectionResponse = (Packet) in.readObject();
                switch (connectionResponse.responseCode){
                    case 100: {
                        out.writeObject(new Packet(100, null));
                        out.flush();
                        System.out.println("[PACKET] Alive send");
                        break;
                    }
                    case 101: {
                        System.out.println(connectionResponse.responseCode);
                        gameWindow.getMainMenu().setConnecting(false);
                        gameWindow.isInMenu = false;
                        break;
                    }
                }
                System.out.println(connectionResponse.responseCode);

            }
            close(socket);
        } catch (SocketException se) {
            System.out.println("Conexio amb el servidor perduda: " + se.getMessage());
            Platform.exit();

        } catch (UnknownHostException ex) {
            System.out.println("Error de connexió. No existeix el host: " + ex.getMessage());
            gameWindow.getMainMenu().setConectionStatus(new Image(GameWindow.imageURI+"failed.png"));

        } catch (IOException ex) {
            System.out.println("Error de connexió indefinit: " + ex.getMessage());
            ex.printStackTrace();

            gameWindow.getMainMenu().setConectionStatus(new Image(GameWindow.imageURI+"failed.png"));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(3000);
            gameWindow.getMainMenu().setConectionStatus(new Image(GameWindow.imageURI + "connecting.png"));
            gameWindow.getMainMenu().setConnecting(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void close(Socket socket){
        //si falla el tancament no podem fer gaire cosa, només enregistrar
        //el problema
        try {
            //tancament de tots els recursos
            if(socket!=null && !socket.isClosed()){
                if(!socket.isInputShutdown()){
                    socket.shutdownInput();
                }
                if(!socket.isOutputShutdown()){
                    socket.shutdownOutput();
                }
                socket.close();
            }
        } catch (IOException ex) {
            //enregistrem l'error amb un objecte Logger
            Logger.getLogger(GameClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
