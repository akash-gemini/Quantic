package com.gemini.automation.generic;

import com.gemini.automation.ApiTest.ProjectApiUrl;
import com.gemini.automation.ApiTest.ProjectSampleJson;
import com.qa.gemini.quartzReporting.GemTestReporter;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.InputStream;
import java.lang.reflect.Method;

public class QuanticAPIBase extends QuanticGenericUtils {
    @BeforeSuite
    public void beforeSuite(ITestContext iTestContext) {
//        setKerberosRequiredConfiguration();
        initializeQuanticGlobalVariables(iTestContext);
        int numberOfTestCasesToRun = iTestContext.getSuite().getAllInvokedMethods().size();
        //Report initialize
        String urlFileName = QuanticGlobalVar.projectName + "_" + QuanticGlobalVar.environment + "_Url.properties";
        InputStream ip = ClassLoader.getSystemResourceAsStream(urlFileName);
        ProjectApiUrl.initializeApiUrl(ip);
        ProjectSampleJson.loadSampleJson();


        String loc = null;
        try {
            if (quanticProperty.containsKey("reportLocation")) {
                loc = quanticProperty.getProperty("reportLocation");
            } else if (ProjectProperties.getStringPropertyNames().contains("reportLocation")) {
                loc = ProjectProperties.getProperty("reportLocation");
            } else {
                loc = null;
            }
        } catch (Exception e) {
            System.out.println("Some Error Occur With reportLocation . Default reportLocation Set");
        }


        // Initializing startSuite of Gem-Reporting
        GemTestReporter.startSuite(QuanticGlobalVar.projectName, QuanticGlobalVar.environment, loc);
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
        TestCaseData.setCurrentTestCaseData(testcaseName);


        String productType = ProjectProperties.getProperty("productType") == null ? "GemJavaProject" : ProjectProperties.getProperty("productType");
        GemTestReporter.startTestCase(testcaseName, "test", productType, false);
        GemTestReporter.startTestCase(testcaseName, "test", "GemJavaProject", false);

    }

    @AfterMethod
    public void afterMethod() {
        //Report
        GemTestReporter.endTestCase();
    }

    @AfterClass
    public void afterClass() {

    }

    @AfterTest
    public void afterTest() {
    }

    @AfterSuite
    public void afterSuite() {
        //Report execution
        GemTestReporter.endSuite();
        //Report mail
    }
}