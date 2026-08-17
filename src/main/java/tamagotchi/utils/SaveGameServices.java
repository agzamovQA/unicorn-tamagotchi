package tamagotchi.utils;

import tamagotchi.unicorns.Unicorn;
import java.io.*;

public class SaveGameServices {

    public static final String SAVE_FILE = "unicorn.bin";

    public static void saveGame (Unicorn unicorn) throws IOException {
        try {
            FileOutputStream fos = new FileOutputStream(SAVE_FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(unicorn);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Unicorn loadGame () {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_FILE))) {
            Unicorn unicorn = (Unicorn) ois.readObject();
            return unicorn;
        }
        catch (IOException | ClassNotFoundException e) {
            System.out.println("Сохранение не найдено!");
        }
        return null;
    }

}
