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
}




