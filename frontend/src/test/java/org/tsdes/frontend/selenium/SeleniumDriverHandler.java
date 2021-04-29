package org.tsdes.frontend.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Primarily adapted from https://github.com/arcuri82/testing_security_development_enterprise_systems/
 */
public class SeleniumDriverHandler {

    private static boolean lookIfFileExists(String property, Path path) {
        if(Files.exists(path)){
            System.setProperty(property, path.toAbsolutePath().toString());
            return true;
        }
        return false;
    }

    private static boolean setupDriverExecutable(String executableName, String property) {
        String homeDir = System.getProperty("user.home");
        //first try Linux/Mac executable
        if(!lookIfFileExists(property, Paths.get(homeDir, executableName))){
            //then check if on Windows
            return lookIfFileExists(property, Paths.get(homeDir, executableName + ".exe"));
        }
        return true;
    }

    public static WebDriver getChromeDriver() {
        boolean OK = setupDriverExecutable("chromedriver", "webdriver.chrome.driver");
        if(!OK) return null;
        return new ChromeDriver();
    }
}
