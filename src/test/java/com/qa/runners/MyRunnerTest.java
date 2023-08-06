package com.qa.runners;

import com.qa.utils.*;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.apache.logging.log4j.ThreadContext;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"
                , "html:target/cucumber.html"
                , "summary"
                , "me.jvt.cucumber.report.PrettyReports:target/cucumber-html-reports"
        }
        ,features = {"src/test/resources/features"}
        ,glue = {"com.qa.stepdef"}
        ,snippets = CucumberOptions.SnippetType.CAMELCASE
        ,dryRun=false
        ,monochrome=true
        ,tags = "@Main"
)
public class MyRunnerTest {

    @BeforeClass
    public static void initialize() throws Exception {
        GlobalParams params = new GlobalParams();
        Properties props = new PropertyManager().getProps();
        params.initializeGlobalParams();

        ThreadContext.put("ROUTINGKEY", params.getPlatformName() + "_"
                + params.getDeviceName());

        if (props.getProperty("remoteOrLocal").equalsIgnoreCase("Local"))
            new ServerManager().startServer();
            DriverManager.initializeDriver();
            Thread.sleep(1500);

//        Set contexts = DriverManager.getDriver().getContextHandles();
//        for (Object context : contexts) {
//            System.out.println("context: " + context.toString());
//        }
    }

    @AfterClass
    public static void quit() throws IOException, InterruptedException {
        Properties props = new PropertyManager().getProps();
        if (DriverManager.getDriver() != null) {
            DriverManager.getDriver() .quit();
            DriverManager.setDriver(null);

        }if (props.getProperty("remoteOrLocal").equalsIgnoreCase("Local")) {
            ServerManager serverManager = new ServerManager();
            if (serverManager.getServer() != null) {
                serverManager.getServer().stop();
            }
        }
    }

}
