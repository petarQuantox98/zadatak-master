package LocalUtils;

import NIRI.SiteTools.JsonReadWrite;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;

import static LocalUtils.OSDetection.OsCheck.baseDownloadPath;
import static LocalUtils.OSDetection.OsCheck.baseFilePath;
import static LocalUtils.UtilsBaseClass.xmlParameters;

class WebDriverFactory {

    private Integer windowWidth;
    private Integer windowHeight;
    private String userAgent;
    private ReadProperties readProperties = new ReadProperties();

    private int count = 0;
    private int maxTry = 2;

    public Integer getWindowWidth() {
        return windowWidth;
    }

    public void setWindowWidth(Integer windowWidth) {
        this.windowWidth = windowWidth;
    }

    public Integer getWindowHeight() {
        return windowHeight;
    }

    public void setWindowHeight(Integer windowHeight) {
        this.windowHeight = windowHeight;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    WebDriver create(String type) throws IllegalAccessException {

        WebDriver driver;
        ChromeOptions options = new ChromeOptions();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        OSDetection.OsCheck.OSType ostype = OSDetection.OsCheck.getOperatingSystemType();


        switch (type) {
            case "Firefox":
                switch (ostype) {
                    case Windows:
                        System.setProperty("webdriver.gecko.driver", "./Drivers/Windows/geckodriver.exe");
                        break;
                    case MacOS:
                        break;
                    case Linux:
                        System.setProperty("webdriver.gecko.driver", "./Drivers/Linux/geckodriver");
                        firefoxOptions.setBinary("/usr/bin/iceweasel");
                        firefoxOptions.setHeadless(true);
                        break;
                    case Other:
                        break;
                }


                System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
                System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
                firefoxOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS);
                driver = new FirefoxDriver(firefoxOptions);

                break;


            case "Chrome":
                while (true) {
                    try {


                        switch (ostype) {
                            case Windows:
                                System.setProperty("webdriver.chrome.silentOutput", "true");
                                System.setProperty("webdriver.chrome.driver", "./Drivers/Windows/chromedriver.exe");


                                //If Build is not run locally, or contains a Param=HeadlessBrowser as false. Run as headless
                                if ((xmlParameters().containsKey("headlessBrowser"))) {
                                    if ((xmlParameters().get("headlessBrowser").toLowerCase().contains("true"))) {
                                        options.addArguments("--headless");
                                        options.addArguments("--disable-gpu");
                                        options.addArguments("--disable-features=VizDisplayCompositor");
                                        options.addArguments("--window-size=1980,1080");
                                    }
                                }

                                HashMap<String, Object> chromePrefs = new HashMap<>();
                                chromePrefs.put("profile.default_content_settings.popups", 0);
                                chromePrefs.put("download.default_directory", baseDownloadPath);

                                options.setExperimentalOption("prefs", chromePrefs);
                                break;
                            case MacOS:
                                break;
                            case Linux:
                                System.setProperty("webdriver.chrome.driver", "./Drivers/Linux/chromedriver");
                                System.setProperty("webdriver.chrome.silentOutput", "true");
                                options.addArguments("--headless");
                                options.addArguments("--disable-gpu");
                                options.addArguments("--window-size=1980,1080");
                                options.addArguments("--no-sandbox");
                                options.addArguments("--disable-dev-shm-usage");
                                break;
                            case Other:
                                break;
                        }
                        //If "Unexpected Alert" is shown, Accept it
                        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);


                        //If test is WebMobile, setup device as a ChromeEmulated device
                        if (UtilsBaseClass.getPlatform().equals("webPhone")) {
                            // Get phone type switch, for two different running test types
                            String phoneType;
                            if (xmlParameters().get("device") != null) {
                                phoneType = xmlParameters().get("device");
                            } else {
                                phoneType = readProperties.getPropertyValue("device_samsung_s8", "Properties/Devices/mobile_type.properties");
                            }

                            Map<String, Object> deviceProperties = JsonReadWrite.parseDeviceTypeJSONObject(baseFilePath + "/src/main/resources/BrowserExtensions/UserAgents.json", phoneType);
                            String userAgent = deviceProperties.get("ua").toString();
                            setUserAgent(userAgent);
                            deviceProperties.remove("ua");
                            setWindowWidth(Integer.valueOf(deviceProperties.get("width").toString()));
                            setWindowHeight(Integer.valueOf(deviceProperties.get("height").toString()));

                            Map<String, Object> mobileEmulation = new HashMap<>();
                            mobileEmulation.put("deviceMetrics", deviceProperties);

                            mobileEmulation.put("userAgent", userAgent);
                            options.setExperimentalOption("mobileEmulation", mobileEmulation);
                        }
                        driver = new ChromeDriver(options);

                        break;
                    } catch (WebDriverException e) {
                        System.err.println("Error in Webdriver Initialization: " + e);

                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }

                        if (++count == maxTry) throw e;
                    }
                }
                break;


            default:
                throw new IllegalAccessException();
        }
        return driver;


    }
}
