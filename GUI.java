package sk.stuba.fei.uim.oop.BlackJack;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class GUI extends JLabel {

    private final BlackJack game;

    public GUI(BlackJack game){
        this.game = game;
    }

    @Override
    protected void paintComponent(Graphics g) {

        background(g);


        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.setColor(Color.orange);
        g.drawString("Money: " + game.getMoney() + "€", 10, 25);
        g.drawString("Bet: " + game.getBet() + "€", 10, 55);
        g.drawString("Cards: " + game.getNumOfCards(), 330,25);

        g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        g.setColor(Color.white);
        g.drawString("Dealer: ", 10, 81);
        g.setColor(Color.yellow);
        g.drawString(Integer.toString(game.getCSum()), 10, 120);
        g.setColor(Color.white);

        int space = 1, x = 60;

        for (Card i : game.getCroupierCards()) {
            g.drawImage(i.getImage(), x + 10 * space, 70, null);
            space++;
            x = x + 32;
        }

        g.drawString("Player: ", 10, 162);
        g.setColor(Color.yellow);
        g.drawString(Integer.toString(game.getPSum()), 10, 200);
        space = 1;
        x = 60;
        for (Card card : game.getPlayerCards()) {
            g.drawImage(card.getImage(), x + 10 * space, 150, null);
            space++;
            x = x + 32;
        }

        endgameAndBet(g);

    }



    private void background(Graphics g) {
        BufferedImage img = null;

        try {
            img = ImageIO.read(new File("images/BJ.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Color background = new Color(0,90,0);
        g.setColor(background);
        g.fillRect(0,0,450,350);
        Color desk = new Color(0,60,0);
        g.setColor(desk);
        g.fillRect(5,63,425,66);
        g.fillRect(5,143,425,66);

        g.drawImage(img, 145,6,null);
    }


    private void endgameAndBet(Graphics g){
        g.setFont(new Font("Arial", Font.PLAIN, 35));
        if (game.isBeforeBet()){
            String s = "Placeyourbet!";
            int x = 70;
            g.setColor(Color.white);
            g.fillRect(70,262,289,36);
            for (int i = 0; i < s.length(); i++) {
                if (i == 1) x = x + 2;
                if (i == 2) x = x - 12;
                if (i == 5 || i == 9) x = x + 17;
                if (i % 2 == 0){
                    g.setColor(Color.black);
                }else {
                    g.setColor(Color.red);
                }
                g.drawString(Character.toString(s.charAt(i)),x,290);
                x = x + 21;
            }
            g.setColor(Color.white);
           // g.drawString("Place your bet!", 90, 290);
        }
        if (game.isWinState()){
            g.setColor(Color.green);
            g.drawString("WIN! " + game.getPrize() + "€", 90, 250);
        }
        if (game.isLostState()){
            g.setColor(Color.red);
            g.drawString("LOST! -" + game.getPrize() + "€", 90, 250);
        }
        if (game.isBlackjack()){
            g.setColor(Color.green);
            g.drawString("BLACK JACK! " + game.getPrize() + "€", 50, 250);
        }
        if (game.isTieState()){
            g.setColor(Color.cyan);
            g.drawString("TIE!", 180, 250);
        }
        if (game.getMoney() == 0 && game.getBet() == 0){
            game.setBeforeBet(false);
            game.setLostState(false);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 45));
            g.setColor(Color.red);
            g.drawString("GAME OVER!", 75, 268);
        }

    }


}