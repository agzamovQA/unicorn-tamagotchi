package tamagotchi.utils;

import tamagotchi.unicorns.Unicorn;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class GameEventSystem {

    private List<GameEvent> events = new ArrayList<>();
    private Random random = new Random();

    public static class GameEvent {
        private String description;
        private Consumer<Unicorn> effect;

        public GameEvent(String description, Consumer<Unicorn> effect) {
            this.description = description;
            this.effect = effect;
        }

        public void apply(Unicorn unicorn) {
            effect.accept(unicorn);
        }

        public String getDescription() { return description; }
    }

    public GameEventSystem () {
        events.add(new GameEvent("Ты простудился!",
                unicorn -> unicorn.setHealth(unicorn.getHealth() - 20)));
        events.add(new GameEvent("Укусила злая собака!",
                unicorn -> unicorn.setHunger(unicorn.getHunger() + 15)));
        events.add(new GameEvent("Выиграл в лотерею!",
                unicorn -> { unicorn.setHappiness(unicorn.getHappiness() + 30);
                    unicorn.setEnergy(unicorn.getEnergy() + 20); }));
    }

    public void triggerRandomEvent(Unicorn unicorn) {
        if (unicorn.isAlive() && random.nextDouble() < 0.8) { // 80% шанс
            GameEvent event = events.get(random.nextInt(events.size()));
            System.out.println("\n⚡ СОБЫТИЕ: " + event.getDescription());
            event.apply(unicorn);
        }
    }
}
