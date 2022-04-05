package com.gemini.automation.generic;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.qa.gemini.quartzReporting.GemTestReporter;
import com.qa.gemini.quartzReporting.GemTestReporter;
import com.qa.gemini.quartzReporting.STATUS;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;


public class DriverAction {


    public static void launchUrl(String url, boolean report) {
        try {
            DriverManager.getWebDriver().get(url);
            if (report) {
                GemTestReporter.addTestStep("Launch Url", "Url ~" + url, STATUS.PASS);
            }
        } catch (Exception e) {
            if (report) {
                GemTestReporter.addTestStep("Launch Url", "Launch Url Failed <BR>Url ~" + url, STATUS.FAIL);
            }
            e.printStackTrace();
        }
    }

    public static void launchUrl(String url) {
        launchUrl(url, false);
    }


    public static String getTitle(String url, Boolean report) {
        try {
            String title = DriverManager.getWebDriver().getTitle();
            if (report) {
                GemTestReporter.addTestStep("Get Title", "Title ~" + title, STATUS.PASS);
            }
            return title;
        } catch (Exception e) {
            GemTestReporter.addTestStep("Get Title", "Get Title Failed", STATUS.FAIL);
            e.printStackTrace();
            return null;
        }
    }

    public static String getTitle(String url) {
        return getTitle(url, false);
    }

