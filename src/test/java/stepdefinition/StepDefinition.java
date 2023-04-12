package stepdefinition;

import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.bytebuddy.asm.Advice;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.io.StringReader;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class StepDefinition {

    private WebDriver driver;
    static String currentUrl = "";
    static String size;
    static String invalidmobilenoerrortext;
    static ArrayList<String> handles;
    static String tshirt;
    static String Men;
    static String Topwear;
    WebElement mobileNumberField;
    WebElement addtobagtextpopup2;
    WebElement search;
    static Actions ac;

    @Given("I have opened my web browser")
    public void i_have_opened_my_web_browser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\swati.sharma\\Desktop\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Then("I navigate to Myntra")
    public void i_navigate_to_myntra() throws InterruptedException {
        driver.get("https://www.myntra.com/");
        Thread.sleep(500);
    }

    @Then("Validate if the myntra page opened")
    public void validate_if_the_myntra_page_opened() {
        WebElement profileIcon = driver.findElement(myntraLocators.profileIcon);
        profileIcon.click();
        WebElement myntraIcon = driver.findElement(myntraLocators.myntraIcon);
    }

    @Given("I have clicked on Signup or login button under profile")
    public void iHaveClickedOnSignupOrLoginButtonUnderProfile() throws InterruptedException {
        currentUrl = driver.getCurrentUrl();
        System.out.println("currentUrl:" + currentUrl);
        Assert.assertTrue(currentUrl.contains("https://www.myntra.com/"));
        ac = new Actions(driver);
        ac.moveToElement(driver.findElement(myntraLocators.profileIcon)).perform();
        WebElement loginOption = driver.findElement(myntraLocators.loginOption);
        loginOption.click();
        Thread.sleep(100);
    }

    @Then("Validate the UI is redirected to login or singup page")
    public void validateTheUIIsRedirectedToLoginOrSingupPage() {
        String currentUrl1 = driver.getCurrentUrl();
        System.out.println("currentUrl1:" + currentUrl1);
        Assert.assertTrue(currentUrl1.contains("https://www.myntra.com/login?referer=https://www.myntra.com/"));
    }

    @And("User should not able to proceed with invalid mobile number")
    public void userShouldNotAbleToProceedWithInvalidMobileNumber() {
        mobileNumberField = driver.findElement(myntraLocators.mobilenumberfield);
        mobileNumberField.click();
        mobileNumberField.sendKeys("123456");
        driver.findElement(myntraLocators.continuebutton).click();
        invalidmobilenoerrortext = driver.findElement(myntraLocators.invalidmobilenoerror).getText();
        System.out.println("invalidmobilenoerrortext:" + invalidmobilenoerrortext);
    }

    @Then("User to validate the termsofuse and privacypolicy link")
    public void userToValidateTheTermsofuseAndPrivacypolicyLink() throws InterruptedException {
        driver.findElement(myntraLocators.termsofuse).click();
        String currentUrl2 = driver.getCurrentUrl();
        System.out.println("currentUrl2:" + currentUrl2);
        Assert.assertTrue(currentUrl2.contains("https://www.myntra.com/termsofuse"));
        driver.navigate().back();
        driver.close();
        Thread.sleep(100);
    }

    @Given("Validate if desktop tag are present")
    public void validateIfDesktopTagArePresent() {
        List<WebElement> mainpageelements1list = driver.findElements(myntraLocators.mainpageelements1);
        System.out.println("mainpageelements1:" + mainpageelements1list.size());
        for (int i = 0; i < mainpageelements1list.size(); i++) {
            String mainpageelement1Text = mainpageelements1list.get(i).getText();
            System.out.println(mainpageelement1Text);
        }
        List<WebElement> mainpageelements2list = driver.findElements(myntraLocators.mainpageelements2);
        System.out.println("mainpageelements2:" + mainpageelements2list.size());
        for (int i = 0; i < mainpageelements2list.size(); i++) {
            String mainpageelement2Text = mainpageelements2list.get(i).getText();
            System.out.println(mainpageelement2Text);
        }
        search = driver.findElement(myntraLocators.searchtab);
        Assert.assertTrue(search.isDisplayed());
    }

    @Then("click on search button and validate the url")
    public void clickOnSearchButtonAndValidateTheUrl() throws InterruptedException {
        search = driver.findElement(myntraLocators.searchtab);
        search.click();
        tshirt = "Tshirts for women";
        search.sendKeys(tshirt);
        search.sendKeys(Keys.ENTER);
        String CurrentUrl4 = driver.getCurrentUrl();
        System.out.println(CurrentUrl4);
        Assert.assertTrue(CurrentUrl4.contains("https://www.myntra.com/tshirts"));
    }

    @Then("All filter tag are present on url")
    public void all_filter_tag_are_present_on_url() {
        String filtertag = driver.findElement(myntraLocators.filtertag).getText();
        Assert.assertTrue(filtertag.contains("FILTER"));
        List<WebElement> filtertagelementlist = driver.findElements(myntraLocators.filtertagelement);
        if (filtertagelementlist.size() > 0) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
    }

    @Then("Validate data under categories")
    public void validate_data_under_categories() throws InterruptedException {
        List<WebElement> categorieslist = driver.findElements(myntraLocators.categorytag);
        if (categorieslist.size() > 0) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
    }

    @Then("Validate data under Brand")
    public void validateDataUnderBrand() {
        List<WebElement> brandlist = driver.findElements(myntraLocators.brandtag);
        System.out.println(brandlist.size()); //8
        if (brandlist.size() > 0) {
            Assert.assertTrue(true);
        } else {
            Assert.assertFalse(false);
        }
        driver.findElement(myntraLocators.brandmoretag).click();
        driver.findElement(myntraLocators.brandmorepage).isDisplayed();
        List<WebElement> allbrandlist = driver.findElements(myntraLocators.brandmorepagetag);
        System.out.println(allbrandlist.size()); //604
        if (allbrandlist.size() > brandlist.size()) {
            Assert.assertTrue(true);
        } else {
            Assert.assertFalse(false);
        }
    }

    @Then("Validate data under price")
    public void validateDataUnderPrice() {
        List<WebElement> pricelist = driver.findElements(myntraLocators.pricetag);
        if (pricelist.size() > 0) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
    }

    @Then("Validate data under colour")
    public void validateDataUnderColour() {
        List<WebElement> colorlist = driver.findElements(myntraLocators.colortag);
        System.out.println(colorlist.size()); //7
        if (colorlist.size() > 0) {
            Assert.assertTrue(true);
        } else {
            Assert.assertFalse(false);
        }
        driver.findElement(myntraLocators.colormoretag).click();
        List<WebElement> allcolorlist = driver.findElements(myntraLocators.colortag);
        System.out.println(allcolorlist.size()); //44
        if (allcolorlist.size() > colorlist.size()) {
            Assert.assertTrue(true);
        } else {
            Assert.assertFalse(false);
        }
    }

    @And("Validate data under discount range")
    public void validateDataUnderDiscountRange() {
        List<WebElement> discountrangelist = driver.findElements(myntraLocators.discountrangetag);
        if (discountrangelist.size() > 0) {
            Assert.assertTrue(true);
        } else {
            Assert.assertFalse(false);
        }
    }

    @Then("validate the navigated page elements")
    public void validate_The_NavigatedPageElements() throws InterruptedException {
        driver.findElement(By.xpath("//div[@class='sort-sortBy']")).isDisplayed();
        List<WebElement> sorttablist = driver.findElements(By.xpath("//label[@class='sort-label ']"));
        if (sorttablist.size() > 0) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
        driver.close();
    }

    @Given("^Hover on Men \\\"(.*)\\\" and Validate the background page and click on \\\"(.*)\\\"$")
    public void hover_on_men_and_validate_the_background_page(String men, String topwear) {
        Men = "//div[@class='desktop-navContent']/div/a[contains(text(), '%s')]";
        ac = new Actions(driver);
        ac.moveToElement(driver.findElement(By.xpath(String.format(Men, men)))).perform();
        Boolean desktopcpntainer = driver.findElement(myntraLocators.desktopcontainermen).isDisplayed();
        Topwear = "//div[@class='desktop-categoryContainer']/li/ul/li/a[contains(text(), '%s')]";
        driver.findElement(By.xpath(String.format(Topwear, topwear))).click();
        String Currenturlmen = driver.getCurrentUrl();
        Assert.assertTrue(Currenturlmen.contains("https://www.myntra.com/men-topwear"));
    }

    @Then("Validate the topwear page is open")
    public void validateTheTopwearPageIsOpen() {
        String Mentopweartext = driver.findElement(myntraLocators.topwearpagevalidate).getText();
        if (Mentopweartext.equals("Men Topwear")) {
            Assert.assertTrue(true);
        } else {
            Assert.assertFalse(false);
        }
        String filtertag = driver.findElement(myntraLocators.filtertag).getText();
        Assert.assertTrue(filtertag.contains("FILTER"));
    }

    @Then("Validate the images are visible on screen")
    public void validate_the_images_are_visible_on_screen() {
        List<WebElement> topwearimgvalidation = driver.findElements(myntraLocators.homepageimg);
        System.out.println(topwearimgvalidation.size());
        if (topwearimgvalidation.size() > 0) {
            Assert.assertTrue(true);
        } else {
            Assert.assertFalse(false);
        }
        List<WebElement> homepagebrandname = driver.findElements(myntraLocators.homepagebrandname);
        System.out.println(homepagebrandname.size());
        for (int i = 0; i < homepagebrandname.size(); i++) {
            String homepagebrandnamelisttext = homepagebrandname.get(i).getText();
            System.out.println(homepagebrandnamelisttext);
        }
    }

    @Then("Check brand button search functionality")
    public void checkBrandButtonSearchFunctionality() throws InterruptedException {
        driver.findElement(myntraLocators.brandmoretag).click();
        Boolean brandmorepageopenvalidation = driver.findElement(myntraLocators.brandmorepageopen).isDisplayed();
        Boolean searchbrandvalidation = driver.findElement(myntraLocators.searchbrandtag).isDisplayed();
        List<WebElement> searchtabtextlist1 = driver.findElements(myntraLocators.brandtextinsearchtab);
        driver.findElement(myntraLocators.searchbrandtag).sendKeys("Nike");
        List<WebElement> searchtabtextlist2 = driver.findElements(myntraLocators.brandtextinsearchtab);
        if (searchtabtextlist1.size() > searchtabtextlist2.size()) {
            Assert.assertTrue(true);
        } else {
            Assert.assertFalse(false);
        }
        WebElement checkboxelement = driver.findElement(myntraLocators.brandcheckbox);
        checkboxelement.click();
        driver.findElement(myntraLocators.brandpageclosebutton).click();
        Thread.sleep(2000);
        List<WebElement> homepagebrandname1 = driver.findElements(myntraLocators.homepagebrandname);
        System.out.println(homepagebrandname1.size());
        for (int i = 0; i < homepagebrandname1.size(); i++) {
            String homepagebrandnamelisttext1 = homepagebrandname1.get(i).getText();
            if (homepagebrandnamelisttext1.contains("Nike")) {
                Assert.assertTrue(true);
            }
            else {
                Assert.assertFalse(false);
            }
        }
    }

    @Then("Go to the end of page and validate the number of pages and available buttons")
    public void goToTheEndOfPageAndValidateTheNumberOfPagesAndAvailableButton() throws InterruptedException {
        String pagecounttext = driver.findElement(myntraLocators.pagecounttag).getText();
        Boolean activepagevalidation = driver.findElement(myntraLocators.activepage).isDisplayed();
        Boolean nextbuttonvalidation = driver.findElement(myntraLocators.nextbutton).isDisplayed();
        String[] pagecounttextParts = pagecounttext.split(" ");
        int PageCount1 = Integer.parseInt(pagecounttextParts[3]);
        String pagecount2 = driver.findElement(myntraLocators.lastvalueofpagecount).getText();
        int PageCount2 = Integer.parseInt(pagecount2);
        if(PageCount2==PageCount1) {
            Assert.assertTrue(true);
        }
        else {
            Assert.assertFalse(false);
        }
        driver.findElement(myntraLocators.lastvalueofpagecount).click();
        Thread.sleep(30000);
        driver.findElement(myntraLocators.previousbutton).isDisplayed();
    }

    @Given("Click on image and validate if it opens in new window")
    public void clickOnImageAndValidateIfItOpensInNewWindow() throws InterruptedException {
        clickOnSearchButtonAndValidateTheUrl();
        Thread.sleep(1000);
//        WebElement click = driver.findElement(By.xpath(String.format(myntraLocators.firstimg)));
        driver.findElement(myntraLocators.firstimgclick).click();
        handles = new ArrayList<String>(driver.getWindowHandles());
        if (handles.size() == 2) {
            Assert.assertTrue(true);
        } else {
            Assert.assertFalse(false);
        }
    }

    @Then("Validate the URL and text on the window")
    public void validateTheURLAndTextOnTheWindow() {
        WebElement firstimgtextoldwindown = driver.findElement(myntraLocators.firstimgtext(1));
        WebElement discountpriceoldwindow = driver.findElement(myntraLocators.discuntoldwindown);
        driver.switchTo().window(handles.get(1));
        String CurrentUrlnewtab = driver.getCurrentUrl();
        Assert.assertTrue(CurrentUrlnewtab.contains("https://www.myntra.com/tshirts/"));
        WebElement firrstimgtextnewwindow = driver.findElement(myntraLocators.firstimgnewwindow);
        WebElement discountpricenewwindow = driver.findElement(myntraLocators.discountnewwindow);
        if (firstimgtextoldwindown == firrstimgtextnewwindow && discountpriceoldwindow == discountpricenewwindow){
            Assert.assertTrue(true);
        }
        else {
            Assert.assertFalse(false);
        }

    }

    @And("Validate a click on ADD TO BAG, it should ask for size")
    public void validateAClickOnADDTOBAGItShouldAskForSize() {
        driver.findElement(myntraLocators.addtobag).click();
        driver.findElement(myntraLocators.addtobagerror).isDisplayed();
        List<WebElement> sizeelements = driver.findElements(myntraLocators.sizebutton);
        for (WebElement element : sizeelements) {
            size = element.getText();
            System.out.println(size);
            if (sizeelements.size() > 0) {
                System.out.println("Size elements are present");
            }
            if (size.contains("XS") || size.contains("S")  || size.contains("M") || size.contains("L")  || size.contains("XL")){
                Assert.assertTrue(true);
            }
                else {
                Assert.assertFalse("Size not present", false);
            }
        }
    }

    @Then("^Select the size \\\"(.*)\\\" and click on ADD TO BAG and validate item added to bag$")
    public void selectTheSizeAndClickOnADDTOBAGAndValidateItemAddedToBag(String M) {
        WebElement addtobagtextpopup1 = driver.findElement(myntraLocators.addtobagtextpopup);
        System.out.println(addtobagtextpopup1.getText());
        if (addtobagtextpopup1.isDisplayed()){
            Assert.assertTrue("Element found", true);
        }
        else {
            Assert.assertFalse("Element not found", false);
        }
        String xssizebutton = "//p[text()= '%s']";
        driver.findElement(By.xpath(String.format(xssizebutton, M))).click();
        driver.findElement(myntraLocators.addtobag).click();
        WebElement addtobagpopupwindow = driver.findElement(myntraLocators.addtobagpopup);
        Boolean addtobagpopupwindownvalidation = addtobagpopupwindow.isDisplayed();
        addtobagtextpopup2 = driver.findElement(myntraLocators.addtobagtextpopup);
        if (addtobagtextpopup2.isDisplayed()){
            Assert.assertTrue("Element found", true);
        }
        else {
            Assert.assertFalse("Element not found", false);
        }
        driver.findElement(myntraLocators.deliverytextbox).sendKeys("204101");
        driver.findElement(myntraLocators.deliverycheckbutton).click();
        Boolean deliverygreenbuttonvalidation = driver.findElement(myntraLocators.deliverygreencheck).isDisplayed();
        Boolean deliverychangebuttonvalidation = driver.findElement(myntraLocators.deliverychangebutton).isDisplayed();
        driver.findElement(myntraLocators.deliverychangebutton).click();
        Boolean deliverycheckbuttonvalidation = driver.findElement(myntraLocators.deliverycheckbutton).isDisplayed();
        Boolean gotobagicon = driver.findElement(myntraLocators.gotobag).isDisplayed();
    }

    @And("Validate checkout page")
    public void validateCheckoutPage() {
        driver.findElement(myntraLocators.bagicon).click();
        driver.findElement(myntraLocators.checkoutfullpage).click();
        String addtobagtexturl = driver.getCurrentUrl();
        Assert.assertTrue(addtobagtexturl.contains("checkout"));
        Boolean itemaddedconfirmation1 = driver.findElement(myntraLocators.addeditemconfirmation).isDisplayed();
        String secureelement = driver.findElement(myntraLocators.secureelement).getText();
        Boolean securevalidation = secureelement.contains("100%");
        Boolean movetowishlistbuttonconfirm1 = driver.findElement(myntraLocators.movetowishlistbutton).isDisplayed();
        driver.findElement(myntraLocators.placeorderbutton).click();
        String placeorderbuttonclickurl = driver.getCurrentUrl();
        Boolean placeorderbuttonvalidation = placeorderbuttonclickurl.contains("login");
        driver.navigate().back();
    }

    @Then("Validate Remove and wishlist functionality")
    public void validateRemoveAndWishlistFunctionality() {
        driver.findElement(myntraLocators.removebutton1).click();
        driver.findElement(myntraLocators.removebuttonocnfirmationpage).isDisplayed();
        driver.findElement(myntraLocators.removebutton2).click();
        Boolean itemremovedconfrimation = driver.findElement(myntraLocators.addeditemconfirmation).isDisplayed();
        Boolean movetowishlistbuttonremoved = driver.findElement(myntraLocators.movetowishlistbutton).isDisplayed();
        driver.findElement(myntraLocators.additemfromwishlist).click();
        String wishlisturl = driver.getCurrentUrl();
        Assert.assertTrue(wishlisturl.contains("wishlist"));
        Boolean loginbuttononwishlistscreen = driver.findElement(myntraLocators.wishlistscreenloginbutton).isDisplayed();
        driver.findElement(myntraLocators.wishlistscreenloginbutton).click();
        String loginurl = driver.getCurrentUrl();
        Assert.assertTrue(loginurl.contains("https://www.myntra.com/login?referer=https://www.myntra.com/wishlist"));
        driver.quit();
    }
}