package com.qa.utils;

import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class AppiumCapabilitiesManager {
    public DesiredCapabilities getCaps() throws IOException {
        GlobalParams params = new GlobalParams();
        Properties props = new PropertyManager().getProps();
        try {
            TestUtils.log().info("getting capabilities");
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, params.getPlatformName());

            switch (params.getPlatformName()) {
                case "Android":
                    //String androidAppUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                      //      + File.separator + "resources" + File.separator + "apps" + File.separator + props.getProperty("AppFileNameAndroid");
                   // System.out.println("androidAppUrl = " + androidAppUrl);
                   // TestUtils.log().info("appUrl is" + androidAppUrl);

                    if (props.getProperty("RealOrSimulator").equalsIgnoreCase("Simulator")) {
                        caps.setCapability("udid", "emulator-5554");
                    } else {
                        caps.setCapability("deviceName", "AndroidEmulator");
                    }
                    caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, props.getProperty("androidAutomationName"));
                    caps.setCapability("systemPort", params.getSystemPort());
                   // caps.setCapability("app", "/Users/serkankanca/Downloads/SampleMobile 2/src/main/resources/apps/Android.SauceLabs.Mobile.Sample.app.2.7.1 (1).apk");
                    caps.setCapability("appWaitActivity", "*");
                    caps.setCapability("appPackage", "com.palringo.qa_automation_android");
                    caps.setCapability("appActivity", "com.palringo.qa_automation_android.MainActivity");
                    break;
                case "iOS":

                    break;
            }
            return caps;
        } catch (Exception e) {
            e.printStackTrace();
            TestUtils.log().fatal("Failed to load capabilities. ABORT!!" + e.toString());
            throw e;
        }
    }
}
