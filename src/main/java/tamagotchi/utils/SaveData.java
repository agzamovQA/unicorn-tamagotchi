package tamagotchi.utils;

import tamagotchi.unicorns.Unicorn;

import java.io.Serializable;

public class SaveData implements Serializable {
    private static final long serialVersionUID = 1L;

    private Unicorn unicorn;

    private int turnCounter;

    public SaveData (Unicorn unicorn, int turnCounter) {
        this.unicorn = unicorn;
        this.turnCounter = turnCounter;
    }

    public Unicorn getUnicorn() { return unicorn; }

    public int getTurnCounter() { return turnCounter; }

    public void setUnicorn(Unicorn unicorn) { this.unicorn = unicorn; }

    public void setTurnCounter(int turnCounter) { this.turnCounter = turnCounter; }

}
