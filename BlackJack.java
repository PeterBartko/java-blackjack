package sk.stuba.fei.uim.oop.BlackJack;

import lombok.Data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.Timer;

/* 3.1
TODO slower picking cards
3.2
TODO new button: surrender: na zaciatku moze sa vzdat a vrati sa mu polovica vkladu
TODO new button: double: zdvojansoby sa stavka a dostane dalsiu kartu a zaroven konci kolo
TODO new button: split: ak ma hrac 2 rovnake karty (hodnotou) a dostane ku kazdej karte dasli kartu a hra dve hry
*/

@Data
public class BlackJack {

    private final int STARTING_MONEY = 100;

    private int money;
    private int bet;
    private int pSum;
    private int cSum;
    private int prize;
    private int numOfCards;

    private boolean beforeBet;
    private boolean winState;
    private boolean lostState;
    private boolean tieState;
    private boolean blackjack;
    private boolean place;

    private ArrayList<Card> playerCards;
    private ArrayList<Card> croupierCards;
    private ArrayList<Card> cards;

    private Window window;
    private Timer timer;

    public BlackJack() {
        this.money = STARTING_MONEY;
        this.bet = 0;
        this.beforeBet = true;
        this.playerCards = new ArrayList<>();
        this.croupierCards = new ArrayList<>();
        this.cards = new ArrayList<>();
        this.window = new Window(this);
        this.timer = new Timer(75,null);
        play();
    }

    public void play(){
        refresh();
        newSetOfCards();
    }


    private int count(ArrayList<Card> cards){
        int sum = 0;
        for (Card card : cards) {
            if (card.getValue() >= 11) sum += 10;
            else if (card.getValue() == 1) {
                if (sum + 11 > 21)
                    sum += 1;
                else
                    sum += 11;
            }
            else
                sum += card.getValue();
        }
        return sum;
    }

    private void newSetOfCards(){
        try {
            for (int j = 0; j < 2; j++) {
                for (int i = 0; i < 52; i++) {
                    cards.add(new Card(i % 13 + 1, ImageIO.read(new File("images/" + (i + 1) + ".jpg"))));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        numOfCards = cards.size();
        Collections.shuffle(cards);
    }

    public void init(){
        pickCard(playerCards);
        pickCard(playerCards);
        pSum = count(playerCards);
        pickCard(croupierCards);
        cSum = count(croupierCards);
    }

    public void clear(){
        playerCards.clear();
        croupierCards.clear();
        pSum = 0;
        cSum = 0;
    }

    public void reset(){
        clear();
        winState = false;
        blackjack = false;
        beforeBet = true;
        money = STARTING_MONEY; bet = 0;
        cards.clear();
        newSetOfCards();
    }

    public void refresh(){
        var paint = new GUI(this);
        window.getWindow().add(paint, BorderLayout.CENTER);
        window.getWindow().validate();
    }

    public void stand(){
        while (cSum <= 17) {
            pickCard(croupierCards);
            cSum = count(croupierCards);
        }
        if (cSum > 21 || cSum < pSum) { // dealer lost
            prize = bet * 2;
            money += prize;
            bet = 0;
            beforeBet = true;
            winState = true;
            place = false;
        } else if (cSum == 21 || cSum > pSum){ // dealer won
            prize = bet;
            bet = 0;
            lostState = true;
            beforeBet = true;
            place = false;
        } else if (cSum == pSum){ // tie
            money += bet;
            bet = 0;
            tieState = true;
            beforeBet = true;
            place = false;
        }

    }

    public void hit(){
        pickCard(playerCards);
        pSum = count(playerCards);
        blackjack();
        if (pSum > 21){
            prize = bet;
            bet = 0;
            lostState = true;
            beforeBet = true;
            place = false;
        }
    }

    public void blackjack(){
        if (pSum == 21) {
            prize = (int) (bet * 2.5);
            money += prize;
            bet = 0;
            beforeBet = true;
            blackjack = true;
            place = false;
        }
    }

    private void pickCard(ArrayList<Card> who){
        who.add(cards.get(0));
        cards.remove(0);
        numOfCards = cards.size();
        if (numOfCards == 0){
            newSetOfCards();}
    }

    public void setMoney(int bet) {
        this.money += bet;
    }

    public void setBet(int bet) {
        this.bet += bet;
    }

}
