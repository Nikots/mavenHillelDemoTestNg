package hillel.lesson25_files_part2;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ReadFileTest {
    private final String filePath = "src/test/resources/username.csv";

    @Test
    public void testCsvHeaders() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String firstLine = reader.readLine(); //"Username; Identifier;First name;Last name"
            List<String> headers = Arrays.asList(firstLine.split(";"));
            List<String> expectedHeaders = Arrays.asList("Username", " Identifier", "First name", "Last name");
            Assert.assertEquals(headers, expectedHeaders, "CSV заголовки не відповідають очікуваним.");
        }
    }

    @Test
    public void testCsvContainsSpecificData() throws IOException {
        boolean dataFound = false;
        String expectedRow1 = "booker12;9012;Rachel;Booker";
       // String expectedRow2 = "grey07;2070;Laura;Grey";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals(expectedRow1)) {
                    dataFound = true;
                    break;
                }
            }
        }
        Assert.assertTrue(dataFound, "Не знайдено специфічних даних у CSV-файлі.");
    }

    @Test
    public void testCsvFileContentNio() throws IOException {
        List<String> rows = Files.readAllLines(Paths.get(filePath));

        Assert.assertFalse(rows.isEmpty(), "File is empty");

        List<String> headers = Arrays.asList(rows.get(0).split(";"));
        List<String> expectedHeaders = Arrays.asList("Username", " Identifier", "First name", "Last name");
        Assert.assertEquals(headers, expectedHeaders, "CSV заголовки не відповідають очікуваним.");

        String expectedRow1 = "booker12;9012;Rachel;Booker";
        String expectedRow2 = "grey07;2070;Laura;Grey";

        Assert.assertTrue(rows.contains(expectedRow1));
        Assert.assertTrue(rows.contains(expectedRow2));
    }

    @Test
    public void testCsvFileContentWithPOJO() throws IOException {
        List<String> rows = Files.readAllLines(Paths.get(filePath));

        User user1 = new User("booker12", "9012", "Rachel", "Booker");

        List<User> users = rows.stream()
                .skip(1)
                .map(row -> row.split(";"))
                .filter(data -> data.length > 3)
                .map(data -> new User(data[0], data[1], data[2], data[3]))
                .toList();

        Assert.assertTrue(users.contains(user1));
    }

    @Test
    public void testContentByBytes() throws IOException {
        String image1 = "src/test/resources/img1.jpeg";
        String image2 = "src/test/resources/img2.jpeg";

        byte[] file1 = Files.readAllBytes(Paths.get(image1));
        byte[] file2 = Files.readAllBytes(Paths.get(image2));

        Assert.assertEquals(file1, file2);
    }
}
