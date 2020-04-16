package com.adrianpratik.control;

import com.adrianpratik.model.Packet;
import com.adrianpratik.model.packetOutgoing.JoinRequestOutgoing;
import com.adrianpratik.sprites.Card;
import javafx.scene.image.Image;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientTCP extends Thread{
    public static final String hostname = "127.0.0.1";
    public static final int port = 20200;
    boolean continueConnected = true;
    private GameController gameController;

    public ClientTCP(GameController gameController) {
        this.gameController = gameController;
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
            gameController.getMainMenu().setConectionStatus(new Image(GameController.imageURI+"waitplayers.png"));

            // Esperamos respuesta del servidor para saber cuando inicia la partida.
            connectionResponse = (Packet) in.readObject();
            if (connectionResponse.responseCode != 200)

            while(continueConnected){
                //enviament el número i els intents

            }
            close(socket);
        } catch (UnknownHostException ex) {
            System.out.println("Error de connexió. No existeix el host: " + ex.getMessage());
            gameController.getMainMenu().setConectionStatus(new Image(GameController.imageURI+"failed.png"));

        } catch (IOException ex) {
            System.out.println("Error de connexió indefinit: " + ex.getMessage());
            gameController.getMainMenu().setConectionStatus(new Image(GameController.imageURI+"failed.png"));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(3000);
            gameController.getMainMenu().setConectionStatus(new Image(GameController.imageURI + "connecting.png"));
            gameController.getMainMenu().setConnecting(false);
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
            Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
