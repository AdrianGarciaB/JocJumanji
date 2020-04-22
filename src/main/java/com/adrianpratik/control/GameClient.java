package com.adrianpratik.control;

import com.adrianpratik.model.Packet;
import com.adrianpratik.sprites.Card;
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
    public static final String hostname = "localhost";
    public static final int port = 20200;
    boolean continueConnected = true;
    private GameWindow gameWindow;
    private int playerId;
    ObjectOutputStream out;


    public GameClient(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    public void run() {
        Socket socket;
        ObjectInputStream in;

        try {
            System.out.println("[CONNECTION] Connecting...");

            socket = new Socket(InetAddress.getByName(hostname), port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            // Enviamos un paquete para saber si la comunicacion con el servidor ha sido satisfactoria.
            Packet connectionResponse = (Packet) in.readObject();
            playerId = (int) connectionResponse.data;
            if (connectionResponse.responseCode != 200) socket.close();

            System.out.println("[CONNECTION] Sucessful");
            gameWindow.getMainMenu().setConectionStatus(new Image(GameWindow.imageURI+"waitplayers.png"));

            while(continueConnected){
                //enviament el número i els intents
                connectionResponse = (Packet) in.readObject();
                switch (connectionResponse.responseCode){
                    // Paquete para saber si estamos conectados.
                    case 100: {
                        out.writeObject(new Packet(100, null));
                        out.flush();
                        System.out.println("[PACKET] Alive send");
                        break;
                    }
                    // Paquete de inicio de la partida.
                    case 101: {
                        System.out.println(connectionResponse.responseCode);
                        gameWindow.getMainMenu().setConnecting(false);
                        gameWindow.isInMenu = false;
                        gameWindow.setGameInfoImage(new Image("images/spreadingCards.png"));
                        gameWindow.springCards(connectionResponse.data);
                        gameWindow.setGameInfoImage(null);
                        out.writeObject(new Packet(102, null));
                        out.flush();
                        break;
                    }
                    case 102: {
                        Card card = (Card) connectionResponse.data;
                        card.generateSprite();
                        gameWindow.getDecks().discardCard(card);
                        break;
                    }
                    case 103: {
                        if ((int) connectionResponse.data == playerId) {
                            gameWindow.isMyTurn = true;
                            gameWindow.setGameInfoImage(new Image("images/yourTurn.png"));
                        }
                        else {
                            gameWindow.isMyTurn = false;
                            gameWindow.setGameInfoImage(null);
                        }
                        break;
                    }
                    case 104: {
                        Card cardRobbed = (Card) connectionResponse.data;
                        gameWindow.setCardSelected(cardRobbed);
                        gameWindow.repartPlayer.stop();
                        gameWindow.repartPlayer.play();
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

    public void requestCard() {
        try {
            System.out.println("Requesting card");
            out.writeObject(new Packet(104, null));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void nextTurn(Card cardSelected) {
        cardSelected.setSprite(null);
        try {
            System.out.println(cardSelected.getCardNumber() + ":" + cardSelected.getType());
            out.writeObject(new Packet(105, cardSelected));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void discardCard(Card cardSelected) {
        cardSelected.setSprite(null);
        try {
            out.writeObject(new Packet(106, cardSelected));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
