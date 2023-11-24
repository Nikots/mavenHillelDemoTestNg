package hillel.lesson24_files;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesSample {
    public static void main(String[] args) {
        File file = new File("src/test/resources/stage.properties");

        Properties properties = new Properties();
        try {
            properties.load(new FileReader(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String baseUrl = properties.getProperty("baseUrl", "dot.com");
        String login = properties.getProperty("login");

        int port = Integer.parseInt(properties.getProperty("port", "80"));

        System.out.println(baseUrl);
        System.out.println(login);
        System.out.println(port);
    }
}
