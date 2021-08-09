package NIRI.SiteTools;


import LocalUtils.UtilsBaseClass;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BaseClass extends UtilsBaseClass {

    public BaseClass() {
    }

    public WebDriverWait waiter() {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), 70,500);
        return wait;
    }

    public JavascriptExecutor getExecutor() {
        JavascriptExecutor executor = (JavascriptExecutor) getWebDriver();
        return executor;
    }


    protected void elementToHasAttribute(WebElement element, String attribute, String value) {
        waiter().until(ExpectedConditions.attributeContains(element, attribute, value));
    }


    public void scrollToElement(WebElement element) {
        getExecutor().executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
