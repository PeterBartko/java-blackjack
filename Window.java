package sk.stuba.fei.uim.oop.BlackJack;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class Window extends JFrame {

    private final JFrame window;

    public Window(BlackJack game){
        this.window = new JFrame("Black Jack 3.0");
        window.setSize(450,395);
        window.setVisible(true);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());

        var buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2,5));

        buttonPanel.add(new Buttons(game,"2"));
        buttonPanel.add(new Buttons(game,"5"));
        buttonPanel.add(new Buttons(game,"20"));
        buttonPanel.add(new Buttons(game,"50"));
        buttonPanel.add(new Buttons(game,"100"));
        buttonPanel.add(new Buttons(game,"All in"));
        buttonPanel.add(new Buttons(game,"Reset"));
        buttonPanel.add(new Buttons(game,"Place bet"));
        buttonPanel.add(new Buttons(game,"Hit"));
        buttonPanel.add(new Buttons(game,"Stand"));

        window.add(buttonPanel,BorderLayout.SOUTH);
        window.validate();
    }

    public JFrame getWindow() {
        return window;
    }
}
