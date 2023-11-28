package hillel.lesson25_files_part2;

import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class WriteFileTest {

    @Test
    public void testWriteToCsv() {
        String filePath = "src/test/resources/username_w1.csv";

        String[] lines = {"Username; Identifier;First name;Last name",
                "booker12;9012;Rachel;Booker",
                "grey07;2070;Laura;Grey"};

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                bufferedWriter.append(line);
                bufferedWriter.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testWriteToCsvNio() throws IOException {
        String filePath = "src/test/resources/username_w2.csv";

        List<String> lines = Arrays.asList("Username; Identifier;First name;Last name",
                "booker12;9012;Rachel;Booker",
                "grey07;2070;Laura;Grey");

        Files.write(Paths.get(filePath), lines, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    @Test
    public void testWriteToCsvPOJO() throws IOException {
        String filePath = "src/test/resources/username_w3.csv";
        User user = new User("booker12", "9012", "Rachel", "Booker");
        Files.write(Paths.get(filePath), user.toString().getBytes(), StandardOpenOption.CREATE);
    }

}
