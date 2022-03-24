package com.gemini.automation.generic;

import com.gemini.automation.listners.QuanticTestngTestFilter;
import com.qa.gemini.quartzReporting.GemTestReporter;
import com.qa.gemini.quartzReporting.GemTestReporter2;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.lang.reflect.Method;

@Listeners(QuanticTestngTestFilter.class)
public class QuanticUIBase extends QuanticGenericUtils {

    @BeforeSuite
    public void beforeSuite(ITestContext iTestContext) {
        initializeQuanticGlobalVariables(iTestContext);
        int numberOfTestCasesToRun = iTestContext.getSuite().getAllInvokedMethods().size();
        //Report
        GemTestReporter.startSuite(QuanticGlobalVar.projectName, QuanticGlobalVar.environment);
        GemTestReporter2.startSuite(QuanticGlobalVar.projectName, QuanticGlobalVar.environment);

    }

    @BeforeTest
    public void beforeTest() {
    }

    @BeforeClass
    public void beforeClass() {
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        String testcaseName = method.getName();
        DriverManager.initializeBrowser(QuanticGlobalVar.browserInTest);
        DriverAction driverAction = new DriverAction();
        driverAction.maximizeBrowser();
        driverAction.setImplicitTimeOut(Long.parseLong(ProjectProperties.getProperty("browserTimeOut")));
        driverAction.setPageLoadTimeOut(Long.parseLong(ProjectProperties.getProperty("browserTimeOut")));
        driverAction.launchUrl(ProjectProperties.getProperty("baseURL"));
        TestCaseData.setCurrentTestCaseData(testcaseName);
        //Report
        GemTestReporter.startTestCase(testcaseName, "test", "GemJavaProject", false);
        GemTestReporter2.startTestCase(testcaseName, "test", "GemJavaProject", false);

    }

    @AfterMethod
    public void afterMethod() {
        DriverManager.closeDriver();
        //Report
        GemTestReporter.endTestCase();
        GemTestReporter2.endTestCase();


    }

    @AfterClass
    public void afterClass() {
    }

    @AfterTest
    public void afterTest(){

    }

    @AfterSuite
    public void afterSuite(){
        //Report
        GemTestReporter.endSuite();
        GemTestReporter2.endSuite();
        //send mail
    }


}
