package org.example.com.driverutil;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

public class DriverFactory{

    public WebDriver newDriver() {
        WebDriver driver;
        //String lstr = System.getProperty("browser.name").toUpperCase();
        DriverType driverType =DriverType.CHROME; //DriverType.valueOf(str);
        //List<String> args = Arrays.asList(System.getProperty("browser.arguments").split(";"));
        switch (driverType) {
            case CHROME:
                ChromeOptions options = new ChromeOptions();
                //args.forEach(arg -> options.addArguments(arg));
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(options);
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case IE:
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;
            case OPERA:
                WebDriverManager.operadriver().setup();
                driver = new OperaDriver();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
        }
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        return driver;

    }
}
