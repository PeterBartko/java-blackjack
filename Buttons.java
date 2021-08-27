package sk.stuba.fei.uim.oop.BlackJack;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Buttons extends JButton implements ActionListener {

    private final BlackJack game;

    public Buttons(BlackJack game, String string){
        super(string);
        this.game = game;
        this.addActionListener(this);
        this.setFocusable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Reset")) {
            game.reset();
            afterRound();
            game.refresh();
        }
        if (check(e.getActionCommand())){
            if (!game.isPlace()){
                int bet = Integer.parseInt(e.getActionCommand());
                if (game.getMoney() - bet >= 0){
                    game.setBet(bet);
                    game.setMoney(-bet);
                }
                afterRound();
                game.setBeforeBet(true);
            }
        }
        if (e.getActionCommand().equals("All in")){
            if (!game.isPlace()){
                game.setBet(game.getMoney());
                game.setMoney(-game.getMoney());
                afterRound();
                game.setBeforeBet(true);
            }
        }
        if (e.getActionCommand().equals("Hit")){
            if (game.isPlace()){
                game.hit();
                game.refresh();
            }
        }
        if (e.getActionCommand().equals("Stand")){
            if (game.isPlace()){
                game.stand();
                game.refresh();
            }
        }
        if (e.getActionCommand().equals("Place bet")){
            if (game.getBet() > 0 && game.isBeforeBet()){
                game.init();
                game.setPlace(true);
                game.blackjack();
                game.refresh();
                game.setBeforeBet(false);
            }

        }
    }

    private void afterRound(){
        game.setWinState(false);
        game.setBlackjack(false);
        game.setLostState(false);
        game.setTieState(false);
        game.clear();
        game.refresh();
    }

    private boolean check(String string){
        return string.equals("2") || string.equals("5") || string.equals("20") || string.equals("50") || string.equals("100");
    }

}
