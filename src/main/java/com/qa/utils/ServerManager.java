package com.qa.utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class ServerManager {

    private static ThreadLocal<AppiumDriverLocalService> server = new ThreadLocal<>();
    Properties props;

    {
        try {
            props = new PropertyManager().getProps();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AppiumDriverLocalService getServer(){
        return server.get();
    }

    public void startServer() throws IOException {
        TestUtils.log().info("starting appium server");
        AppiumDriverLocalService server = macOSGetAppiumService();

        server.start();
        if(server == null || !server.isRunning()){
            TestUtils.log().fatal("Appium server not started. ABORT!!!");
            throw new AppiumServerHasNotBeenStartedLocallyException("Appium server not started. ABORT!!!");
        }

        server.clearOutPutStreams();
        ServerManager.server.set(server);
        TestUtils.log().info("Appium server started");
    }

    public AppiumDriverLocalService macOSGetAppiumService(){
        String Appium_MAIN_JS = "/opt/homebrew/lib/node_modules/appium/build/lib/main.js";
        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .withAppiumJS(new File(Appium_MAIN_JS)).usingPort(4723)
             //   .usingAnyFreePort()
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE));

    }

    public AppiumDriverLocalService WinGetAppiumService() throws IOException {

        GlobalParams params = new GlobalParams();
        PropertyManager props = new PropertyManager();
        Properties myprops = props.getProps();
        HashMap<String, String> environment = new HashMap<String, String>();
        environment.put("PATH", "/" + System.getenv("PATH"));
        environment.put("ANDROID_HOME", "C:\\Program Files\\Android\\Android Studio\\bin");
        environment.put("JAVA_HOME", "C:\\Program Files\\Java\\jre1.8.0_261");
        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(new File(props.getProps().getProperty("FilePathChromeDriver")))
                .withAppiumJS(new File(props.getProps().getProperty("File")))
                .usingPort(4723)
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withEnvironment(environment)
                .withLogFile(new File(myprops.getProperty("platformName") + "_"
                        + params.getDeviceName() + File.separator + "Server.log")));
    }
}