    public static void maximizeBrowser(Boolean report) {
        try {
            DriverManager.getWebDriver().manage().window().maximize();
            if (report) {
                GemTestReporter.addTestStep("Maximize Browser", "Browser Maximize Successful ", STATUS.PASS);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Maximize Browser", "Browser Maximize Failed", STATUS.FAIL);
            e.printStackTrace();
        }
    }

    public static void maximizeBrowser() {
        try {
            maximizeBrowser(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void minimizeBrowser(Boolean report) {
        try {
            DriverManager.getWebDriver().manage().window().minimize();
            if (report) {
                GemTestReporter.addTestStep("Minimize Browser", "Browser Minimize Successful", STATUS.PASS);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Minimize Browser", "Browser Minimize Failed", STATUS.FAIL);
            e.printStackTrace();
        }
    }

    public static void minimizeBrowser() {
        try {
            minimizeBrowser(false);
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


    public static void setBrowserSize(int width, int height, Boolean report) {
        try {
            Dimension dimension = new Dimension(width, height);
            DriverManager.getWebDriver().manage().window().setSize(dimension);
            if (report) {
                GemTestReporter.addTestStep("Set Browser Size", "Browser Size Set To <BR> width ~ " + width + "<BR> height ~ " + height, STATUS.PASS);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Set Browser Size", "Set Browser Size Failed", STATUS.FAIL);
            e.printStackTrace();
        }
    }

    public static void setBrowserSize(int width, int height) {
        try {
            setBrowserSize(width, height, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setBrowserPosition(int x, int y, Boolean report) {
        try {
            Point point = new Point(x, y);
            DriverManager.getWebDriver().manage().window().setPosition(point);
            if (report) {
                GemTestReporter.addTestStep("Set Browser Position", "Browser Position Set To <BR> x ~ " + x + "<BR> y ~ " + y, STATUS.PASS);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Set Browser Position", "Set Browser Position Failed", STATUS.FAIL);
            e.printStackTrace();
        }
    }

    public static void setBrowserPosition(int x, int y) {
        try {
            setBrowserSize(x, y, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Point getBrowserLocation() {
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
            Thread.sleep(seconds * 1000);
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

    public static void navigateBack(Boolean report) {
        try {
            DriverManager.getWebDriver().navigate().back();
            if (report) {
                GemTestReporter.addTestStep("Navigate Back", "Navigate Back Successful", STATUS.PASS);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Navigate Back", "Navigate Back Failed", STATUS.FAIL);
            e.printStackTrace();
        }
    }

    public static void navigateBack() {
        try {
            navigateBack(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void navigateRefresh(boolean report) {
        try {
            DriverManager.getWebDriver().navigate().refresh();
            if (report) {
                GemTestReporter.addTestStep("Refresh Page", "Page Refresh Successful", STATUS.PASS);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Refresh Page", "Page Refresh Failed", STATUS.FAIL);
            e.printStackTrace();
        }
    }

    public static void navigateRefresh() {
        try {
            navigateRefresh(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void navigateForward(Boolean report) {
        try {
            DriverManager.getWebDriver().navigate().forward();
            if (report) {
                GemTestReporter.addTestStep("Navigate Forward", "Forward Navigation Successful ", STATUS.PASS);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Navigate Forward", "Forward Navigation Failed ", STATUS.FAIL);
            e.printStackTrace();
        }
    }

    public static void navigateForward() {
        try {
            navigateForward(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void navigateToUrl(String url, Boolean report) {
        try {
            DriverManager.getWebDriver().navigate().to(url);
            if (report) {
                GemTestReporter.addTestStep("Navigate to Url", "Navigation to Url Successful<BR>URL ~ " + url, STATUS.PASS);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Navigate to Url", "Navigation to Url Failed<BR>URL ~ " + url, STATUS.FAIL);
            e.printStackTrace();
        }
    }

    public static void navigateToUrl(String url) {
        try {
            navigateToUrl(url, false);
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

    public static Set<String> getWindowHandles() {
        try {
            return DriverManager.getWebDriver().getWindowHandles();
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

    public static void switchToAlert(Boolean report) {
        try {
            DriverManager.getWebDriver().switchTo().alert();
            if (report) {
                GemTestReporter.addTestStep("Switch To Alert", "Switch To Alert Successful", STATUS.PASS);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Switch To Alert", "Switch To Alert Failed", STATUS.FAIL);

            e.printStackTrace();
        }
    }

    public static void switchToAlert() {
        switchToAlert(false);
    }


    public static void AcceptAlert(Boolean report) {
        try {
            DriverManager.getWebDriver().switchTo().alert().accept();
            if (report) {
                GemTestReporter.addTestStep("Accept Alert", "Alert Accepted Successful", STATUS.PASS);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Accept Alert", "Alert Accepted Failed", STATUS.FAIL);
            e.printStackTrace();
        }
    }

    public static void AcceptAlert() {
        AcceptAlert(false);
    }

    public static void DismissAlert(Boolean report) {
        try {
            DriverManager.getWebDriver().switchTo().alert().dismiss();
            if (report) {
                GemTestReporter.addTestStep("Dismiss Alert", "Alert Dismissed Successful", STATUS.PASS);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Dismiss Alert", "Alert Dismissed Failed", STATUS.FAIL);
            e.printStackTrace();
        }
    }

    public static void DismissAlert() {
        DismissAlert(false);
    }

    public static void AlertInput(String input, Boolean report) {
        try {
            DriverManager.getWebDriver().switchTo().alert().sendKeys(input);
            if (report) {
                GemTestReporter.addTestStep("SendKeys To Alert", "SendKeys To Alert Successful <BR> input ~ " + input, STATUS.PASS);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("SendKeys To Alert", "SendKeys To Alert Failed <BR> input ~ " + input, STATUS.FAIL);
            e.printStackTrace();
        }
    }

    public static void AlertInput(String input) {
        AlertInput(input);
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

    public static void click(By locator, String elementLabel, Boolean report) {
        try {
            WebElement element = getElement(locator);
            element.click();
            if (report) {
                GemTestReporter.addTestStep("Click on ", "Click Successful on " + elementLabel, STATUS.PASS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            GemTestReporter.addTestStep("Click on ", "Click Failed on " + elementLabel, STATUS.FAIL);
        }
    }

    public static void click(By locator) {
        try {
            click(locator, "", false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void click(By locator, String steps, String description, Boolean report) {
        try {
            WebElement element = getElement(locator);
            element.click();
            if (report) {
                GemTestReporter.addTestStep(steps, description, STATUS.PASS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            GemTestReporter.addTestStep(steps, description, STATUS.FAIL);
        }
    }

    public static void click(By locator, String steps, String description) {
        try {
            click(locator, steps, description, false);
        } catch (Exception e) {
            e.printStackTrace();
            GemTestReporter.addTestStep(steps, description, STATUS.FAIL);
        }
    }


    public static void click(WebElement webElement, String steps, String description, Boolean report) {
        try {
            webElement.click();
            if (report) {
                GemTestReporter.addTestStep(steps, description, STATUS.PASS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            GemTestReporter.addTestStep(steps, description, STATUS.FAIL);
        }
    }

    public static void click(WebElement webElement, String steps, String description) {
        try {
            click(webElement, "", "", false);
        } catch (Exception e) {
            e.printStackTrace();
            GemTestReporter.addTestStep(steps, description, STATUS.FAIL);
        }
    }

    public static void click(WebElement webElement, String elementLabel, Boolean report) {
        try {
            webElement.click();
            if (report) {
                GemTestReporter.addTestStep("Click on ", "Click Successful on " + elementLabel, STATUS.PASS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            GemTestReporter.addTestStep("Click on ", "Click Failed on " + elementLabel, STATUS.FAIL);
        }
    }


    public static void click(WebElement webElement) {
        try {
            click(webElement, "", false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /////////////////Type Operation//////////////////////

    public static void typeText(By locator, String textToEnter, String steps, String description, Boolean report) {
        try {
            WebElement element = getElement(locator);
            element.clear();
            element.sendKeys(textToEnter);
            if (report) {
                GemTestReporter.addTestStep(steps, description, STATUS.PASS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            GemTestReporter.addTestStep(steps, description, STATUS.FAIL);
        }
    }

    public static void typeText(By locator, String steps, String description, String textToEnter) {
        try {
            typeText(locator, textToEnter, "", "", false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void typeText(By locator, String textToEnter, String elementLabel, Boolean report) {
        try {
            WebElement element = getElement(locator);
            element.clear();
            element.sendKeys(textToEnter);
            if (report) {
                GemTestReporter.addTestStep("Type on " + elementLabel, "Type Text Successful<BR>Type Text ~" + textToEnter, STATUS.PASS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            GemTestReporter.addTestStep("Type on " + elementLabel, "Type Text Failed<BR>Type Text ~" + textToEnter, STATUS.FAIL);
        }
    }

    public static void typeText(By locator, String textToEnter) {
        typeText(locator, textToEnter, "", false);
    }


    public static void typeText(WebElement element, String textToEnter, String steps, String description, Boolean report) {
        try {
            element.clear();
            element.sendKeys(textToEnter);
            if (report) {
                GemTestReporter.addTestStep(steps, description, STATUS.PASS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            GemTestReporter.addTestStep(steps, description, STATUS.FAIL);
        }
    }

    public static void typeText(WebElement element, String textToEnter, String steps, String description) {
        typeText(element, textToEnter, steps, description, false);
    }

    public static void typeText(WebElement element, String textToEnter, String elementLabel, Boolean report) {
        try {
            element.clear();
            element.sendKeys(textToEnter);
            if (report) {
                GemTestReporter.addTestStep("Type on " + elementLabel, "Type Text Successful<BR>Type Text ~" + textToEnter, STATUS.PASS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            GemTestReporter.addTestStep("Type on " + elementLabel, "Type Text Failed<BR>Type Text ~" + textToEnter, STATUS.FAIL);
        }
    }

    public static void typeText(WebElement element, String textToEnter) {
        try {
            typeText(element, textToEnter, "", false);
        } catch (Exception e) {
            e.printStackTrace();
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
