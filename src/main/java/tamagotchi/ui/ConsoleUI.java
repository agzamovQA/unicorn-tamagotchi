package tamagotchi.ui;
import tamagotchi.unicorns.Unicorn;
import tamagotchi.utils.*;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ConsoleUI {
    private Scanner scanner = new Scanner(System.in);
    UnicornTextures textures = new UnicornTextures();
    GameTextures gameTextures = new GameTextures();
    GameEventSystem randomEvent = new GameEventSystem();
    private int turnCounter;
    private Unicorn unicorn;

    public void showMainMenu() throws InterruptedException {

        System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
        System.out.println("  🦄  ДОБРО ПОЖАЛОВАТЬ В TAMAGOCHI - UNICORN  🦄");
        System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
        System.out.println("            Ваш питомец - Единорог!          ");
        System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");

        while (true) {
            System.out.println("\nГЛАВНОЕ МЕНЮ:\n");
            System.out.println("  1. Создать нового единорога");
            System.out.println("  2. Загрузить сохранённую игру");
            System.out.println("  3. Выйти");
            System.out.print("\nВаш выбор: ");

            var choice = readInt(1, 3);

            switch (choice) {
                case 1:
                    createNewUnicorn();
                    break;
                case 2:
                    loadGame();
                    break;
                case 3:
                    System.out.println("\n🦄 До свидания! Пусть радуга освещает ваш путь!");
                    scanner.close();
                    System.exit(0);
                    break;

            }
        }
    }

    private void createNewUnicorn() throws InterruptedException {
        System.out.print("\nВведите имя для вашего единорога: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            name = "Без имени";
        }

        Unicorn unicorn = new Unicorn(name);
        this.turnCounter = 0;

        System.out.println("\n🦄 Поздравляем! Вы создали единорога по имени " + name + "!");
        System.out.println(textures.neutralUnicorn());
        TimeUnit.SECONDS.sleep(3);
        startGameLoop(unicorn);
    }

    private void startGameLoop(Unicorn unicorn) {
        System.out.println("\n=== Начинаем! Цель: продержаться 10 ходов! ===");

        while (unicorn.isAlive()) {
            turnCounter++;
            System.out.println("\n--- Ход " + turnCounter + " ---");
            unicorn.showStatus();

            System.out.println("\nЧто делаем?");
            System.out.println("  1. Покормить");
            System.out.println("  2. Поиграть");
            System.out.println("  3. Уложить спать");
            System.out.println("  4. Сделать 'Пердь'");
            System.out.println("  5. Сохранить и выйти в меню");
            System.out.print("\nВаш выбор: ");

            int choice = readInt(1, 5);

            switch (choice) {
                case 1:
                    unicorn.feed();
                    break;
                case 2:
                    unicorn.play();
                    break;
                case 3:
                    unicorn.sleep();
                    break;
                case 4:
                    System.out.println(gameTextures.fart());
                    break;
                case 5:
                    SaveData saveData = new SaveData(unicorn, turnCounter);
                    try {
                        SaveGameServices.saveGame(saveData);
                    } catch (Exception e) {
                        System.out.println(" Ошибка сохранения: " + e.getMessage());
                    }
                    System.out.println("Возвращаемся в главное меню.");
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
                    continue;
            }

            unicorn.tick();
            randomEvent.triggerRandomEvent(unicorn);

            var finalGame = turnCounter == 10;

            if (finalGame) {
                clearConsole();
                System.out.println("🦄 Поздравляем! Вы прошли игру!");
                System.out.println(gameTextures.endGame());
                System.out.println("🦄 До свидания! Пусть радуга освещает ваш путь!");
                scanner.close();
                System.exit(0);
                break;
            }

            if (!unicorn.isAlive()) {
                System.out.println("\n💀 Ваш единорог умер...");
                System.out.println("Всего прожито ходов: " + turnCounter);
                System.out.println("Возвращаемся в главное меню.");

                return;
            }
        }
    }

    private void loadGame() {
        try {
            SaveData loadedData = SaveGameServices.loadGame();

            if (loadedData == null) {
                System.out.println(" Сохранение не найдено. Создайте нового единорога.");
                return;
            }

            Unicorn loadedUnicorn = loadedData.getUnicorn();
            int loadedTurnCounter = loadedData.getTurnCounter();

            this.unicorn = loadedUnicorn;
            this.turnCounter = loadedTurnCounter;

            System.out.println("📂 Загрузка успешна! Ваш единорог " + loadedUnicorn.getName() + " ждёт вас.");
            startGameLoop(loadedUnicorn);

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("❌ Ошибка загрузки: " + e.getMessage());
        }
    }

    private int readInt(int min, int max) {
        while (true) {
            try {
                var value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.print("Введите число от " + min + " до " + max + ": ");
            } catch (NumberFormatException e) {
                System.out.print("Ошибка! Введите целое число: ");
            }
        }
    }

    private void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
