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
import org.openqa.selenium.devtools.v85.network.model.WebSocketHandshakeResponseReceived;
import org.openqa.selenium.interactions.Actions;

import java.io.StringReader;
import java.security.Key;
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
    static ArrayList<String> handles;
    static Actions ac;
    static String tempFilterTagText;
    WebElement clearButtonDisplay;

    @Given("I have opened my web browser")
    public void i_have_opened_my_web_browser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\swati.sharma\\IdeaProjects\\Myntra\\src\\test\\chrome\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Then("I navigate to Myntra")
    public void i_navigate_to_myntra() {
        driver.get("https://www.myntra.com/");
    }

    @Then("Validate if the myntra page opened")
    public void validate_if_the_myntra_page_opened() {
        WebElement profileIcon = driver.findElement(myntraLocators.profileIcon);
        WebElement myntraIcon = driver.findElement(myntraLocators.myntraIcon);
        if (!profileIcon.isDisplayed() && !myntraIcon.isDisplayed()){
            Assert.assertFalse("Elements not found", profileIcon.isDisplayed() && myntraIcon.isDisplayed());
        }
        else {Assert.assertTrue("Element found", true);}
        profileIcon.click();
    }

    @Given("I have clicked on Signup or login button under profile")
    public void iHaveClickedOnSignupOrLoginButtonUnderProfile() {
        currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("https://www.myntra.com/"));
        ac = new Actions(driver);
        ac.moveToElement(driver.findElement(myntraLocators.profileIcon)).perform();
        driver.findElement(myntraLocators.loginOption).click();
    }

    @Then("Validate the UI is redirected to login or singup page")
    public void validateTheUIIsRedirectedToLoginOrSingupPage() {
        String currentUrl1 = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl1.contains("https://www.myntra.com/login?referer=https://www.myntra.com/"));
    }

    @And("User should not able to proceed with invalid mobile number")
    public void userShouldNotAbleToProceedWithInvalidMobileNumber() {;
        WebElement mobileNumberField = driver.findElement(myntraLocators.mobileNumberField);
        mobileNumberField.click();
        mobileNumberField.sendKeys("123456");
        driver.findElement(myntraLocators.continueButton).click();
        String invalidMobileNoErrorText = driver.findElement(myntraLocators.invalidMobileNoError).getText();
        if (invalidMobileNoErrorText.contains("Please enter a valid mobile number")){
            Assert.assertTrue("Validation Pass", true);
        }
        else {
            Assert.fail("Validation Fail");
        }
    }

    @Then("User to validate the termsofuse and privacypolicy link")
    public void userToValidateTheTermsofuseAndPrivacypolicyLink() {
        driver.findElement(myntraLocators.termsOfUse).click();
        String currentUrl2 = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl2.contains("https://www.myntra.com/termsofuse"));
        driver.navigate().back();
        driver.close();
    }

    @Given("Validate if desktop tag are present")
    public void validateIfDesktopTagArePresent() {
        List<WebElement> mainPageElements1List = driver.findElements(myntraLocators.mainPageElements1);
        boolean menFound = false;
        boolean womenFound = false;
        boolean kidsFound = false;
        boolean homeAndLivingFound = false;
        boolean beautyFound = false;
        boolean studioFound = false;
        for (WebElement element1 : mainPageElements1List) {
            String mainPageElement1Text = element1.getText();
            if (mainPageElement1Text.contains("MEN")) {
                menFound = true;
            }
            if (mainPageElement1Text.contains("WOMEN")) {
                womenFound = true;
            }
            if (mainPageElement1Text.contains("KIDS")) {
                kidsFound = true;
            } if (mainPageElement1Text.contains("BEAUTY")) {
                beautyFound = true;
            } if (mainPageElement1Text.contains("STUDIO")) {
                studioFound = true;
            } if (mainPageElement1Text.contains("HOME & LIVING")) {
                homeAndLivingFound = true;
            }
        }
        if (menFound && womenFound && kidsFound && beautyFound && homeAndLivingFound && studioFound) {
            Assert.assertTrue("All elements found", true);
        } else {
            Assert.fail("Elements not found");
        }
        List<WebElement> mainPageElements2List = driver.findElements(myntraLocators.mainPageElements2);
        boolean profileFound = false;
        boolean wishListFound = false;
        boolean bagFound = false;
        for (WebElement element2 : mainPageElements2List) {
            String mainPageElement2Text = element2.getText();
            if (mainPageElement2Text.contains("Profile")) {
                profileFound = true;
            }
            if (mainPageElement2Text.contains("Bag")) {
                 bagFound = true;
            }
            if (mainPageElement2Text.contains("Wishlist")) {
                wishListFound = true;
            }
        }
        if (profileFound && bagFound && wishListFound) {
            Assert.assertTrue("All elements found", true);
        } else {
            Assert.fail("Elements not found");
        }
    }

    @Then("click on search button and validate the url")
    public void clickOnSearchButtonAndValidateTheUrl() {
        WebElement search = driver.findElement(myntraLocators.searchTab);
        search.click();
        String tshirt = "Tshirts for women";
        search.sendKeys(tshirt, Keys.ENTER);
        String CurrentUrl3 = driver.getCurrentUrl();
        System.out.println(CurrentUrl3);
        Assert.assertTrue(CurrentUrl3.contains("https://www.myntra.com/tshirts"));
    }

    @Then("All filter tag are present on url")
    public void all_filter_tag_are_present_on_url() {
        String filterTag = driver.findElement(myntraLocators.filterTag).getText();
        Assert.assertTrue(filterTag.contains("FILTER"));
        List<WebElement> filterTagElementList = driver.findElements(myntraLocators.filterTagElement);
        boolean categoriesFound = false;
        boolean brandFound = false;
        boolean priceFound = false;
        boolean colorFound = false;
        boolean discountRangeFound = false;
        for (WebElement filterElement : filterTagElementList) {
            String filterElementText = filterElement.getText();
            if (filterElementText.contains("CATEGORIES")) {
                categoriesFound = true;
            }
            if (filterElementText.contains("BRAND")) {
                brandFound = true;
            }
            if (filterElementText.contains("PRICE")) {
                priceFound = true;
            }
            if (filterElementText.contains("COLOR")) {
                colorFound = true;
            }
            if (filterElementText.contains("DISCOUNT RANGE")) {
                discountRangeFound = true;
            }
        }
        if (categoriesFound && brandFound && priceFound && colorFound && discountRangeFound) {
            Assert.assertTrue("All elements found", true);
        } else {
            Assert.fail("Elements not found");
        }
    }

    @Then("Validate data under categories")
    public void validate_data_under_categories() {
        List<WebElement> categoriesList = driver.findElements(myntraLocators.categoryTag);
        if (categoriesList.size() > 0) {
            Assert.assertNotNull(categoriesList);
        } else {
            Assert.fail("Validation Failed");
        }
        driver.findElement(myntraLocators.tshirtsFilter).click();
        tempFilterTagText = driver.findElement(myntraLocators.tempFilterTag).getText();
        if (tempFilterTagText.equals("Tshirts")){
            Assert.assertTrue("box is checked", true);
        }
        clearButtonDisplay = driver.findElement(myntraLocators.clearAllButton);
        if (clearButtonDisplay.isDisplayed()){
            driver.findElement(myntraLocators.clearAllButton).click();
            }
        else {Assert.fail("clear button not displayed");}
    }

    @Then("Validate data under Brand")
    public void validateDataUnderBrand() {
        List<WebElement> brandList = driver.findElements(myntraLocators.brandTag);
        Assert.assertNotNull("List is not null", brandList);
        driver.findElement(myntraLocators.brandMoreTag).click();
        driver.findElement(myntraLocators.brandMorePage).isDisplayed();
        List<WebElement> allBrandList = driver.findElements(myntraLocators.brandMorePageTag);
        if (allBrandList.size() > brandList.size()) {
            Assert.assertTrue("Validation Pass", true);
        } else {
            Assert.fail("Validation Fail");
        }
        driver.findElement(myntraLocators.brandPageCloseButton).click();
        driver.findElement(myntraLocators.brandFabfleeFilter).click();
        WebElement brandNameValidation = driver.findElement(myntraLocators.homePageBrandName);
        if (brandNameValidation.getText().contains("Fabflee")){
            Assert.assertTrue("brand name display", true);
        }
        if (tempFilterTagText.contains("Fabflee")){
            Assert.assertTrue("Filter applied", true);
        }
        driver.findElement(myntraLocators.brandFabfleeFilter).click();
    }

    @Then("Validate data under colour")
    public void validateDataUnderColour() {
        List<WebElement> colorList = driver.findElements(myntraLocators.colorTag);
        if (colorList.size() > 0) {
            Assert.assertTrue("Validation Pass", true);
        } else {
            Assert.fail("Validation Fail");
        }
        driver.findElement(myntraLocators.colorMoreTag).click();
        List<WebElement> allColorList = driver.findElements(myntraLocators.colorTag);
        if (allColorList.size() > colorList.size()) {
            Assert.assertTrue("Validation Pass", true);
        } else {
            Assert.fail("Validation Fail");
        }
        driver.findElement(myntraLocators.colorFilter).click();
        String tempColorTagText = driver.findElement(myntraLocators.tempFilterTag).getText();
        if (tempColorTagText.contains("Black")){
            Assert.assertTrue("box is checked", true);
        }
        driver.findElement(myntraLocators.clearAllButton).click();
    }

    @And("Validate data under discount range and price")
    public void validateDataUnderDiscountRangeAndPrice() throws InterruptedException {
        List<WebElement> discountRangeList = driver.findElements(myntraLocators.discountRangeTag);
        if (discountRangeList.size() > 0) {
            Assert.assertTrue("Validation Pass", true);
        } else {
            Assert.fail("Validation Fail");
        }
        ac = new Actions(driver);
        ac.sendKeys(Keys.PAGE_DOWN).build().perform();
        Thread.sleep(1000);
        driver.findElement(myntraLocators.discountFilter).click();
        Thread.sleep(1000);
        String tempDiscountTagText = driver.findElement(myntraLocators.tempFilterTag).getText();
        if (tempDiscountTagText.contains("10%")){
            Assert.assertTrue("box is checked", true);
        }
        Thread.sleep(1000);
        driver.findElement(myntraLocators.clearAllButton).click();
        List<WebElement> priceList = driver.findElements(myntraLocators.priceTag);
        if (priceList.size() > 0) {
            Assert.assertTrue("Validation Pass", true);
        } else {
            Assert.fail("Validation Fail");
        }
        driver.findElement(myntraLocators.priceFilter).click();
        String tempPriceTagText = driver.findElement(myntraLocators.tempFilterTag).getText();
        if (tempDiscountTagText.contains("Rs.")){
            Assert.assertTrue("box is checked", true);
        }
        driver.findElement(myntraLocators.clearAllButton).click();
    }

    @Then("validate the navigated page elements")
    public void validate_The_NavigatedPageElements() {
        List<WebElement> sortTabList = driver.findElements(By.xpath("//label[@class='sort-label ']"));
        if (sortTabList.size() > 0) {
            Assert.assertTrue("Validation Pass", true);
        } else {
            Assert.fail("Validation Fail");
        }
        driver.close();
    }

    @Given("^Hover on Men \\\"(.*)\\\" and Validate the background page and click on \\\"(.*)\\\"$")
    public void hover_on_men_and_validate_the_background_page(String Men, String Topwear) {
        String men = "//div[@class='desktop-navContent']/div/a[contains(text(), '%s')]";
        ac = new Actions(driver);
        ac.moveToElement(driver.findElement(By.xpath(String.format(men, Men)))).perform();
        Boolean desktopContainer = driver.findElement(myntraLocators.desktopContainerMen).isDisplayed();
        String topWear = "//div[@class='desktop-categoryContainer']/li/ul/li/a[contains(text(), '%s')]";
        driver.findElement(By.xpath(String.format(topWear, Topwear))).click();
        String currentUrlMen = driver.getCurrentUrl();
        Assert.assertTrue(currentUrlMen.contains("https://www.myntra.com/men-topwear"));
    }

    @Then("Validate the topwear page is open")
    public void validateTheTopwearPageIsOpen() {
        String menTopWearText = driver.findElement(myntraLocators.topwearPageValidate).getText();
        if (menTopWearText.equals("Men Topwear")) {
            Assert.assertTrue("Validation Pass", true);
        } else {
            Assert.fail("Validation Fail");
        }
        String filterTag = driver.findElement(myntraLocators.filterTag).getText();
        Assert.assertTrue(filterTag.contains("FILTER"));
    }

    @Then("Validate the images are visible on screen")
    public void validate_the_images_are_visible_on_screen() {
        List<WebElement> topWearImgValidation = driver.findElements(myntraLocators.homePageImg);
        if (topWearImgValidation.size() > 0) {
            Assert.assertTrue("Validation Pass", true);
        } else {
            Assert.fail("Validation Fail");
        }
        List<WebElement> homePageBrandName = driver.findElements(myntraLocators.homePageBrandName);
        for (int i = 0; i < homePageBrandName.size(); i++) {
            String homePageBrandNameListText = homePageBrandName.get(i).getText();
            System.out.println(homePageBrandNameListText);
        }
    }

    @Then("Check brand button search functionality")
    public void checkBrandButtonSearchFunctionality() throws InterruptedException {
        driver.findElement(myntraLocators.brandMoreTag).click();
        Boolean brandMorePageOpenValidation = driver.findElement(myntraLocators.brandMorePageOpen).isDisplayed();
        Boolean searchBrandValidation = driver.findElement(myntraLocators.searchBrandTag).isDisplayed();
        List<WebElement> searchTabTextList1 = driver.findElements(myntraLocators.brandTextInSearchTab);
        driver.findElement(myntraLocators.searchBrandTag).sendKeys("Nike");
        List<WebElement> searchTabTextList2 = driver.findElements(myntraLocators.brandTextInSearchTab);
        if (searchTabTextList1.size() > searchTabTextList2.size()) {
            Assert.assertTrue("Validation Pass", true);
        } else {
            Assert.fail("Validation Fail");
        }
        WebElement checkBoxElement = driver.findElement(myntraLocators.brandCheckBox);
        checkBoxElement.click();
        driver.findElement(myntraLocators.brandPageCloseButton).click();
        Thread.sleep(2000);
        List<WebElement> homePageBrandName1 = driver.findElements(myntraLocators.homePageBrandName);
        System.out.println(homePageBrandName1.size());
        for (int i = 0; i < homePageBrandName1.size(); i++) {
            String homePageBrandNameListText1 = homePageBrandName1.get(i).getText();
            if (homePageBrandNameListText1.contains("Nike")) {
                Assert.assertTrue("Validation Pass", true);
            } else {
                Assert.fail("Validation Fail");
            }
        }
    }

    @Then("Go to the end of page and validate the number of pages and available buttons")
    public void goToTheEndOfPageAndValidateTheNumberOfPagesAndAvailableButton() throws InterruptedException {
        String pageCountText = driver.findElement(myntraLocators.pageCountTag).getText();
        Boolean activePageValidation = driver.findElement(myntraLocators.activePage).isDisplayed();
        Boolean nextButtonValidation = driver.findElement(myntraLocators.nextButton).isDisplayed();
        String[] pageCountTextParts = pageCountText.split(" ");
        int PageCount1 = Integer.parseInt(pageCountTextParts[3]);
        String pageCount2 = driver.findElement(myntraLocators.lastValueOfPageCount).getText();
        int PageCount2 = Integer.parseInt(pageCount2);
        if(PageCount2==PageCount1) {
            Assert.assertTrue("Validation Pass", true);
        } else {
            Assert.fail("Validation Fail");
        }
        driver.findElement(myntraLocators.lastValueOfPageCount).click();
        Thread.sleep(30000);
        driver.findElement(myntraLocators.previousButton).isDisplayed();
        driver.quit();
    }

    @Given("Click on image and validate if it opens in new window")
    public void clickOnImageAndValidateIfItOpensInNewWindow() throws InterruptedException {
        clickOnSearchButtonAndValidateTheUrl();
        Thread.sleep(1000);
//        WebElement click = driver.findElement(By.xpath(String.format(myntraLocators.firstimg)));
        driver.findElement(myntraLocators.firstImgClick).click();
        handles = new ArrayList<String>(driver.getWindowHandles());
        if (handles.size() == 2) {
            Assert.assertTrue("Validation Pass", true);
        } else {
            Assert.fail("Validation Fail");
        }
    }

    @Then("Validate the URL and text on the window")
    public void validateTheURLAndTextOnTheWindow() {
        WebElement firstImgOldWindow = driver.findElement(myntraLocators.firstImgText(1));
        String firstImgOldWindowText = firstImgOldWindow.getText();
        WebElement discountPriceOldWindow = driver.findElement(myntraLocators.discountOldWindow);
        String discountPriceOldWindowText = discountPriceOldWindow.getText();
        driver.switchTo().window(handles.get(1));
        String currentUrlNewTab = driver.getCurrentUrl();
        Assert.assertTrue(currentUrlNewTab.contains("https://www.myntra.com/tshirts/"));
        WebElement firstImgNewWindow = driver.findElement(myntraLocators.firstImgNewWindow);
        String firstImgNewWindowText = firstImgNewWindow.getText();
        WebElement discountPriceNewWindow = driver.findElement(myntraLocators.discountNewWindow);
        String discountPriceNewWindowText = discountPriceNewWindow.getText();
        if (firstImgOldWindowText.equals(firstImgNewWindowText) && discountPriceOldWindowText.equals(discountPriceNewWindowText)){
            Assert.assertTrue("Validation Pass", true);
        } else {
            Assert.fail("Validation Fail");
        }

    }

    @And("Validate a click on ADD TO BAG, it should ask for size")
    public void validateAClickOnADDTOBAGItShouldAskForSize() {
        driver.findElement(myntraLocators.addToBag).click();
        driver.findElement(myntraLocators.addToBagError).isDisplayed();
        List<WebElement> sizeElements = driver.findElements(myntraLocators.sizeButton);
        for (WebElement element : sizeElements) {
            size = element.getText();
            System.out.println(size);
            if (sizeElements.size() > 0) {
                System.out.println("Size elements are present");
            }
            if (size.contains("XS") || size.contains("S")  || size.contains("M") || size.contains("L")  || size.contains("XL")){
                Assert.assertTrue("Size is present", true);
            }
                else {
                Assert.assertFalse("Size not present", false);
            }
        }
    }

    @Then("^Select the size \\\"(.*)\\\" and click on ADD TO BAG and validate item added to bag$")
    public void selectTheSizeAndClickOnADDTOBAGAndValidateItemAddedToBag(String M) {
        WebElement addToBagTextPopup1 = driver.findElement(myntraLocators.addToBagTextPopup);
        System.out.println(addToBagTextPopup1.getText());
        if (addToBagTextPopup1.isDisplayed()){
            Assert.assertTrue("Element found", true);
            Assert.assertFalse("Element not found", false);
        }
        String xssizebutton = "//p[text()= '%s']";
        driver.findElement(By.xpath(String.format(xssizebutton, M))).click();
        driver.findElement(myntraLocators.addToBag).click();
        WebElement addToBagPopupWindow = driver.findElement(myntraLocators.addToBagPopup);
        Boolean addToBagPopupWindowValidation = addToBagPopupWindow.isDisplayed();
        WebElement addToBagTextPopup2 = driver.findElement(myntraLocators.addToBagTextPopup);
        if (addToBagTextPopup2.isDisplayed()){
            Assert.assertTrue("Element found", true);
        }
        else {
            Assert.assertFalse("Element not found", false);
        }
        driver.findElement(myntraLocators.deliveryTextBox).sendKeys("204101");
        driver.findElement(myntraLocators.deliveryCheckButton).click();
        Boolean deliveryGreenButtonValidation = driver.findElement(myntraLocators.deliveryGreenCheck).isDisplayed();
        Boolean deliveryChangeButtonValidation = driver.findElement(myntraLocators.deliveryChangeButton).isDisplayed();
        driver.findElement(myntraLocators.deliveryChangeButton).click();
        Boolean deliveryCheckButtonValidation = driver.findElement(myntraLocators.deliveryCheckButton).isDisplayed();
        Boolean goToBagIcon = driver.findElement(myntraLocators.goToBag).isDisplayed();
    }

    @And("Validate checkout page")
    public void validateCheckoutPage() {
        driver.findElement(myntraLocators.bagIcon).click();
        driver.findElement(myntraLocators.checkoutFullPage).click();
        String addToBagTextUrl = driver.getCurrentUrl();
        Assert.assertTrue(addToBagTextUrl.contains("checkout"));
        Boolean itemAddedConfirmation1 = driver.findElement(myntraLocators.addedItemConfirmation).isDisplayed();
        String secureElement = driver.findElement(myntraLocators.secureElement).getText();
        Boolean secureValidation = secureElement.contains("100%");
        Boolean moveToWishlistButtonConfirm1 = driver.findElement(myntraLocators.moveToWishlistButton).isDisplayed();
        driver.findElement(myntraLocators.placeOrderButton).click();
        String placeOrderButtonClickUrl = driver.getCurrentUrl();
        Boolean placeOrderButtonValidation = placeOrderButtonClickUrl.contains("login");
        driver.navigate().back();
    }

    @Then("Validate Remove and wishlist functionality")
    public void validateRemoveAndWishlistFunctionality() {
        driver.findElement(myntraLocators.removeButton1).click();
        driver.findElement(myntraLocators.removeButtonCnfirmationPage).isDisplayed();
        driver.findElement(myntraLocators.removeButton2).click();
        Boolean itemRemovedConfirmation = driver.findElement(myntraLocators.addedItemConfirmation).isDisplayed();
        Boolean moveToWishListButtonRemoved = driver.findElement(myntraLocators.moveToWishlistButton).isDisplayed();
        driver.findElement(myntraLocators.additemFromWishlist).click();
        String wishlistUrl = driver.getCurrentUrl();
        Assert.assertTrue(wishlistUrl.contains("wishlist"));
        Boolean loginButtonOnWishListScreen = driver.findElement(myntraLocators.wishlistScreenLoginButton).isDisplayed();
        driver.findElement(myntraLocators.wishlistScreenLoginButton).click();
        String loginUrl = driver.getCurrentUrl();
        Assert.assertTrue(loginUrl.contains("https://www.myntra.com/login?referer=https://www.myntra.com/wishlist"));
        driver.quit();
    }
}