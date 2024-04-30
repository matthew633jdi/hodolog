package com.matthew.hodolog.crawling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.List;

public class CrawlingTest {

    @Test
    @DisplayName("crawling test")
    void getDoc() throws IOException, InterruptedException {
//        Process process = Runtime.getRuntime().exec("xattr -d com.apple.quarantine drivers/chromedriver");
//        process.waitFor();
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");

        ChromeDriver driver = new ChromeDriver();

        String url = "http://bible.godpia.com/read/reading.asp?ver=saenew";

        boolean hasNext = true;

        while (hasNext) {
            driver.get(url);

            String currentUrl = driver.getCurrentUrl();
            if (!currentUrl.contains("reading.asp")) {
                driver.get(url);
            }

            WebElement body = driver.findElement(By.className("body"));
            WebElement header = body.findElements(By.tagName("h3")).get(1);
            System.out.println("header.getText() = " + header.getText());
            
            WebElement wideDiv = driver.findElement(By.className("wide"));
            List<WebElement> pList = wideDiv.findElements(By.tagName("p"));

            for (WebElement p : pList) {
                // StringJoiner ->
//                System.out.println(p.getText());
            }

            try {
                WebElement nextLink = driver.findElement(By.className("nextUrl"));
                url = nextLink.getAttribute("href");
            } catch (Exception e) {
                hasNext = false;
            }
        }
    }
}
