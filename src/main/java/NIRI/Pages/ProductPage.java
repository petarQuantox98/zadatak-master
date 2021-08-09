package NIRI.Pages;


import NIRI.SiteTools.BaseClass;
import LocalUtils.Annotations.WebElementLocator;
import LocalUtils.Annotations.WebElementLocatorFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductPage extends BaseClass {

    // Web elements

    /**
     * All faq elements on page
     *
     * @return Web element list
     */
    @WebElementLocator(webDesktop = "section#faq div.container div.col-md-12 a.faq-title",
                       webPhone = "section#faq div.container div.col-md-12 a.faq-title")
    private static List<WebElement> faqElements() {
        return getDriver().findElements(By.cssSelector(new WebElementLocatorFactory().getLocator(ProductPage.class, "faqElements")));
    }

    /**
     * All faq expands
     *
     * @return Web element list
     */
    @WebElementLocator(webDesktop = "section#faq div.container div.col-md-12 div.accordion-slide",
                       webPhone = "section#faq div.container div.col-md-12 div.accordion-slide")
    private static List<WebElement> faqExpands() {
        return getDriver().findElements(By.cssSelector(new WebElementLocatorFactory().getLocator(ProductPage.class, "faqExpands")));
    }

    /**
     * All faq answers
     *
     * @return Web element list
     */
    @WebElementLocator(webDesktop = "section#faq div.container div.col-md-12 div.accordion-slide p",
                       webPhone = "section#faq div.container div.col-md-12 div.accordion-slide p")
    private static List<WebElement> faqElementAnswers() {
        return getDriver().findElements(By.cssSelector(new WebElementLocatorFactory().getLocator(ProductPage.class, "faqElementAnswers")));
    }

    // Actions


    /**
     * Gets faq answers
     *
     * @return String list
     */
    public List<String> getFaqElementAnswers() {
        List<String> answersList = new ArrayList<>();
        for(WebElement faqAnswer : faqElementAnswers()) {
            answersList.add(faqAnswer.getText());
        }
        return answersList;
    }

    /**
     * Expands all faq answers
     *
     */
    public void expandAllFaqElements() {
        for(int counter = 0; counter < faqElements().size(); counter ++) {
            scrollToElement(faqElements().get(counter));
            faqElements().get(counter).click();
            elementToHasAttribute(faqExpands().get(counter), "style", "display: block;");
        }

    }

    /**
     * Counts all string appearances in each element of list - if it can be found multiple times inside one, those will be counted, too
     *
     * @return int
     */
    public int countAllStringAppearances(List<String> stringsToCheck, String stringToContain) {
        int counter = 0;
        Pattern patternToMatch = Pattern.compile(stringToContain.toLowerCase());
        for (String currentString : stringsToCheck) {
            Matcher matcher = patternToMatch.matcher(currentString.toLowerCase());
            while (matcher.find()) {
                counter ++;
            }
        }
        return counter;
    }

}
