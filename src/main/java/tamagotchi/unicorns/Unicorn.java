package tamagotchi.unicorns;

import tamagotchi.utils.ProgressBar;

public class Unicorn {

    public static final int MAX_STAT = 100;
    private String name;
    private int health;
    private int hunger;
    private int happiness;
    private int energy;
    private boolean isAlive;

    public Unicorn (String name) {
        this.name = name;
        this.health = MAX_STAT;
        this.hunger = 50;
        this.happiness = 75;
        this.energy = MAX_STAT;
        this.isAlive = true;
    }

    public void feed() {
        if (!isAlive) return;
        hunger = Math.max(0, hunger - 30);
        health = Math.min(MAX_STAT, health + 10);
        happiness = Math.min(MAX_STAT, happiness + 5);
//        energy = Math.min(MAX_STAT, energy + 5);
        System.out.println("🦄 " + name + " с удовольствием жуёт радугу! \nЗдоровье: +10 \nГолод: -30 \nСчастье: +5");
    }

    public void play() {
        if (!isAlive) return;
        hunger = Math.max(0, hunger + 15);
        happiness = Math.min(MAX_STAT, happiness + 10);
        energy = Math.min(MAX_STAT, energy - 15);
        System.out.println("🦄 " + name + " с удовольствием играет! \nЭнергия: -15 \nГолод: +15 \nСчастье: +10");
    }

    public void sleep() {
        if (!isAlive) return;
        hunger = Math.max(0, hunger + 50);
        happiness = Math.min(MAX_STAT, happiness + 10);
        energy = Math.min(MAX_STAT, energy + 100);
        System.out.println("🦄 " + name + " Выспался! \nЭнергия: Максимум \nГолод: +50 \nСчастье: +10");
    }

    public void tick() {
        if (!isAlive) return;
        hunger = Math.min(MAX_STAT, hunger + 10);
        happiness = Math.max(0, happiness - 6);
        energy = Math.max(0, energy - 5);
        checkHealth();
    }

    private void checkHealth() {
        if (hunger >= 90) health -= 10;
        if (happiness <= 20) health -= 5;
        if (energy <= 20) health -= 5;
        if (energy <= 10) {
            isAlive = false;
            System.out.println("🦄 очень устал, уложи его спать ⚡");
        }
        if (health <= 0 || energy <= 0 || hunger >= MAX_STAT || happiness <= 0) {
            isAlive = false;
        }
    }

   public void showStatus() {
        System.out.println("=== " + name + " (Единорог) ===");
        System.out.println("Здоровье: " + ProgressBar.build(health, MAX_STAT));
        System.out.println("Голод:   " + ProgressBar.build(hunger, MAX_STAT));
        System.out.println("Счастье: " + ProgressBar.build(happiness, MAX_STAT));
        System.out.println("Энергия: " + ProgressBar.build(energy, MAX_STAT));
        System.out.println("Статус:  " + (isAlive ? "❤️ Жив" : "💀 Мёртв"));
    }

    public String getName() { return name; }

    public int getHealth() { return health; }

    public int getHunger() { return hunger; }

    public int getHappiness() { return happiness; }
    public int getEnergy() { return energy; }

    public boolean isAlive() { return isAlive; }

    public void setHunger(int hunger) { this.hunger = Math.min(hunger, MAX_STAT); }
    public void setHappiness(int happiness) { this.happiness = Math.min(happiness, MAX_STAT); }
    public void setEnergy(int energy) { this.happiness = Math.min(happiness, MAX_STAT); }
}
