package LocalUtils;


import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Optional;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.TimeUnit;


import static LocalUtils.OSDetection.OsCheck;
import static LocalUtils.OSDetection.OsCheck.baseFilePath;


public class UtilsBaseClass {

    public static WebDriver driver;
    public static ThreadLocal<String> testPlatform = new ThreadLocal<>();

    //PC Setup
    private static ThreadLocal<WebDriver> dr = new ThreadLocal<>();

    private void setWebDriver(WebDriver driver) {
        dr.set(driver);
    }

    public static WebDriver getWebDriver() {
        return dr.get();
    }

    public static WebDriver getDriver() {
        if (getWebDriver() != null) {
            driver = dr.get();
        }
        return driver;
    }


    @BeforeSuite
    public void cleanUpLastRunAndSetup(ITestContext xmlName) {
        OsCheck.getOperatingSystemType();
        OsCheck.setBaseFilePath();
        if (OsCheck.getOperatingSystemType() == OsCheck.OSType.Linux) {
            ShellScript shellScript = new ShellScript();
            shellScript.runScript("sh ./Properties/UnixDriver777.sh");
        }

    }

    //Browser parameter comes from the XML file, uses a default Browser value if running locally
    @Parameters({"browser"})
    @BeforeMethod(onlyForGroups = {"PC", "MobEmulator"})
    public void setUp(@Optional("Chrome") String browser, Method m, ITestResult iTestResult) throws IllegalAccessException {
        System.out.println("Initializing WebDriver: " + browser);

        try {
            Thread.sleep((long) (Math.random() * 8000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setPlatform(iTestResult);

        WebDriverFactory webDriverFactory = new WebDriverFactory();
        WebDriver driver = webDriverFactory.create(browser);
        setWebDriver(driver);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);


        switch (getPlatform()) {
            case "webDesktop":
                getWebDriver().manage().window().setSize(new Dimension(1920, 1080));
                break;
            case "webPhone":
                getWebDriver().manage().window().setSize(new Dimension(webDriverFactory.getWindowWidth(), webDriverFactory.getWindowHeight()));
                break;
        }

    }


    @AfterMethod
    public void afterTestActions(ITestResult result, ITestContext xmlName) {
            if (result.getStatus() == ITestResult.SUCCESS) {
                System.out.println("Test PASSED: " + result.getMethod().getMethodName());
            }
    }


    @AfterMethod
    public void tearDown() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (getWebDriver() != null) {
            try {
                getWebDriver().quit();
            } catch (WebDriverException e ) {
                System.err.println("Error when attempting to quit Browser session \n" + e);
            }
            testPlatform.remove();
            dr.remove();
        }
    }


    /**
     * Getter Method for the Platform that is Set using "setPlatform()" method
     * @return String
     */

    public static String getPlatform() {
        return testPlatform.get();
    }

    /**
     * Setter Method for the Platform the Test is intended to run on
     * In "setup()" Method, set the platform based on the Groups present on the Test
     */
    protected void setPlatform(ITestResult iTestResult){
        String[] testGroups = iTestResult.getMethod().getGroups();
        String platform = "";
        for (String tgroup : testGroups) {
            if (tgroup.contains("PC")) {
                platform = "webDesktop";
            } else if (tgroup.contains("MobEmulator")) {
                platform = "webPhone";
            }
        }
        testPlatform.set(platform);
    }


    public static void checkPageIsReady() {
        JavascriptExecutor js = null;
        if (getWebDriver() != null) {
            js = (JavascriptExecutor) getWebDriver();
        }

        //This loop will rotate for 25 times to check If page Is ready after every 2 second.
        for (int i = 0; i < 25; i++) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.err.println("Hit problem while waiting for JavaScript to load: " + e.getMessage());
            }
            //To check page ready state.
            if (js != null && js.executeScript("return document.readyState").toString().equals("complete")) {

                break;
            }
        }
    }


    /**
     * 1. Check Env Var "baseURL" for a value
     * 2. If empty, check XMLParameters for "baseUrl" Parameter, return the value
     *
     * @return A String for the baseURL to be used for all URL's, Ex "https://www-stage."
     */
    public String getBaseURL() {
        String baseURL;
        if (System.getProperty("baseURL") != null) {
            baseURL = System.getProperty("baseURL");
            return baseURL;
        } else if (xmlParameters().containsKey("baseURL")) {
            return xmlParameters().get("baseURL");
        } else {
            baseURL = "https://www.";
        }
        return baseURL;
    }


    public WebDriverWait waiter() {
        return new WebDriverWait(getWebDriver(), 60);
    }


    /**
     * @return <Key, Value> Map of all XML Parameters
     */
    public static Map<String, String> xmlParameters() {
        return Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getAllParameters();
    }

}