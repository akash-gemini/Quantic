package com.gemini.automation.generic;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.qa.gemini.quartzReporting.GemTestReporter;
import com.qa.gemini.quartzReporting.GemTestReporter;
import com.qa.gemini.quartzReporting.STATUS;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;


public class DriverAction {

    private static final String PASS = "Pass";
    private static final String FAIL = "Fail";

    public static void launchUrl(String url,boolean report) {

        try {
            DriverManager.getWebDriver().get(url);
            if(report) {
                GemTestReporter.addTestStep("Launch Url", "Url ~" + url, STATUS.PASS);
            }
        } catch (Exception e) {
            if(report) {
                GemTestReporter.addTestStep("Launch Url", "Launch Url Failed <BR>Url ~" + url, STATUS.FAIL);
            }
            e.printStackTrace();
        }
    }

    public static void launchUrl(String url) {
        launchUrl(url,false);
    }



    public static String getTitle(String url) {
        try {
            String title = DriverManager.getWebDriver().getTitle();
            GemTestReporter.addTestStep("Get Title", "Title ~" + title, STATUS.PASS);
            return title;
        } catch (Exception e) {
            GemTestReporter.addTestStep("Get Title", "Get Title Failed", STATUS.FAIL);

            e.printStackTrace();
            return null;
        }
    }

