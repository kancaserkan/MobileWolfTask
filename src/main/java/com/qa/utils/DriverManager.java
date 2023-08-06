package com.qa.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class DriverManager {

    private final static ThreadLocal<AppiumDriver> threadLocal = new ThreadLocal<>();
    TestUtils utils = new TestUtils();

    public static AppiumDriver getDriver(){
        return threadLocal.get();
    }

    public static void setDriver(AppiumDriver driver2){
        threadLocal.set(driver2);
    }

    public static void initializeDriver() throws Exception {
        AppiumDriver driver = null;
        GlobalParams params = new GlobalParams();
        Properties props = new PropertyManager().getProps();

        URL url;
        DesiredCapabilities caps;

        switch(props.getProperty("remoteOrLocal")){
            case "RemoteB":
                //BrowserStack
                url = new URL("http://hub.browserstack.com/wd/hub");
                caps= new BsCapabilitiesManager().getCaps();
                System.out.println("REMOTE_BrowserStack");
                break;

            case "Local":
              //  url = new ServerManager().getServer().getUrl();
           url = new URL("http://127.0.0.1:4723/");
                caps= new AppiumCapabilitiesManager().getCaps();
                System.out.println("LOCAL");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + props.getProperty("remoteOrLocal"));
        }


            try{
                TestUtils.log().info("initializing Appium driver");
                switch(props.getProperty("platformName")){
                    case "Android":
                        driver = new AndroidDriver(url, caps);
                        break;
                    case "iOS":
                        if(props.getProperty("RealOrSimulator").equalsIgnoreCase("Real")){
                            driver = new IOSDriver(url, caps);
                            System.out.println("url = " + url);
                        }else{
                            URL urlAppium = new URL(props.getProperty("appiumURL"));
                            System.out.println("url1 = " + urlAppium);
                            driver = new IOSDriver(urlAppium, caps);
                        }
                        break;

                }
               if(driver == null) {
                        throw new Exception("driver is null. ABORT!!!");
                    }

                TestUtils.log().info("Driver is initialized");
                setDriver(driver);
            } catch (IOException e) {
                e.printStackTrace();
                TestUtils.log().fatal("Driver initialization failure. ABORT !!!!" + e.toString());
                throw e;
            }


    }

}
