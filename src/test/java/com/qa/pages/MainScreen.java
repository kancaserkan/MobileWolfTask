package com.qa.pages;
import com.qa.utils.TestUtils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class MainScreen extends TestUtils {
    public MainScreen() throws Exception {
    }

    @AndroidFindBy(xpath = "//*[@resource-id='tag_button']")
    //*[@resource-id='tag_button']
    public WebElement buttonMainScreen;

    @AndroidFindBy(xpath = "//*[@resource-id='tag_label']")
    public WebElement valueMainScreen;

}