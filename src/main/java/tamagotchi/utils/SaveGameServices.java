package tamagotchi.utils;

import tamagotchi.unicorns.Unicorn;
import java.io.*;

public class SaveGameServices {

    public static final String SAVE_FILE = "unicorn.bin";

    public static void saveGame (SaveData saveData) throws IOException {
        try {
            FileOutputStream fos = new FileOutputStream(SAVE_FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(saveData);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SaveData loadGame() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_FILE))) {
            return (SaveData) ois.readObject();
        } catch (FileNotFoundException e) {
            return null;
        }
    }

}
