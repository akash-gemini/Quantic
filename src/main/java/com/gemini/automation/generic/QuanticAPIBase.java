package com.gemini.automation.generic;

import com.gemini.automation.ApiTest.ProjectApiUrl;
import com.gemini.automation.ApiTest.ProjectSampleJson;
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
        //Submit test case
    }

    @AfterMethod
    public void afterMethod(){
        //Report
    }

    @AfterClass
    public void afterClass(){

    }

    @AfterTest
    public void afterTest(){}

    @AfterSuite
    public void afterSuite(){
        //Report execution
        //Report mail
    }
}
