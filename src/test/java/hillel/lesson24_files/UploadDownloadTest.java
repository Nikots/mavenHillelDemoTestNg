package hillel.lesson24_files;

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

        URL url = getClass().getClassLoader().getResource("text.txt");
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
