package tamagotchi.ui;
import tamagotchi.unicorns.Unicorn;
import tamagotchi.utils.GameTextures;
import tamagotchi.utils.UnicornTextures;

import java.util.Scanner;

public class ConsoleUI {
    private Scanner scanner = new Scanner(System.in);
    UnicornTextures textures = new UnicornTextures();
    GameTextures gameTextures = new GameTextures();
    private int turnCounter = 0;

    public void showMainMenu() {
        System.out.println("========================================");
        System.out.println("  🦄  ДОБРО ПОЖАЛОВАТЬ В TAMAGOCHI - UNICORN  🦄");
        System.out.println("========================================");
        System.out.println("          Ваш питомец - Единорог!        ");
        System.out.println("========================================");

        while (true) {
            System.out.println("\nГЛАВНОЕ МЕНЮ:");
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
//                    loadGame();
                    System.out.println("  Типо загрузился");
                    break;
                case 3:
                    System.out.println("🦄 До свидания! Пусть радуга освещает ваш путь!");
                    scanner.close();
                    System.exit(0);
                    break;

            }
        }
    }

    private void createNewUnicorn() {
        System.out.print("\nВведите имя для вашего единорога: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            name = "Без имени";
        }

        Unicorn unicorn = new Unicorn(name);

        System.out.println("🦄 Поздравляем! Вы создали единорога по имени " + name + "!");
        System.out.println(textures.neutralUnicorn());
        startGameLoop(unicorn);
    }

    private void startGameLoop(Unicorn unicorn) {
        turnCounter = 0;
        System.out.println("\n=== Начинаем! Цель: продержаться 10 ходов! ===");

        while (unicorn.isAlive()) {
            turnCounter++;
            System.out.println("\n--- Ход " + turnCounter + " ---");
            unicorn.showStatus();

            System.out.println("\nЧто делаем?");
            System.out.println("  1. Покормить");
            System.out.println("  2. Поиграть");
            System.out.println("  3. Уложить спать");
            System.out.println("  4. Пропустить ход");
            System.out.println("  5. Сохранить и выйти в меню");
            System.out.print("Ваш выбор: ");

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
                    System.out.println("⏳ Время идёт...");
                    break;
                case 5:
//                    try {
//                        SaveService.saveGame(unicorn);
//                    } catch (Exception e) {
//                        System.out.println("❌ Ошибка сохранения: " + e.getMessage());
//                    }
//                    System.out.println("Возвращаемся в главное меню.");
                    return;
                default:
                    System.out.println("❌ Неверный выбор, попробуйте снова.");
                    continue;
            }

            unicorn.tick();

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
