package com.gemini.automation.generic;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface genericInterface {
    public void launchUrl(String url);

    public String getTitle(String url);

    public void maximizeBrowser();

    public void minimizeBrowser();

    public Dimension getBrowserSize();

    public void setBrowserSize(int width, int height);

    public void setBrowserPosition(int x, int y);

    public Point getBrowserLocation();

    //////////TimeOuts///////////
    public void setImplicitTimeOut(long seconds);

    public void setScriptTimeOut(long seconds);

    public void setPageLoadTimeOut(long seconds);

    ///Navigation////////////

    public void navigateBack();

    public void navigateRefresh();

    public void navigateForward();

    public void navigateToUrl(String url);

    ///WebElement/////////////
    public WebElement getElement(By locator);

    public String getWindowHandle();

    public String getCurrentURL();

    public String getPageSource();

    ///Switch Operation

    public void switchToWindow(String nameOfHandle);

    public void switchToFrame(int index);

    public void switchToFrame(String nameOrId);

    public void switchToFrame(WebElement frameElement);

    public void switchToParentFrame();

    public void switchToDefaultContent();

    public void switchToActiveElement();

    public void switchToAlert();

    //////////////Web Elements///////////////////

    public List<WebElement> getElements(By locator);

    public String getElementText(By locator);

    public List<String> getElementsText(By locator);

    public String getElementText(WebElement element);

    //////////////////Click Operation/////////////////

    public void click(By locator, String elementLabel);

    public void click(By locator, String steps, String description);

    public void click(WebElement webElement, String steps, String description);

    public void click(WebElement webElement, String elementLabel);

    public void clickWithoutReporting(By locator);

    public void clickWithoutReporting(WebElement webElement);

    /////////////////Type Operation//////////////////////

    public void typeText(By locator, String textToEnter, String steps, String description);

    public void typeTextWithoutReporting(By locator, String textToEnter);

    public void typeText(By locator, String textToEnter, String elementLabel);

    public void typeText(WebElement element, String textToEnter, String steps, String description);

    public void typeTextWithoutReporting(WebElement element, String textToEnter);

    public void typeText(WebElement element, String textToEnter, String elementLabel);

    //////////////Accessible name////////////////////

    public String getAccessibleName(WebElement webElement);

    public String getAccessibleName(By locator);

    public String getArialRole(By locator);

    public String getArialRole(WebElement element);

    public String getAttributeName(WebElement webElement,String name);

    public String getCSSValue(By locator,String propertyName);

    public String getCSSValue(WebElement element,String propertyName);

    public String takeSnapShot();

    }
