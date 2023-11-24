package hillel.lesson24_files;

import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class FileReadWriteExamples {
    @Test
    public void workWithFile() {
        // -----> Отримання Інформації про Файл
        File file = new File("src/test/resources/newFile.txt");
        // File fileWin = new File("src\\test\\resources\\allTests.xml");
        // Отримання абсолютного шляху
        String absolutePath = file.getAbsolutePath();
        System.out.println(absolutePath);


        // Перевірка існування файлу
        boolean exists = file.exists();

        // Перевірка, чи файл чи директорія
        boolean isFile = file.isFile();
        boolean isDirectory = file.isDirectory();

        // Отримання розміру файлу
        long fileSize = file.length();


        // Отримання імені файлу
        String fileName = file.getName();

        // Отримання останньої дати модифікації
        long lastModified = file.lastModified();
//        System.out.println(exists);
//        System.out.println(isFile);
//        System.out.println(isDirectory);
//        System.out.println(fileSize);
//        System.out.println(lastModified);

//        File directory = new File("src/test/resources/newDir");
//        directory.mkdir();
//
//        File[] files = new File("src/test/resources").listFiles();
//        Arrays.stream(files).map(File::getName).toList().forEach(System.out::println);

        // file.renameTo(new File("src/test/resources/newFile.txt"));

        file.delete();

    }

    @Test
    public void writeFile() {
        // Вкажіть шлях та ім'я файлу для запису
        String filePath = "src/test/resources/outputfile.txt";

        try (
                FileWriter fileWriter = new FileWriter(filePath);
                // FileWriter fileWriter = new FileWriter(filePath,true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            // Записуємо декілька рядків у файл
            bufferedWriter.write("Перший рядок тексту\n");
            //bufferedWriter.newLine(); // Перехід на новий рядок
            bufferedWriter.write("Другий рядок тексту\n");
            //bufferedWriter.newLine();
            bufferedWriter.write("Третій рядок тексту\n");

            // Після закриття BufferedWriter файл буде збережено
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readFile() {
        // Вкажіть шлях до файлу, який потрібно зчитати
        String filePath = "src/test/resources/text.txt";


        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Виведення прочитаної лінії
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readFileNIO() {
        String filePath = "src/test/resources/text.txt";

        try {
            // Читання всіх рядків з файлу
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            // Виведення прочитаних рядків
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void writeFileNIO() {
        String filePath = "src/test/resources/outputfileNIO.txt";
        List<String> lines = Arrays.asList("Перший рядок", "Другий рядок", "Третій рядок");

        try {
            // Запис у файл
            Files.write(Paths.get(filePath), lines, StandardOpenOption.APPEND, StandardOpenOption.CREATE);

            System.out.println("Файл успішно записано!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteFileNIO() {
        Path path = Paths.get("src/test/resources/outputfileNIO.txt");
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
