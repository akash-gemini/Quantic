package com.gemini.automation.generic;

import com.gemini.automation.ApiTest.ProjectApiUrl;
import com.gemini.automation.ApiTest.ProjectSampleJson;
import com.qa.gemini.quartzReporting.GemTestReporter2;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.InputStream;
import java.lang.reflect.Method;

public class QuanticAPIBase extends QuanticGenericUtils {
    @BeforeSuite
    public void beforeSuite(ITestContext iTestContext){
//        setKerberosRequiredConfiguration();
        initializeQuanticGlobalVariables(iTestContext);
        int numberOfTestCasesToRun = iTestContext.getSuite().getAllInvokedMethods().size();
        //Report initialize
        String urlFileName = QuanticGlobalVar.projectName+"_"+QuanticGlobalVar.environment+"_Url.properties";
        InputStream ip = ClassLoader.getSystemResourceAsStream(urlFileName);
        ProjectApiUrl.initializeApiUrl(ip);
        ProjectSampleJson.loadSampleJson();

        // Initializing startSuite of Gem-Reporting
        GemTestReporter2.startSuite(QuanticGlobalVar.projectName, QuanticGlobalVar.environment);
    }

    @BeforeTest
    public void beforeTest(){}

    @BeforeClass
    public void beforeClass(){}

    @BeforeMethod
    public void beforeMethod(Method method){
        String testcaseName = method.getName();
        TestCaseData.setCurrentTestCaseData(testcaseName);
        //Report
        GemTestReporter2.startTestCase(testcaseName, "test", "GemJavaProject", false);

    }

    @AfterMethod
    public void afterMethod(){
        //Report
        GemTestReporter2.endTestCase();
    }

    @AfterClass
    public void afterClass(){

    }

    @AfterTest
    public void afterTest(){}

    @AfterSuite
    public void afterSuite(){
        //Report execution
        GemTestReporter2.endSuite();
        //Report mail
    }
}