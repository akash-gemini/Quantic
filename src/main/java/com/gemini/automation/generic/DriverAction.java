package com.gemini.automation.generic;

import java.security.DomainCombiner;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.*;


public class DriverAction implements genericInterface {

    private static final String PASS = "Pass";
    private static final String FAIL = "Fail";

    public void launchUrl(String url) {
        try {
            DriverManager.getWebDriver().get(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getTitle(String url) {
        try {
            String title = DriverManager.getWebDriver().getTitle();
            return title;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void maximizeBrowser() {
        try {
            DriverManager.getWebDriver().manage().window().maximize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void minimizeBrowser() {
        try {
            DriverManager.getWebDriver().manage().window().minimize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Dimension getBrowserSize() {
        try {
            return DriverManager.getWebDriver().manage().window().getSize();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setBrowserSize(int width, int height) {
        try {
            Dimension dimension = new Dimension(width, height);
            DriverManager.getWebDriver().manage().window().setSize(dimension);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBrowserPosition(int x, int y) {
        try {
            Point point = new Point(x, y);
            DriverManager.getWebDriver().manage().window().setPosition(point);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getBrowserLocation() {//change return
        try {
            DriverManager.getWebDriver().manage().window().getPosition();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //////////TimeOuts///////////

    public void setImplicitTimeOut(long seconds) {
        try {
            DriverManager.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setScriptTimeOut(long seconds) {
        try {
            DriverManager.getWebDriver().manage().timeouts().scriptTimeout(Duration.ofSeconds(seconds));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPageLoadTimeOut(long seconds) {
        try {
            DriverManager.getWebDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(seconds));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ///Navigation

    public void navigateBack() {
        try {
            DriverManager.getWebDriver().navigate().back();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void navigateRefresh() {
        try {
            DriverManager.getWebDriver().navigate().refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void navigateForward() {
        try {
            DriverManager.getWebDriver().navigate().forward();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void navigateToUrl(String url) {
        try {
            DriverManager.getWebDriver().navigate().to(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    ///WebElement
    public WebElement getElement(By locator) {
        try {
            WebElement element = DriverManager.getWebDriver().findElement(locator);
            return element;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //return set<String>
    public String getWindowHandle() {
        try {
            return DriverManager.getWebDriver().getWindowHandle();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getCurrentURL() {
        try {
            return DriverManager.getWebDriver().getCurrentUrl();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getPageSource() {
        try {
            return DriverManager.getWebDriver().getPageSource();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    ///////////Switch Operations/////////////////////
    public void switchToWindow(String nameOfHandle) {
        try {
            DriverManager.getWebDriver().switchTo().window(nameOfHandle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToFrame(int index) {
        try {
            DriverManager.getWebDriver().switchTo().frame(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToFrame(String nameOrId) {
        try {
            DriverManager.getWebDriver().switchTo().frame(nameOrId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToFrame(WebElement frameElement) {
        try {
            DriverManager.getWebDriver().switchTo().frame(frameElement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToParentFrame() {
        try {
            DriverManager.getWebDriver().switchTo().parentFrame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToDefaultContent() {
        try {
            DriverManager.getWebDriver().switchTo().defaultContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToActiveElement() {
        try {
            DriverManager.getWebDriver().switchTo().activeElement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToAlert() {
        try {
            DriverManager.getWebDriver().switchTo().alert();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //////////////Web Elements///////////////////

    public List<WebElement> getElements(By locator) {
        try {
            List<WebElement> elements = DriverManager.getWebDriver().findElements(locator);
            return elements;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getElementText(By locator) {
        try {
            WebElement element = getElement(locator);
            String elementText = element.getText();
            return elementText;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getElementText(WebElement element) {
        try {
            String elementText = element.getText();
            return elementText;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String> getElementsText(By locator) {
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

    public void click(By locator,String elementLabel){
        try{
            WebElement element = getElement(locator);
            element.click();
            System.out.println("Successful to Click on "+elementLabel);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to Click on "+elementLabel);
        }
    }

    public void click(By locator,String steps,String description){
        try{
            WebElement element=getElement(locator);
            element.click();
            System.out.println("Successful to Click ");
            System.out.println("Steps : "+steps);
            System.out.println("Description : "+description);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to Click ");
            System.out.println("Steps : "+steps);
            System.out.println("Description : "+description);
        }
    }

    public void click (WebElement webElement,String steps,String description){
        try{
            webElement.click();
            System.out.println("Successful to Click on web element : "+webElement);
            System.out.println("Steps : "+steps);
            System.out.println("Description : "+description);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to Click on web element : "+webElement);
            System.out.println("Steps : "+steps);
            System.out.println("Description : "+description);
        }
    }

    public void click (WebElement webElement,String elementLabel){
        try{
            webElement.click();
            System.out.println("Successful to Click on web element : "+webElement);
            System.out.println("element label : "+elementLabel);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to Click on web element : "+webElement);
            System.out.println("element label : "+elementLabel);
        }
    }

    public void clickWithoutReporting(By locator){
        try {
            WebElement element = getElement(locator);
            element.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickWithoutReporting(WebElement webElement) {
        try{
            webElement.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /////////////////Type Operation//////////////////////

    public void  typeText(By locator,String textToEnter , String steps,String description){
        try{
            WebElement element=getElement(locator);
            element.clear();
            element.sendKeys(textToEnter);
            System.out.println("TypeText Successful ");
            System.out.println("Steps : "+steps);
            System.out.println("Description : "+description);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("TypeText Failed");
            System.out.println("Steps : "+steps);
            System.out.println("Description : "+description);
        }
    }

    public void typeTextWithoutReporting(By locator,String textToEnter){
        try{
            WebElement element =getElement(locator);
            element.clear();
            element.sendKeys(textToEnter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void typeText(By locator,String textToEnter,String elementLabel){
        try{
            WebElement element = getElement(locator);
            element.clear();
            element.sendKeys(textToEnter);
            System.out.println("TypeText Successful ");
            System.out.println("Text to Enter : "+textToEnter);
            System.out.println("element Label : "+elementLabel);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("TypeText Failed");
            System.out.println("Text to Enter : "+textToEnter);
            System.out.println("element Label : "+elementLabel);
        }
    }

    public void  typeText(WebElement element,String textToEnter , String steps,String description){
        try{
            element.clear();
            element.sendKeys(textToEnter);
            System.out.println("TypeText Successful ");
            System.out.println("Steps : "+steps);
            System.out.println("Description : "+description);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("TypeText Failed");
            System.out.println("Steps : "+steps);
            System.out.println("Description : "+description);
        }
    }

    public void typeTextWithoutReporting(WebElement element,String textToEnter){
        try{
            element.clear();
            element.sendKeys(textToEnter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void typeText(WebElement element,String textToEnter,String elementLabel){
        try{
            element.clear();
            element.sendKeys(textToEnter);
            System.out.println("TypeText Successful ");
            System.out.println("Text to Enter : "+textToEnter);
            System.out.println("element Label : "+elementLabel);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("TypeText Failed");
            System.out.println("Text to Enter : "+textToEnter);
            System.out.println("element Label : "+elementLabel);
        }
    }

    //////////////Accessible name////////////////////

    public String getAccessibleName(WebElement webElement){
        try{
            return webElement.getAccessibleName();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getAccessibleName(By locator){
        try{
            WebElement element = getElement(locator);
            return element.getAccessibleName();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getArialRole(By locator){
        try{
            WebElement element = getElement(locator);
            return element.getAriaRole();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getArialRole(WebElement element){
        try{
            return element.getAriaRole();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getAttributeName(WebElement webElement,String name){
        try{
            return webElement.getAttribute(name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getCSSValue(By locator,String propertyName){
        try{
            WebElement element = getElement(locator);
            return element.getCssValue(propertyName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getCSSValue(WebElement element,String propertyName){
        try{
            return element.getCssValue(propertyName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
