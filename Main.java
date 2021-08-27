package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.BlackJack.BlackJack;
import sk.stuba.fei.uim.oop.RandomShit.RandomShit;
import sk.stuba.fei.uim.oop.TicTacToe.TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
//import sk.stuba.fei.uim.oop.kalkulacka.Window;

class DrawPanel extends JPanel
{
    int i = 0;
    public DrawPanel() {
        ActionListener animate = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                repaint();
            }
        };
        Timer timer = new Timer(50,animate);
        timer.start();
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(new Color(71,12,3));
        g.fillRect(35,30,410,90);

        Color c = new Color (12*i/2,8*i/2,2*i/2);
        g.setColor(c);
        g.fillRect( 30+10*i,35,20,80);

        i+=2;
        if (i>40) i = 0;
    }
}


public class Main {

    static int hundoGrind() {
        Random rand = new Random();
        int attack, stamina, hp, shiny, count = 0;

        while (true){
            //iv
            attack = rand.nextInt(7);
            stamina = rand.nextInt(7);
            hp = rand.nextInt(7);

            //shiny
            shiny = rand.nextInt(26);

            if (attack == 6 && stamina == 6 && hp == 6 && shiny == 0) break;
            count++;
        }
        //System.out.println("You caught shundo in " + count + "th raid!");
        return count;
    }

    public static void main(String[] args) {

      new BlackJack();
        int avg = 0;
        int[] pole = {1, 2, 4};
        //for (int i = 0; i < 100; i++) {
        //    avg += hundoGrind();
        //}

        //System.out.println("average shundo caught in raid is: " + avg / 100);

    //hundoGrind();

      //new TicTacToe();

      //  RandomShit.inverseNum(97,60);
      //  int[] A = {1,1,3,4,5};
      //  int[] B = {3,5,1,4,1};
      //  System.out.println(RandomShit.sameArrays(A,B));

      //  DrawPanel panel = new DrawPanel();
        /*JFrame app = new JFrame();
        app.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        app.add(panel);
        app.setSize(500,200);
        app.setVisible(true);*/
        //new BlackJack();

        //new TicTacToe();

        //new mw();

        //new Window();

        //RandomShit.quad("-3x^2+43x-2");

/*
        int[][] A = {{1,2,3},{4,5,6}};
        int[][] B = {{6,5},{4,3},{2,1}};
        RandomShit.printMatrix(RandomShit.plusMatrix(A,B));
        RandomShit.plusMatrix(A,B);
        RandomShit.timesMatrix(A,B);

        String string = "";
        System.out.println('"' + string + '"');
        System.out.println(RandomShit.parseInts(string));
        System.out.println(RandomShit.isStringNumeric(string));
        System.out.println(RandomShit.genPassword(RandomShit.ALPHA_NUM_ONLY, 10)); */

    }


}


