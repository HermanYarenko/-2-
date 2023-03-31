import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class program1{
    private static final Logger LOGGER = Logger.getLogger(program1.class.getName());

    public static void main(String[] args) {
        try {
            FileHandler fileHandler = new FileHandler("./Files/log.txt");
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
            String json = readFile("./Files/students.json");
            String result = parseFile(json);
            System.out.println(result);
            writeResultToFile("./Files/result.txt", result);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error creating file handler", e);
        }
    }

    private static String readFile(String fileName) {
        try {
            FileReader reader = new FileReader(fileName);
            StringBuilder stringBuilder = new StringBuilder();
            int character;
            while ((character = reader.read()) != -1) {
                stringBuilder.append((char) character);
            }
            reader.close();
            return stringBuilder.toString();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading file: " + fileName, e);
            throw new NullPointerException("Ошибка чтения файла");
            
        }

    }

    private static String parseFile(String json) {
        StringBuilder result = new StringBuilder();
        json = json.replaceAll("[\\[\\]{}\"\\s+]", "");
        String[] resultStringArray = json.split("[,:]");

        for (int i = 5; i < resultStringArray.length; i+=6) {
            result.append("Студент ").append(resultStringArray[i-4]).append(" получил ")
                    .append(resultStringArray[i-2]).append(" по предмету ").append(resultStringArray[i])
                    .append("\n");
        }
        return result.toString();
    }

    private static void writeResultToFile(String fileName, String result) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(result);
            System.out.printf("Entry saved to %s", fileName);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error writing to file: " + fileName, e);
            throw new NullPointerException("Ошибка записи в файл");
        }
    }
}