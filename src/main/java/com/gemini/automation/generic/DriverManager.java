package com.gemini.automation.generic;

import com.qa.gemini.quartzReporting.GemTestReporter2;
import com.qa.gemini.quartzReporting.STATUS;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Locale;

public class DriverManager {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    public static void setWebDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }

    public static WebDriver getWebDriver() {
        return driver.get();
    }

    public static void closeDriver() {
        try {
            GemTestReporter2.addTestStep("Close Driver", "Driver Close Successful", STATUS.PASS);
            driver.get().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void initializeBrowser() {
        GemTestReporter2.addTestStep("initialize Browser", "No browser provided <BR> set to default browser CHROME", STATUS.PASS);

        System.out.println("No browser provided /n set to default browser CHROME");
        initializeBrowser("Chrome");
    }

    public static void initializeBrowser(String browserName) {
        try {
            GemTestReporter2.addTestStep("initialize Browser", "Browser set to ~ "+browserName, STATUS.PASS);
            if (browserName == null) {
                System.out.println("No browser provided /n set to default browser CHROME");
                WebDriverManager.chromedriver().setup();
                setWebDriver(new ChromeDriver());
            } else {
                String browser = browserName.trim().toLowerCase();
                switch (browser) {
                    case "chrome":
                        initializeChrome();
                        break;
                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        setWebDriver(new FirefoxDriver());
                        break;
                    default:
                        WebDriverManager.chromedriver().setup();
                        setWebDriver(new ChromeDriver());
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void initializeChrome(ChromeOptions chromeOptions) {
        setWebDriver(new ChromeDriver(chromeOptions));
    }

    public static void initializeChrome() {
        WebDriverManager.chromedriver().setup();
        setWebDriver(new ChromeDriver());
    }


}
