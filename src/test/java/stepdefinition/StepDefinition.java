package stepdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            Assert.assertTrue("Validation Pass", invalidMobileNoErrorText.contains("Please enter a valid mobile number"));
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
        List<String> mainPageElement1Text = new ArrayList<>();
        for (WebElement element : mainPageElements1List) {
            mainPageElement1Text.add(element.getText());
        }
        List<String> manualList1 = Arrays.asList("MEN", "WOMEN", "KIDS", "HOME & LIVING", "BEAUTY", "STUDIO");
        Assert.assertEquals("List are equal", mainPageElement1Text, manualList1);
        List<WebElement> mainPageElements2List = driver.findElements(myntraLocators.mainPageElements2);
        List<String> mainPageElement2Text = new ArrayList<>();
        for (WebElement element : mainPageElements2List) {
            mainPageElement2Text.add(element.getText());
        }
        List<String> manualList2 = Arrays.asList("Profile", "Wishlist", "Bag");
        if (mainPageElement2Text.equals(manualList2)){
            System.out.println("The two lists are equal.");
        } else {
            System.out.println("The two lists are not equal.");
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
        List<String> filterTagElement1Text = new ArrayList<>();
        for (WebElement element : filterTagElementList) {
            filterTagElement1Text.add(element.getText());
        }
        List<String> manualList3 = Arrays.asList("CATEGORIES", "BRAND", "PRICE", "COLOR", "DISCOUNT RANGE");
        Assert.assertEquals("List are equal", filterTagElement1Text, manualList3);
    }

    @Then("Validate data under categories")
    public void validate_data_under_categories() {
        List<WebElement> categoriesList = driver.findElements(myntraLocators.categoryTag);
            Assert.assertNotNull(categoriesList);
        driver.findElement(myntraLocators.tshirtsFilter).click();
        tempFilterTagText = driver.findElement(myntraLocators.tempFilterTag).getText();
            Assert.assertEquals(tempFilterTagText, "Tshirts");
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
        Assert.assertTrue("Validation Pass", colorList.size() > 0);
        driver.findElement(myntraLocators.colorMoreTag).click();
        List<WebElement> allColorList = driver.findElements(myntraLocators.colorTag);
        Assert.assertTrue("Validation Pass", allColorList.size() > colorList.size());
        driver.findElement(myntraLocators.colorFilter).click();
        String tempColorTagText = driver.findElement(myntraLocators.tempFilterTag).getText();
        Assert.assertTrue("box is checked", tempColorTagText.contains("Black"));
    }

    @And("Validate data under discount range and price")
    public void validateDataUnderDiscountRangeAndPrice() throws InterruptedException {
        List<WebElement> discountRangeList = driver.findElements(myntraLocators.discountRangeTag);
            Assert.assertTrue("Validation Pass", discountRangeList.size() > 0);
        ac = new Actions(driver);
        ac.sendKeys(Keys.PAGE_DOWN).build().perform();
        Thread.sleep(1000);
        driver.findElement(myntraLocators.clearAllButton).click();
        driver.findElement(myntraLocators.discountFilter).click();
        String tempDiscountTagText = driver.findElement(myntraLocators.tempFilterTag).getText();
        Assert.assertTrue("box is checked", tempDiscountTagText.contains("10 % And Above"));
        Thread.sleep(1000);
        driver.findElement(myntraLocators.clearAllButton).click();
        List<WebElement> priceList = driver.findElements(myntraLocators.priceTag);
        Assert.assertTrue("Validation Pass", priceList.size()>0);
        driver.findElement(myntraLocators.priceFilter).click();
        String tempPriceTagText = driver.findElement(myntraLocators.tempFilterTag).getText();
        Assert.assertTrue("box is checked", tempPriceTagText.contains("Rs."));
        driver.findElement(myntraLocators.clearAllButton).click();
    }

    @Then("validate the navigated page elements")
    public void validate_The_NavigatedPageElements() {
        List<WebElement> sortTabList = driver.findElements(By.xpath("//label[@class='sort-label ']"));
        Assert.assertTrue("Validation Pass", sortTabList.size() > 0);
        driver.close();
    }

    @Given("^Hover on Men \\\"(.*)\\\" and Validate the background page and click on \\\"(.*)\\\"$")
    public void hover_on_men_and_validate_the_background_page(String Men, String Topwear) {
        String men = "//div[@class='desktop-navContent']/div/a[contains(text(), '%s')]";
        ac = new Actions(driver);
        ac.moveToElement(driver.findElement(By.xpath(String.format(men, Men)))).perform();
        driver.findElement(myntraLocators.desktopContainerMen).isDisplayed();
        String topWear = "//div[@class='desktop-categoryContainer']/li/ul/li/a[contains(text(), '%s')]";
        driver.findElement(By.xpath(String.format(topWear, Topwear))).click();
        String currentUrlMen = driver.getCurrentUrl();
        Assert.assertTrue(currentUrlMen.contains("https://www.myntra.com/men-topwear"));
    }

    @Then("Validate the topwear page is open")
    public void validateTheTopwearPageIsOpen() {
        String menWearPageText = driver.findElement(myntraLocators.menWearPageText).getText();
        String[] pageElements = menWearPageText.split("\n");
        String menTopwear = pageElements[2];
        Assert.assertEquals("Validation Pass", "Men Topwear", menTopwear);
        String filterTag = driver.findElement(myntraLocators.filterTag).getText();
        Assert.assertTrue(filterTag.contains("FILTER"));
    }

    @Then("Validate the images are visible on screen")
    public void validate_the_images_are_visible_on_screen() {
        List<WebElement> topWearImgValidation = driver.findElements(myntraLocators.homePageImg);
        Assert.assertTrue("Validation Pass", topWearImgValidation.size() > 0);
        List<WebElement> homePageBrandName = driver.findElements(myntraLocators.homePageBrandName);
        for (int i = 0; i < homePageBrandName.size(); i++) {
            String homePageBrandNameListText = homePageBrandName.get(i).getText();
            if (homePageBrandName.isEmpty()) {
                System.out.println("No Brand Names");
            }
            if (homePageBrandNameListText.matches("[a-zA-Z]+")) { //Matches with (a-z or A-Z)
                System.out.println("Data present" + homePageBrandNameListText);
            }
            if(homePageBrandNameListText.matches("[0-9]+")) {
                System.out.println("No brand available");
            }
        }
    }

    @Then("Check brand button search functionality")
    public void checkBrandButtonSearchFunctionality() throws InterruptedException {
        driver.findElement(myntraLocators.brandMoreTag).click();
        driver.findElement(myntraLocators.brandMorePageOpen).isDisplayed();
        driver.findElement(myntraLocators.searchBrandTag).isDisplayed();
        List<WebElement> searchTabTextList1 = driver.findElements(myntraLocators.brandTextInSearchTab);
        driver.findElement(myntraLocators.searchBrandTag).sendKeys("Nike");
        List<WebElement> searchTabTextList2 = driver.findElements(myntraLocators.brandTextInSearchTab);
        Assert.assertTrue("Validation Pass", searchTabTextList1.size() > searchTabTextList2.size());
        WebElement checkBoxElement = driver.findElement(myntraLocators.brandCheckBox);
        checkBoxElement.click();
        driver.findElement(myntraLocators.brandPageCloseButton).click();
        Thread.sleep(2000);
        List<WebElement> homePageBrandName1 = driver.findElements(myntraLocators.homePageBrandName);
        for (int i = 0; i < homePageBrandName1.size(); i++) {
            String homePageBrandNameListText1 = homePageBrandName1.get(i).getText();
            Assert.assertTrue("Validation Failed", homePageBrandNameListText1.contains("Nike"));
        }
    }

    @Then("Go to the end of page and validate the number of pages and available buttons")
    public void goToTheEndOfPageAndValidateTheNumberOfPagesAndAvailableButton() throws InterruptedException {
        String pageCountText = driver.findElement(myntraLocators.pageCountTag).getText();
        WebElement activePageValidation = driver.findElement(myntraLocators.activePage);
        if (activePageValidation.isDisplayed()){
            activePageValidation.click();
        }
        WebElement nextButtonValidation = driver.findElement(myntraLocators.nextButton);
        if (nextButtonValidation.isDisplayed()){
            nextButtonValidation.click();
        }
        String[] pageCountTextParts = pageCountText.split(" ");
        int PageCount1 = Integer.parseInt(pageCountTextParts[3]);
        String pageCount2 = driver.findElement(myntraLocators.lastValueOfPageCount).getText();
        int PageCount2 = Integer.parseInt(pageCount2);
        Assert.assertEquals("Page count validation failed", PageCount1, PageCount2);
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
        Assert.assertEquals("Validation failed: Incorrect number of handles", 2, handles.size());
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
        Assert.assertTrue("Validation Pass", firstImgOldWindowText.equals(firstImgNewWindowText) && discountPriceOldWindowText.equals(discountPriceNewWindowText));
    }

    @And("Validate a click on ADD TO BAG, it should ask for size")
    public void validateAClickOnADDTOBAGItShouldAskForSize() {
        driver.findElement(myntraLocators.addToBag).click();
        String addToBagErrorText = driver.findElement(myntraLocators.addToBagError).getText();
        if (addToBagErrorText.contains("Please select a size")){
            driver.findElement(myntraLocators.sizeChartButton).click();
        }
        WebElement inchButtonSelectedValidation = driver.findElement(myntraLocators.inchButton);
        if (inchButtonSelectedValidation.isSelected()){
            WebElement cmButtonValidation = driver.findElement(myntraLocators.centimeterButton);
            cmButtonValidation.click();
            Assert.assertTrue("cm is selected", cmButtonValidation.isSelected());
        }
        driver.findElement(myntraLocators.sizeChartCloseButton).click();
        List<WebElement> sizeElements = driver.findElements(myntraLocators.sizeButton);
        for (WebElement element : sizeElements) {
            size = element.getText();
            if (size.isEmpty()) {
                System.out.println("Validation Failed");
            }
            Assert.assertTrue("Size is present", size.contains("XS") || size.contains("S")  || size.contains("M") || size.contains("L")  || size.contains("XL"));
        }
    }

    @Then("^Select the size \\\"(.*)\\\" and click on ADD TO BAG and validate item added to bag$")
    public void selectTheSizeAndClickOnADDTOBAGAndValidateItemAddedToBag(String M) {
        WebElement addToBagText = driver.findElement(myntraLocators.addToBagTextPopup);
        String msizebutton = "//p[text()= '%s']";
        driver.findElement(By.xpath(String.format(msizebutton, M))).click();
        driver.findElement(myntraLocators.addToBag).click();
        WebElement addToBagPopupWindow = driver.findElement(myntraLocators.addToBagPopup);
        if(addToBagPopupWindow.isDisplayed()) {
            Assert.assertTrue(addToBagText.isDisplayed());
        }
        WebElement deliveryCheckButton = driver.findElement(myntraLocators.deliveryCheckButton);
        deliveryCheckButton.click();
        WebElement deliveryCheckErrorPopup = driver.findElement(myntraLocators.deliveryCheckError);
        String deliveryCheckError = deliveryCheckErrorPopup.getText();
        System.out.println(deliveryCheckError);
        if (deliveryCheckErrorPopup.isDisplayed()){
            Assert.assertTrue("Validation Pass", deliveryCheckError.contains("Please enter a valid pincode"));
        }
        driver.findElement(myntraLocators.deliveryTextBox).sendKeys("204101");
        deliveryCheckButton.click();
        Boolean deliveryGreenButtonValidation = driver.findElement(myntraLocators.deliveryGreenCheck).isDisplayed();
        Boolean deliveryChangeButtonValidation = driver.findElement(myntraLocators.deliveryChangeButton).isDisplayed();
        if (deliveryGreenButtonValidation && deliveryChangeButtonValidation) {
            driver.findElement(myntraLocators.deliveryMoreInfo).click();
            String exchangeAndReturnText = driver.findElement(myntraLocators.easyExchangeAndReturnHeading).getText();
            Assert.assertEquals("String Match", exchangeAndReturnText, "Easy Exchange & Return");
        } else {
            System.out.println("Element missing");
        }
        driver.findElement(myntraLocators.moreInfoCloseButton).click();
        driver.findElement(myntraLocators.deliveryChangeButton).click();
    }

    @And("Validate checkout page")
    public void validateCheckoutPage() {
        driver.findElement(myntraLocators.bagIcon).click();
        driver.findElement(myntraLocators.checkoutFullPage).click();
        String addToBagTextUrl = driver.getCurrentUrl();
        Assert.assertTrue(addToBagTextUrl.contains("checkout"));
        driver.findElement(myntraLocators.addedItemConfirmation).isDisplayed();
        String secureElement = driver.findElement(myntraLocators.secureElement).getText();
        Assert.assertTrue("Validation Pass", secureElement.contains("100%"));
        driver.findElement(myntraLocators.moveToWishlistButton).isDisplayed();
        driver.findElement(myntraLocators.placeOrderButton).click();
        String placeOrderButtonClickUrl = driver.getCurrentUrl();
        Assert.assertTrue("Validation Pass", placeOrderButtonClickUrl.contains("login"));
        driver.navigate().back();
    }

    @Then("Validate Remove and wishlist functionality")
    public void validateRemoveAndWishlistFunctionality() {
        WebElement addedItemConfirmation = driver.findElement(myntraLocators.addedItemConfirmation);
        WebElement moveToWishlistButton = driver.findElement(myntraLocators.moveToWishlistButton);
        WebElement removeButton = driver.findElement(myntraLocators.removeButton1);
        if (addedItemConfirmation.isDisplayed() && moveToWishlistButton.isDisplayed() && removeButton.isDisplayed()) {
            removeButton.click();
        }
        WebElement removeButtonConfirmationPage = driver.findElement(myntraLocators.removeButtonConfirmationPage);
        if (removeButtonConfirmationPage.isDisplayed()){
            driver.findElement(myntraLocators.removeButton2).click();
    }
        driver.findElement(myntraLocators.addItemFromWishlist).click();
        String wishlistUrl = driver.getCurrentUrl();
        Assert.assertTrue(wishlistUrl.contains("wishlist"));
        driver.findElement(myntraLocators.wishlistScreenLoginButton).isDisplayed();
        driver.findElement(myntraLocators.wishlistScreenLoginButton).click();
        String loginUrl = driver.getCurrentUrl();
        Assert.assertTrue(loginUrl.contains("https://www.myntra.com/login?referer=https://www.myntra.com/wishlist"));
        driver.quit();
    }
}