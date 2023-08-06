package com.qa.stepdef;

import com.qa.pages.MainScreen;
import com.qa.utils.TestUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.qa.utils.DriverManager.getDriver;


public class MainScreenSdf extends TestUtils{


    MainScreen mainScreen = new MainScreen();
    String xpath = "//*[@resource-id='tag_button']" ;
    String xpath2 = "//*[@resource-id='tag_button']" ;

    WebElement button = getDriver().findElement(AppiumBy.xpath(xpath));
    WebElement value = getDriver().findElement(AppiumBy.xpath(xpath2));
  //  WebElement value = getDriver().findElement(AppiumBy.id("tag_label"));
    //id ile locate edilemiyor


    //WebDriver driver;

    //  (mainScreen.buttonMainScreen,2);
    // element.click();

    public MainScreenSdf() throws Exception {

    }
    @When("the app is opened")
    public void theAppIsOpened() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println(button.getAttribute("text"));
        Assert.assertTrue(button.isDisplayed());
    }
    @Then("it’s main screen contains a button with text Click me and a label with text {int}")
    public void itSMainScreenContainsAButtonWithTextClickMeAndALabelWithText(Integer int1) throws InterruptedException {

        Thread.sleep(3000);
        button.click();
        Assert.assertTrue(button.isDisplayed());
        Assert.assertTrue(value.isDisplayed());
    }

    @Given("the label is {string}")
    public void theLabelIs(String expectedValue) throws InterruptedException {
        Thread.sleep(3000);

        button.click();
        String actualValue = button.getAttribute("text");
        System.out.println("Actual Value="+actualValue);
        Assert.assertTrue(expectedValue.equalsIgnoreCase(actualValue));
        //System.out.println("Actual Value="+actualValue);
    }
    @When("clicking the button")
    public void clickingTheButton() throws InterruptedException {
        Thread.sleep(3000);

        button.click();
    }
    @Then("the label should change to {string}")
    public void theLabelShouldChangeTo(String expectedValue) throws InterruptedException {
        Thread.sleep(3000);

        button.click();

        String actualValue = button.getAttribute("text");
        Assert.assertTrue(expectedValue.equalsIgnoreCase(actualValue));

    }




//    @When("the app is opened")
//    public void theAppIsOpened() {
//       //The app opens via before method
//    }
//    @When("clicking the button")
//    public void clickingTheButton() {
//
//        element.click();
//
//        //*[@resource-id='tag_label']
//    }
//    @Then("it’s main screen contains a button with text Click me and a label with text 0")
//    public void itsMainScreenContainsAButtonWithTextClickMeAndALabelWithText(){
//        System.out.println(element.getText().toString());
//        Assert.assertTrue(element.isDisplayed());
//        Assert.assertTrue(value.isDisplayed());
//    }
//
//    @Given("the label is {string}")
//    public void theLabelIs(String expectedValue) {
//
//        String actualValue = value.getAttribute("text");
//        Assert.assertTrue(expectedValue.equalsIgnoreCase(actualValue));
//    }
//
//    @Then("the label should change to {string}")
//    public void theLabelShouldChangeTo(String expectedValue) {
//        String actualValue = value.getAttribute("text");
//        element.click();
//        Assert.assertTrue(expectedValue.equalsIgnoreCase(actualValue));
//    }
}
