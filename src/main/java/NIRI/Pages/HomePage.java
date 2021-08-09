package NIRI.Pages;


import NIRI.SiteTools.BaseClass;
import LocalUtils.Annotations.WebElementLocator;
import LocalUtils.Annotations.WebElementLocatorFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomePage extends BaseClass {

    // Web elements

    /**
     * Navigation elements list
     *
     * @return Web element list
     */
    @WebElementLocator(webDesktop = "ul.top-menu li.menu-item.menu-item-type-post_type.menu-item-object-page a.nav-link",
                       webPhone = "ul.top-menu li.menu-item.menu-item-type-post_type.menu-item-object-page a.nav-link")
    private static List<WebElement> navigationElements() {
        return getDriver().findElements(By.cssSelector(new WebElementLocatorFactory().getLocator(HomePage.class, "navigationElements")));
    }

    /**
     * Navigation headlines list
     *
     * @return Web element list
     */
    @WebElementLocator(webDesktop = "ul.top-menu li.menu-item.menu-item-type-post_type.menu-item-object-page a.nav-link strong",
                       webPhone = "ul.top-menu li.menu-item.menu-item-type-post_type.menu-item-object-page a.nav-link strong")
    private static List<WebElement> navigationHeadlines() {
        return getDriver().findElements(By.cssSelector(new WebElementLocatorFactory().getLocator(HomePage.class, "navigationHeadlines")));
    }

    /**
     * Navigation main menu on mobile
     *
     * @return Web element
     */
    @WebElementLocator(webPhone = "header#main-menu nav.navbar a.navbar-toggler")
    private static WebElement mainNavigationMenuMobile() {
        return getDriver().findElement(By.cssSelector(new WebElementLocatorFactory().getLocator(HomePage.class, "mainNavigationMenuMobile")));
    }

    /**
     * Map with key -> headline string and value -> clickable web element which matches current headline
     *
     * @return Map
     */
    private static Map<String, WebElement> navigationComplete() {
        Map<String, WebElement> headlineLinks = new HashMap<>();
        for (int counter = 0; counter < navigationElements().size(); counter ++) {
            headlineLinks.put(navigationHeadlines().get(counter).getText(), navigationElements().get(counter));
        }
        return headlineLinks;
    }

    // Actions

    /**
     * Navigates to page URL
     *
     * @param pageUrl - passed URL, or URL without base
     */
    public void navigateToPageNoCookie(String pageUrl) {
        if (pageUrl.contains("http")) {
            getDriver().get(pageUrl);
        } else {
            getDriver().get(getBaseURL() + pageUrl);
        }
        checkPageIsReady();
    }



    /**
     * Clicks on web element which has string value as parameter
     *
     * @param navigationHeadline - string value of element which should be clicked
     */
    public void clickOnNavigationHeadlineLink(String navigationHeadline) {
        for (String key : navigationComplete().keySet()) {
            if (key.toLowerCase().equals(navigationHeadline.toLowerCase())) {
                navigationComplete().get(key).click();
            }
        }
    }

    /**
     * Clicks on main navigation menu for mobile
     *
     */
    public void clickMainNavigationMenuMobile() {
        mainNavigationMenuMobile().click();
    }

}
