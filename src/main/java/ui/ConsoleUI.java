package ui;
import unicorns.UnicornTextures;

import java.util.Scanner;

public class ConsoleUI {
    private Scanner scanner = new Scanner(System.in);

    public void showMainMenu() {
        System.out.println("========================================");
        System.out.println("  🦄  ДОБРО ПОЖАЛОВАТЬ В МиР ТАМАГОЧи  🦄");
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
        UnicornTextures textures = new UnicornTextures();

        System.out.println("🦄 Поздравляем! Вы создали единорога по имени " + name + "!");
        System.out.println(textures.neutralUnicorn());

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
}
