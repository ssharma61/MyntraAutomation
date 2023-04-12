@Myntra1
Feature: Testing myntra website

  Background: Launching Myntra website
    Given I have opened my web browser
    Then I navigate to Myntra
    Then Validate if the myntra page opened

  Scenario: Check Singup or Login button functionality
    Given I have clicked on Signup or login button under profile
    Then Validate the UI is redirected to login or singup page
    And User should not able to proceed with invalid mobile number
    Then User to validate the termsofuse and privacypolicy link


  Scenario: Test Home page Search functionality
    Given Validate if desktop tag are present
    Then click on search button and validate the url
    Then All filter tag are present on url
    Then Validate data under categories
    Then Validate data under Brand
    Then Validate data under price
    Then Validate data under colour
    And Validate data under discount range
    Then validate the navigated page elements
# status till 04-07-2023
  Scenario: Check Men clothing button and validate the functionalities
    Given Hover on Men "Men" and Validate the background page and click on "Topwear"
    Then Validate the topwear page is open
    Then Validate the images are visible on screen
    Then Check brand button search functionality
    Then Go to the end of page and validate the number of pages and available buttons

  Scenario: Test Add to Bag functionality
    Given Click on image and validate if it opens in new window
    Then Validate the URL and text on the window
    And Validate a click on ADD TO BAG, it should ask for size
    Then Select the size "M" and click on ADD TO BAG and validate item added to bag
    And Validate checkout page
    Then Validate Remove and wishlist functionality