    public static String getTitleWithoutReporting(String url) {
        try {
            String title = DriverManager.getWebDriver().getTitle();
            return title;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void maximizeBrowser() {
        try {
            GemTestReporter.addTestStep("Maximize Browser", "Browser Maximize Successful ", STATUS.PASS);
            DriverManager.getWebDriver().manage().window().maximize();
        } catch (Exception e) {
            GemTestReporter.addTestStep("Maximize Browser", "Browser Maximize Failed", STATUS.FAIL);
            e.printStackTrace();
        }
    }

    public static void maximizeBrowserWithoutReporting() {
        try {
            DriverManager.getWebDriver().manage().window().maximize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void minimizeBrowser() {
        try {
            GemTestReporter.addTestStep("Minimize Browser", "Browser Minimize Successful", STATUS.PASS);
            DriverManager.getWebDriver().manage().window().minimize();
        } catch (Exception e) {
            GemTestReporter.addTestStep("Minimize Browser", "Browser Minimize Failed", STATUS.FAIL);
            e.printStackTrace();
        }
    }

    public static void minimizeBrowserWithoutReporting() {
        try {
            DriverManager.getWebDriver().manage().window().minimize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Dimension getBrowserSize() {
        try {
            return DriverManager.getWebDriver().manage().window().getSize();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void setBrowserSize(int width, int height) {
        try {
            GemTestReporter.addTestStep("Set Browser Size", "Browser Size Set To <BR> width ~ " + width + "<BR> height ~ " + height, STATUS.PASS);
            Dimension dimension = new Dimension(width, height);
            DriverManager.getWebDriver().manage().window().setSize(dimension);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Set Browser Size", "Set Browser Size Failed", STATUS.FAIL);
            e.printStackTrace();
        }
    }

    public static void setBrowserSizeWithoutReporting(int width, int height) {
        try {
            Dimension dimension = new Dimension(width, height);
            DriverManager.getWebDriver().manage().window().setSize(dimension);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setBrowserPosition(int x, int y) {
        try {
            GemTestReporter.addTestStep("Set Browser Position", "Browser Position Set To <BR> x ~ " + x + "<BR> y ~ " + y, STATUS.PASS);
            Point point = new Point(x, y);
            DriverManager.getWebDriver().manage().window().setPosition(point);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Set Browser Position", "Set Browser Position Failed",STATUS.FAIL);
            e.printStackTrace();
        }
    }

    public static void setBrowserPositionWithoutReporting(int x, int y) {
        try {
            Point point = new Point(x, y);
            DriverManager.getWebDriver().manage().window().setPosition(point);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Point getBrowserLocation() {//change return
        try {
            Point p = DriverManager.getWebDriver().manage().window().getPosition();
            return p;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    //////////TimeOuts///////////

    public static void waitSec(long seconds) {
        try {
            Thread.sleep(seconds*1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setImplicitTimeOut(long seconds) {
        try {
            DriverManager.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setScriptTimeOut(long seconds) {
        try {
            DriverManager.getWebDriver().manage().timeouts().scriptTimeout(Duration.ofSeconds(seconds));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setPageLoadTimeOut(long seconds) {
        try {
            DriverManager.getWebDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(seconds));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ///Navigation

    public static void navigateBack() {
        try {
            GemTestReporter.addTestStep("Navigate Back", "Navigate Back Successful", STATUS.PASS);
            DriverManager.getWebDriver().navigate().back();
        } catch (Exception e) {
            GemTestReporter.addTestStep("Navigate Back", "Navigate Back Failed", STATUS.FAIL);

            e.printStackTrace();
        }
    }

    public static void navigateBackWithoutReporting() {
        try {
            DriverManager.getWebDriver().navigate().back();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void navigateRefresh() {
        try {
            GemTestReporter.addTestStep("Refresh Page", "Page Refresh Successful", STATUS.PASS);
            DriverManager.getWebDriver().navigate().refresh();
        } catch (Exception e) {
            GemTestReporter.addTestStep("Refresh Page", "Page Refresh Failed", STATUS.FAIL);

            e.printStackTrace();
        }
    }

    public static void navigateRefreshWithoutReporting() {
        try {
            DriverManager.getWebDriver().navigate().refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void navigateForward() {
        try {
            GemTestReporter.addTestStep("Navigate Forward", "Forward Navigation Successful ", STATUS.PASS);
            DriverManager.getWebDriver().navigate().forward();
        } catch (Exception e) {
            GemTestReporter.addTestStep("Navigate Forward", "Forward Navigation Failed ", STATUS.FAIL);

            e.printStackTrace();
        }
    }

    public static void navigateForwardWithoutReporting() {
        try {
            DriverManager.getWebDriver().navigate().forward();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void navigateToUrl(String url) {
        try {
            GemTestReporter.addTestStep("Navigate to Url","Navigation to Url Successful<BR>URL ~ "+url,STATUS.PASS);
            DriverManager.getWebDriver().navigate().to(url);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Navigate to Url","Navigation to Url Failed<BR>URL ~ "+url,STATUS.FAIL);

            e.printStackTrace();
        }
    }

    public static void navigateToUrlWithoutReporting(String url) {
        try {
            DriverManager.getWebDriver().navigate().to(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    ///WebElement
    public static WebElement getElement(By locator) {
        try {
            WebElement element = DriverManager.getWebDriver().findElement(locator);
            return element;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //return set<String>
    public static String getWindowHandle() {
        try {
            return DriverManager.getWebDriver().getWindowHandle();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getCurrentURL() {
        try {
            return DriverManager.getWebDriver().getCurrentUrl();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getPageSource() {
        try {
            return DriverManager.getWebDriver().getPageSource();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    ///////////Switch Operations/////////////////////
    public static void switchToWindow(String nameOfHandle) {
        try {
            DriverManager.getWebDriver().switchTo().window(nameOfHandle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void switchToFrame(int index) {
        try {
            DriverManager.getWebDriver().switchTo().frame(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void switchToFrame(String nameOrId) {
        try {
            DriverManager.getWebDriver().switchTo().frame(nameOrId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void switchToFrame(WebElement frameElement) {
        try {
            DriverManager.getWebDriver().switchTo().frame(frameElement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void switchToParentFrame() {
        try {
            DriverManager.getWebDriver().switchTo().parentFrame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void switchToDefaultContent() {
        try {
            DriverManager.getWebDriver().switchTo().defaultContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void switchToActiveElement() {
        try {
            DriverManager.getWebDriver().switchTo().activeElement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void switchToAlert() {
        try {
            DriverManager.getWebDriver().switchTo().alert();
            GemTestReporter.addTestStep("Switch To Alert","Switch To Alert Successful",STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Switch To Alert","Switch To Alert Failed",STATUS.FAIL);

            e.printStackTrace();
        }
    }

    public static void AcceptAlert() {
        try {
            DriverManager.getWebDriver().switchTo().alert().accept();
            GemTestReporter.addTestStep("Accept Alert","Alert Accepted Successful",STATUS.PASS);

        } catch (Exception e) {
            GemTestReporter.addTestStep("Accept Alert","Alert Accepted Failed",STATUS.FAIL);

            e.printStackTrace();
        }
    }

    public static void DismissAlert() {
        try {
            DriverManager.getWebDriver().switchTo().alert().dismiss();
            GemTestReporter.addTestStep("Dismiss Alert","Alert Dismissed Successful",STATUS.PASS);

        } catch (Exception e) {
            GemTestReporter.addTestStep("Dismiss Alert","Alert Dismissed Failed",STATUS.FAIL);

            e.printStackTrace();
        }
    }

    public static void AlertInput(String input) {
        try {
            DriverManager.getWebDriver().switchTo().alert().sendKeys(input);
            GemTestReporter.addTestStep("SendKeys To Alert","SendKeys To Alert Successful <BR> input ~ "+input,STATUS.PASS);

        } catch (Exception e) {
            GemTestReporter.addTestStep("SendKeys To Alert","SendKeys To Alert Failed <BR> input ~ "+input,STATUS.FAIL);

            e.printStackTrace();
        }
    }

    //////////////Web Elements///////////////////

    public static List<WebElement> getElements(By locator) {
        try {
            List<WebElement> elements = DriverManager.getWebDriver().findElements(locator);
            return elements;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getElementText(By locator) {
        try {
            WebElement element = getElement(locator);
            String elementText = element.getText();
            return elementText;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getElementText(WebElement element) {
        try {
            String elementText = element.getText();
            return elementText;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<String> getElementsText(By locator) {
        try {
            List<WebElement> elements = getElements(locator);
            List<String> elementsText = new ArrayList<String>();
            for (WebElement element : elements) {
                elementsText.add(getElementText(element));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    //////////////////Click Operation/////////////////

    public static void click(By locator, String elementLabel) {
        try {
            WebElement element = getElement(locator);
            element.click();
            GemTestReporter.addTestStep("Click on ","Click Successful on "+elementLabel,STATUS.PASS);
        } catch (Exception e) {
            e.printStackTrace();
            GemTestReporter.addTestStep("Click on ","Click Failed on "+elementLabel,STATUS.FAIL);
        }
    }

    public static void click(By locator, String steps, String description) {
        try {
            WebElement element = getElement(locator);
            element.click();
            GemTestReporter.addTestStep(steps,description,STATUS.PASS);

        } catch (Exception e) {
            e.printStackTrace();
            GemTestReporter.addTestStep(steps,description,STATUS.FAIL);
            System.out.println("Failed to Click ");
            System.out.println("Steps : " + steps);
            System.out.println("Description : " + description);
        }
    }

    public static void click(WebElement webElement, String steps, String description) {
        try {
            webElement.click();
            GemTestReporter.addTestStep(steps,description,STATUS.PASS);
            System.out.println("Successful to Click on web element : " + webElement);
            System.out.println("Steps : " + steps);
            System.out.println("Description : " + description);
        } catch (Exception e) {
            e.printStackTrace();
            GemTestReporter.addTestStep(steps,description,STATUS.FAIL);
            System.out.println("Failed to Click on web element : " + webElement);
            System.out.println("Steps : " + steps);
            System.out.println("Description : " + description);
        }
    }

    public static void click(WebElement webElement, String elementLabel) {
        try {
            webElement.click();
            GemTestReporter.addTestStep("Click on ","Click Successful on "+elementLabel,STATUS.PASS);
            System.out.println("Successful to Click on web element : " + webElement);
            System.out.println("element label : " + elementLabel);
        } catch (Exception e) {
            e.printStackTrace();
            GemTestReporter.addTestStep("Click on ","Click Failed on "+elementLabel,STATUS.FAIL);

            System.out.println("Failed to Click on web element : " + webElement);
            System.out.println("element label : " + elementLabel);
        }
    }

    public static void clickWithoutReporting(By locator) {
        try {
            WebElement element = getElement(locator);
            element.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clickWithoutReporting(WebElement webElement) {
        try {
            webElement.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /////////////////Type Operation//////////////////////

    public static void typeText(By locator, String textToEnter, String steps, String description) {
        try {
            WebElement element = getElement(locator);
            element.clear();
            element.sendKeys(textToEnter);
            GemTestReporter.addTestStep(steps,description,STATUS.PASS);
            System.out.println("TypeText Successful ");
            System.out.println("Steps : " + steps);
            System.out.println("Description : " + description);
        } catch (Exception e) {
            e.printStackTrace();
            GemTestReporter.addTestStep(steps,description,STATUS.FAIL);

            System.out.println("TypeText Failed");
            System.out.println("Steps : " + steps);
            System.out.println("Description : " + description);
        }
    }

    public static void typeTextWithoutReporting(By locator, String textToEnter) {
        try {
            WebElement element = getElement(locator);
            element.clear();
            element.sendKeys(textToEnter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void typeText(By locator, String textToEnter, String elementLabel) {
        try {
            WebElement element = getElement(locator);
            element.clear();
            element.sendKeys(textToEnter);
            GemTestReporter.addTestStep("Type on "+elementLabel,"Type Text Successful<BR>Type Text ~"+textToEnter,STATUS.PASS);
            System.out.println("TypeText Successful ");
            System.out.println("Text to Enter : " + textToEnter);
            System.out.println("element Label : " + elementLabel);
        } catch (Exception e) {
            e.printStackTrace();
            GemTestReporter.addTestStep("Type on "+elementLabel,"Type Text Failed<BR>Type Text ~"+textToEnter,STATUS.FAIL);

            System.out.println("TypeText Failed");
            System.out.println("Text to Enter : " + textToEnter);
            System.out.println("element Label : " + elementLabel);
        }
    }

    public static void typeText(WebElement element, String textToEnter, String steps, String description) {
        try {
            element.clear();
            element.sendKeys(textToEnter);
            GemTestReporter.addTestStep(steps,description,STATUS.PASS);
            System.out.println("TypeText Successful ");
            System.out.println("Steps : " + steps);
            System.out.println("Description : " + description);
        } catch (Exception e) {
            e.printStackTrace();
            GemTestReporter.addTestStep(steps,description,STATUS.FAIL);
            System.out.println("TypeText Failed");
            System.out.println("Steps : " + steps);
            System.out.println("Description : " + description);
        }
    }

    public static void typeTextWithoutReporting(WebElement element, String textToEnter) {
        try {
            element.clear();
            element.sendKeys(textToEnter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void typeText(WebElement element, String textToEnter, String elementLabel) {
        try {
            element.clear();
            element.sendKeys(textToEnter);
            GemTestReporter.addTestStep("Type on "+elementLabel,"Type Text Successful<BR>Type Text ~"+textToEnter,STATUS.PASS);
            System.out.println("TypeText Successful ");
            System.out.println("Text to Enter : " + textToEnter);
            System.out.println("element Label : " + elementLabel);
        } catch (Exception e) {
            e.printStackTrace();
            GemTestReporter.addTestStep("Type on "+elementLabel,"Type Text Failed<BR>Type Text ~"+textToEnter,STATUS.FAIL);
            System.out.println("TypeText Failed");
            System.out.println("Text to Enter : " + textToEnter);
            System.out.println("element Label : " + elementLabel);
        }
    }

    //////////////Accessible name////////////////////

    public static String getAccessibleName(WebElement webElement) {
        try {
            return webElement.getAccessibleName();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getAccessibleName(By locator) {
        try {
            WebElement element = getElement(locator);
            return element.getAccessibleName();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getArialRole(By locator) {
        try {
            WebElement element = getElement(locator);
            return element.getAriaRole();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getArialRole(WebElement element) {
        try {
            return element.getAriaRole();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getAttributeName(WebElement webElement, String name) {
        try {
            return webElement.getAttribute(name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getCSSValue(By locator, String propertyName) {
        try {
            WebElement element = getElement(locator);
            return element.getCssValue(propertyName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getCSSValue(WebElement element, String propertyName) {
        try {
            return element.getCssValue(propertyName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /////////////////////////////////////////////

    public static String takeSnapShot() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String fileWithPath = "SS/SS" + timestamp.getTime() + ".png";
        WebDriver webdriver = DriverManager.getWebDriver();
        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(fileWithPath);
        try {
            FileUtils.copyFile(SrcFile, DestFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileWithPath;
    }

}
