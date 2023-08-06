//package com.qa.utils;
//
//import io.appium.java_client.*;
//import io.appium.java_client.pagefactory.AppiumFieldDecorator;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Dimension;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//import java.util.ArrayList;
//import java.util.List;
//
//import static io.appium.java_client.touch.TapOptions.tapOptions;
//import static io.appium.java_client.touch.WaitOptions.waitOptions;
//import static io.appium.java_client.touch.offset.ElementOption.element;
//import static io.appium.java_client.touch.offset.PointOption.point;
//import static java.time.Duration.ofMillis;
//
//
//public class MobileUtils {
//    protected static AppiumDriver<?> driver;
//    static TestUtils utils = new TestUtils();
//    static GlobalParams params = new GlobalParams();
//
//
//    public MobileUtils() throws Exception {
//
//        this.driver = DriverManager.getDriver();
//        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
//    }
//    public static void verticalSwipeByPercentagesByScreenCount(double startPercentage, double endPercentage, double anchorPercentage,int screenCount) {
//        Dimension size = driver.manage().window().getSize();
//        int anchor = (int) (size.width * anchorPercentage);
//        int startPoint = (int) (size.height * startPercentage);
//        int endPoint = (int) (size.height * endPercentage);
//        for(int i=0;i<screenCount;i++) {
//            new TouchAction(driver)
//                    .press(point(anchor, startPoint))
//                    .waitAction(waitOptions(ofMillis(400)))
//                    .moveTo(point(anchor, endPoint))
//                    .release().perform();
//        }
//    }
//
//
//    public static void scrollEelementWithJS(WebElement element) {
//        JavascriptExecutor executor = (JavascriptExecutor) driver;
//        executor.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", element);
//
//    }
//
//    public static void waitFor(int seconds) {
//        try {
//            Thread.sleep(seconds * 1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public static void waitForVisibility(WebElement e, int i) {
//        WebDriverWait wait = new WebDriverWait(new DriverManager().getDriver(), Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.visibilityOf(e));
//    }
//
//    public static void waitForClickable(WebElement e) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.elementToBeClickable(e));
//    }
//
//    public static void waitForVisibility(By e) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(e));
//    }
//
//    public static void clear(WebElement e) {
//        waitForVisibility(e, 5);
//        e.clear();
//    }
//
//    public static void clickWithWait(WebElement e) {
//        waitForVisibility(e, 5);
//        e.click();
//    }
//
//    public static void clickWithWait(WebElement e, String msg) {
//        waitForVisibility(e, 5);
//        utils.log().info(msg);
//        e.click();
//    }
//
//    public static void tapByElement(WebElement element) {
//        new TouchAction(driver)
//                .tap(tapOptions().withElement(element(element)))
//                .waitAction(waitOptions(ofMillis(250))).perform();
//    }
//
//
//    public static void sendKeys(WebElement e, String txt) {
//        waitForVisibility(e, 5);
//        e.sendKeys(txt);
//    }
//
//    public static void sendKeys(WebElement e, String txt, String msg) {
//        waitForVisibility(e, 5);
//        utils.log().info(msg);
//        e.sendKeys(txt);
//    }
//
//
//    public static String getAttribute(By e, String attribute) {
//        waitForVisibility(e);
//        return driver.findElement(e).getAttribute(attribute);
//    }
//
//   // public static void closeApp() {
//      //  ((InteractsWithApps) driver).closeApp();
//   // }
//
//   // public static void launchApp() {
////        ((InteractsWithApps) driver).launchApp();
////    }
//
////    public static WebElement andScrollToElementUsingUiScrollable(String childLocAttr, String childLocValue) {
////        return (WebElement) ((FindsByAndroidUIAutomator) driver).findElementByAndroidUIAutomator(
////                "new UiScrollable(new UiSelector()" + ".scrollable(true)).scrollIntoView("
////                        + "new UiSelector()." + childLocAttr + "(\"" + childLocValue + "\"));");
////    }
//
//
//
//
//    public static void swipe(int startX, int startY, int endX, int endY, int millis)
//            throws InterruptedException {
//        TouchAction t = new TouchAction(driver);
//        t.press(point(startX, startY)).waitAction(waitOptions(ofMillis(millis))).moveTo(point(endX, endY)).release()
//                .perform();
//    }
//
//    public static List<String> getElementsText(List<MobileElement> list) {
//        List<String> elemTexts = new ArrayList<>();
//        for (MobileElement el : list) {
//            elemTexts.add(el.getText());
//        }
//        return elemTexts;
//    }
//
//    public static void clickWithJS(MobileElement element) {
//        ((JavascriptExecutor) new DriverManager().getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
//        ((JavascriptExecutor) new DriverManager().getDriver()).executeScript("arguments[0].click();", element);
//    }
//
//
//
//}