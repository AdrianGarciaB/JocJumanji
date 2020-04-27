package com.adrianpratik.control;

import com.adrianpratik.model.CardType;
import com.adrianpratik.sprites.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int playerNumber;
    private List<Card> cardList;

    public Player(int playerNumber){
        this.playerNumber = playerNumber;
        cardList = new ArrayList<>();
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void addCard(Card.Type type, int cardNumber, int cardPosition){
        CardType cardPositions = GameWindow.cardsPositions.getPlayer().get(playerNumber).getCard().get(cardPosition);
        Card tmp = new Card(cardPositions.getX(), cardPositions.getY(), type, cardNumber, cardPosition, false);
        tmp.setFlipped(true);
        cardList.add(tmp);
    }

    public void addCard(Card card, int position, boolean flipped){
        CardType cardPositions = GameWindow.cardsPositions.getPlayer().get(playerNumber).getCard().get(position);
        Card tmp = new Card(cardPositions.getX(), cardPositions.getY(), card.getType(), card.getCardNumber(), position, true);
        tmp.setHide(false);
        tmp.setFlipped(flipped);
        cardList.add(tmp);
    }

    public void drawCards(){
        cardList.forEach(Card::draw);
    }

    public void clearCards() {
        cardList = new ArrayList<>();
    }

    public void hideCard(int cardPosition){
        getCardList().get(cardPosition).setHide(true);
    }
}
