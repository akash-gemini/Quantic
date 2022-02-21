package com.gemini.automation.generic;

import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Locale;

public class DriverManager {
    private static  ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    public static void setWebDriver(WebDriver webDriver){driver.set(webDriver);}
    public static WebDriver getWebDriver(){return driver.get();}
    public static void closeDriver(){
        try{
            driver.get().close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void initializeBrowser(String browserName){
        String browser = browserName.trim().toLowerCase();
        switch (browser){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                setWebDriver(new ChromeDriver());
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                setWebDriver(new FirefoxDriver());
                break;
            default:
                break;
        }
    }
    public static void initiizeChrome(ChromeOptions chromeOptions){setWebDriver(new ChromeDriver(chromeOptions));}

}
