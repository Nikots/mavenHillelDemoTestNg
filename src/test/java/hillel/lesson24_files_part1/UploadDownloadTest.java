package hillel.lesson24_files_part1;

import hillel.utils.BaseUiTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

public class UploadDownloadTest extends BaseUiTest {

    @Test
    public void uploadFileTest() {
        driver.get("https://smallseotools.com/plagiarism-checker/");

        URL url = getClass().getClassLoader().getResource("random/text.txt");
        if (url == null) throw new IllegalArgumentException("file not found");
        File file;
        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        WebElement input = driver.findElement(By.id("fileUpload"));
        input.sendKeys(file.getAbsolutePath());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.sst_loader")));
        wait.until(ExpectedConditions.attributeToBe(By.id("loader_con11"), "class", "d-none"));
    }

    @Test
    public void uploadFileTest2() throws URISyntaxException {
        String filename = "random/text.txt";
        driver.get("https://the-internet.herokuapp.com/upload");

        WebElement fileInput = driver.findElement(By.id("file-upload"));

        // Отримання URL Ресурсу:
        URL resourceUrl = getClass().getClassLoader().getResource(filename);
        if (resourceUrl == null) {
            throw new IllegalArgumentException("Файл не знайдено");
        }
        //Перетворення URL в Об'єкт File:
        File file = new File(resourceUrl.toURI());
        // Отримання Абсолютного Шляху
        fileInput.sendKeys(file.getAbsolutePath());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeContains(fileInput,"value", filename));

        WebElement uploadBtn = driver.findElement(By.id("file-submit"));
        uploadBtn.submit();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='File Uploaded!']")));
        System.out.println("done");
    }

    @Test
    public void downloadFileTest() {
        driver.get("https://chromedriver.storage.googleapis.com/index.html?path=114.0.5735.16/");

        String fileName = "chromedriver_mac64.zip";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement chromeDriverFile =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(fileName)));
        chromeDriverFile.click();

        Assert.assertTrue(waitFileDownloaded(DOWNLOAD_FOLDER_PATH, fileName, 10));

        Assert.assertTrue(hasCorrectExtension(DOWNLOAD_FOLDER_PATH + "/" + fileName, ".zip"));
    }

    public boolean hasCorrectExtension(String filePath, String extension) {
        File file = new File(filePath);
        String fileName = file.getName();
        return fileName.endsWith(extension);
    }

    public boolean waitFileDownloaded(String directory, String fileName, int timeoutInSec) {
        for (int i = 0; i < timeoutInSec; i++) {
            File file = new File(directory + "/" + fileName);
            if (file.exists() & file.length() > 0) return true;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }


}
