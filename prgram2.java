import java.util.Arrays;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class prgram2 {
    public static void main(String[] args) {
        int[] arrayToSort = new int[6];
        arrayRandomizer(arrayToSort);
        Logger logger = Logger.getLogger(prgram2.class.getName());

        try {
            FileHandler fh = new FileHandler("./Files/Bubblelog.txt");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Ошибка создания лог-файла", e);
        }

        logger.info("первый массив: " + Arrays.toString(arrayToSort));

        for (int i = 0; i < arrayToSort.length - 1; i++) {
            for (int j = 0; j < arrayToSort.length - i - 1; j++) {
                if (arrayToSort[j] > arrayToSort[j + 1]) {
                    int temp = arrayToSort[j];
                    arrayToSort[j] = arrayToSort[j + 1];
                    arrayToSort[j + 1] = temp;
                }
            }
            logger.info("итерация " + (i + 1) + ": " + Arrays.toString(arrayToSort));
        }

        logger.info("после итерации массив: " + Arrays.toString(arrayToSort));
    }

    private static void arrayRandomizer(int[] array) {
        Random rnd = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = rnd.nextInt(0, 10);
        }
    }
}