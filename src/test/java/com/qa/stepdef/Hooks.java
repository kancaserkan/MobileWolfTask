package com.qa.stepdef;

import com.qa.utils.DriverManager;
//import com.qa.utils.MobileUtils;
import com.qa.utils.PropertyManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;

import java.io.IOException;
import java.util.Properties;

public class Hooks  {

    PropertyManager props = new PropertyManager();
    byte[] screenshot;

    public Hooks() throws Exception {
    }

    @Before
    public void initialize() throws Exception {

    }

    @After
    public void quit(Scenario scenario) throws IOException, InterruptedException
    {
        Properties myprops = props.getProps();
        if (scenario.isFailed())
        {
            screenshot = DriverManager.getDriver().getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        switch(myprops.getProperty("platformName"))
        {
            case "Android":
                System.out.println(" before closing the app");
                //driver.closeApp();
               // driver.launchApp();
                System.out.println(" after launching the app");
                Thread.sleep(2000);
                break;
            case "iOS":
                //driverManager.getDriver().terminateApp("io.ontrac.nutshell");
                //driverManager.getDriver().resetApp();
                break;
        }
    }
}

