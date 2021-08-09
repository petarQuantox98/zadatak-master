package NIRI.Tests.PC;


import NIRI.Pages.HomePage;
import NIRI.Pages.ProductPage;
import NIRI.SiteTools.BaseClass;
import NIRI.SiteTools.Constant;
import NIRI.SiteTools.Groups;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class CheckFAQ extends BaseClass {


    private HomePage homePage = new HomePage();
    private ProductPage productPage = new ProductPage();



    @Test(groups = {Groups.PC})
    public void countAllPhraseAppearances() {

        SoftAssert softAssert = new SoftAssert();

        Reporter.log("Navigate to home page", true);
        homePage.navigateToPageNoCookie(Constant.MAIN_NAVIGATION_URL);
        checkPageIsReady();

        Reporter.log("Clicks on wanted menu link", true);
        homePage.clickOnNavigationHeadlineLink(Constant.NAVIGATION_MENU_LINK);
        checkPageIsReady();

        Reporter.log("Expands all faq elements", true);
        productPage.expandAllFaqElements();

        Reporter.log("Asserts if all appearances number is not smaller than stated value", true);
        softAssert.assertTrue(productPage.countAllStringAppearances(productPage.getFaqElementAnswers(), Constant.STRING_TO_CONTAIN) >= Constant.MINIMAL_NIMBER_OF_APPEARANCES);

        Reporter.log("Total number of counted strings: [" + productPage.countAllStringAppearances(productPage.getFaqElementAnswers(), Constant.STRING_TO_CONTAIN) + "]", true);

        softAssert.assertAll();

    }

}
