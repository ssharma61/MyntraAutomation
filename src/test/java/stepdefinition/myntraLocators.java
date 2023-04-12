package stepdefinition;

import org.openqa.selenium.By;

import javax.swing.plaf.PanelUI;

public class myntraLocators {
    public static By profileIcon = By.xpath("//span[@class='myntraweb-sprite desktop-iconUser sprites-headerUser']");
    public static By myntraIcon = By.xpath("//script[@data-release='vendor']");
    public static By loginOption = By.xpath("//a[@class='desktop-linkButton']");
    public static By continuebutton = By.xpath("//div[@class='submitBottomOption']");
    public static By invalidmobilenoerror = By.xpath("//div[@class='errorContainer']");
    public static By termsofuse = By.xpath("//a[normalize-space()='Terms of Use']");

    public static By mobilenumberfield = By.xpath("//input[@class='form-control mobileNumberInput']");

    //    public static By loginvalidation = By.xpath("\"//div[@class='desktop-infoEmail']\"");
    public static By Men = By.xpath("//a[@class='desktop-main'][normalize-space()='Men']");
    public static By mainpageelements1 = By.xpath("//a[@class='desktop-main']");
    public static By mainpageelements2 = By.xpath("//span[@class='desktop-userTitle']");
    public static By searchtab = By.xpath("//input[@class='desktop-searchBar']");
    public static By sortbydropdown = By.xpath("//span[@class='myntraweb-sprite sort-downArrow sprites-downArrow']");
    public static By filtertag = By.xpath("//span[@class='header-title']");
    public static By filtertagelement = By.xpath("//span[@class='vertical-filters-header']");
    public static By categorytag = By.xpath("//div[@class='vertical-filters-filters categories-container']//label[@class='common-customCheckbox vertical-filters-label']");
    public static By brandtag = By.xpath("//div[@class='vertical-filters-filters brand-container']//label[@class='vertical-filters-label common-customCheckbox']");
    public static By pricetag = By.xpath("//div[@class='vertical-filters-filters']//label[@class='common-customCheckbox vertical-filters-label']");
    public static By colortag = By.xpath("//div[@class='vertical-filters-filters']//li[@class='colour-listItem']");
    public static By colormoretag = By.xpath("//div[@class='colour-more']//span");
    public static By discountrangetag = By.xpath("//div[@class='vertical-filters-filters']//label[@class='common-customRadio vertical-filters-label']");
    public static By brandmoretag = By.xpath("//div[@class='brand-more']");
    public static By brandmorepage = By.xpath("//div[@class='FilterDirectory-panel FilterDirectory-expanded']");
    public static By brandmorepageopen = By.xpath("//div[@class='FilterDirectory-panel FilterDirectory-expanded']");
    public static By brandmorepagetag = By.xpath("//div[@class='FilterDirectory-panel FilterDirectory-expanded']//ul[@class='FilterDirectory-list']//li");
    public static By desktopcontainermen = By.xpath("(//div[@class='desktop-categoryContainer'])[1]");
    public static By topwearpagevalidate = By.xpath("//ul[@class='breadcrumbs-list']/li/span[contains(text(),'Men Topwear')]");
    public static By searchbrandtag = By.xpath("//input[@class='FilterDirectory-searchInput']");
    public static By brandtextinsearchtab = By.xpath("//label[@class=' common-customCheckbox']");
    public static By brandcheckbox = By.xpath("//label[@class=' common-customCheckbox']//div[@class='common-checkboxIndicator']");
    public static By homepageimg = By.xpath("//div[@class='search-searchProductsContainer row-base']//img[@class='img-responsive']");
    public static By homepagebrandname = By.xpath("//h3[@class='product-brand']");
    public static By brandpageclosebutton = By.xpath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']");
    public static By pagecounttag = By.xpath("//li[@class='pagination-paginationMeta']");
    public static By lastvalueofpagecount = By.xpath("//ul[@class='pagination-container']//li[@class='pagination-number'][last()]");
    public static By activepage = By.xpath("//li[@class='pagination-active']");
    public static By nextbutton = By.xpath("//li[@class='pagination-next']");
    public static By previousbutton = By.xpath("//li[@class='pagination-prev']");
    //    static String firstimg = homepageimg + "[1]";
    public static By firstimgclick = By.xpath("//li[@class='product-base'][1]");
    public static By firstimgtext(int i) {
        return By.xpath("(//h3[@class='product-brand'])" + "[" + i + "]");
    }

    public static By firstimgnewwindow = By.xpath("//h1[@class='pdp-title']");
    public static By discuntoldwindown = By.xpath("(//span[@class='product-discountedPrice'])[1]");
    public static By discountnewwindow = By.xpath("//span[@class='pdp-discount']");
    public static By headingofnewwindown = By.xpath("//div[@class='breadcrumbs-container']");
    public static By addtobag = By.xpath("//span[@class='myntraweb-sprite pdp-whiteBag sprites-whiteBag pdp-flex pdp-center']");
    public static By addtobagerror = By.xpath("//span[@class='size-buttons-size-error-message']");
    public static By sizebutton = By.xpath("//button[@class='size-buttons-size-button size-buttons-size-button-default']");
    public static By addtobagpopup = By.xpath("//p[@class='notify-thumbnail-text']");
    public static By gotobag = By.xpath("//a[@class='pdp-goToCart pdp-add-to-bag pdp-button pdp-flex pdp-center ']");
    public static By bagicon = By.xpath("//span[@class='myntraweb-sprite desktop-iconBag sprites-headerBag']");
    public static By addtobagtextpopup = By.xpath("//span[contains(@class,'desktop-badge')]");
    public static By deliverytextbox = By.xpath("//input[@class='pincode-code']");
    public static By deliverycheckbutton = By.xpath("//div[@class='pincode-deliveryContainer']//input[@type='submit']");
    public static By deliverygreencheck = By.xpath("//div[@class='pincode-tick sprites-lightTick']");
    public static By deliverychangebutton = By.xpath("//button[@class='pincode-check-another-pincode pincode-button']");
    public static By secureelement = By.xpath("//div[@class='secure']");
    public static By placeorderbutton = By.xpath("//button[@class='css-etguer']");
    public static By removebutton1 = By.xpath("//button[contains(@class,'Remove')]");
    public static By removebutton2 = By.xpath("//button[@class='inlinebuttonV2-base-actionButton ']");
    public static By removebuttonocnfirmationpage = By.xpath("//div[@class='modal-base-modal bulkActionStrip-confirmationModalDesktop']");
    public static By movetowishlistbutton = By.xpath("//button[contains(@class,'Wishlist')]");
    public static By addeditemconfirmation = By.xpath("//div[@class='itemContainer-base-item ']");
    public static By checkoutfullpage = By.xpath("//div[@class='itemComponents-base-invisibleBackDrop']");
    public static By additemfromwishlist = By.xpath("//div[@class='button-base-button emptyCart-base-wishlistButton ']");
    public static By wishlistscreenloginbutton = By.xpath("//a[@class='wishlistLogin-button']");
}




