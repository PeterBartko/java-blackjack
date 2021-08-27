package sk.stuba.fei.uim.oop.BlackJack;

import lombok.Getter;

import java.awt.image.BufferedImage;

@Getter
public class Card {

    private final int value;
    private final BufferedImage image;

    public Card(int type, BufferedImage image){
        this.value = type;
        this.image = image;
    }

}
