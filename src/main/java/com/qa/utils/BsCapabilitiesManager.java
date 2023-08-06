package com.qa.utils;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.util.Properties;

public class BsCapabilitiesManager {
    TestUtils utils = new TestUtils();

    public DesiredCapabilities getCaps() throws IOException {
        GlobalParams params = new GlobalParams();
        Properties props = new PropertyManager().getProps();
        DesiredCapabilities capabilities = new DesiredCapabilities();

        try {
            switch (params.getPlatformName()) {
                case "Android":
                    capabilities.setCapability("browserstack.user", props.getProperty("browserstackUser"));
                    capabilities.setCapability("browserstack.key", props.getProperty("browserstackKey"));
                    capabilities.setCapability("app", props.getProperty("AndroidAppURlBs"));
                    capabilities.setCapability("device", props.getProperty("browserstackAndroidDevice"));
                    capabilities.setCapability("os_version", props.getProperty("browserstackOsVersionAndroid"));
                    capabilities.setCapability("project", props.getProperty("RemoteProjectName"));
                    capabilities.setCapability("name", props.getProperty("RemoteTestName"));
                    capabilities.setCapability("build", "Java Android");
                    capabilities.setCapability("autoGrantPermissions", "true");
                    capabilities.setCapability("autoAcceptAlerts", "true");
                    capabilities.setCapability("locationServicesAuthorized","true");

                    break;
                case "iOS":
                    capabilities.setCapability("browserstack.user", props.getProperty("browserstackUser"));
                    capabilities.setCapability("browserstack.key", props.getProperty("browserstackKey"));
                    capabilities.setCapability("autoWebview","true");
                    capabilities.setCapability("app", props.getProperty("iOSAppURlBs"));
                    capabilities.setCapability("device", props.getProperty("browserstackiOSDevice"));
                    capabilities.setCapability("os_version",props.getProperty("browserstackOsVersioniOS"));
                    capabilities.setCapability("project", props.getProperty("RemoteProjectName"));
                    capabilities.setCapability("name", props.getProperty("RemoteTestName"));
                    capabilities.setCapability("build", "Java iOS");
                    capabilities.setCapability("autoGrantPermissions", "true");
                    capabilities.setCapability("autoAcceptAlerts", "true");
                    capabilities.setCapability("locationServicesAuthorized","true");
                    break;
            }
            return capabilities;
        } catch (Exception e) {
            e.printStackTrace();
            utils.log().fatal("Failed to load capabilities. ABORT!!" + e.toString());
            throw e;
        }
    }
}
