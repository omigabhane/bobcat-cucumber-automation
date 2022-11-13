package org.example.com.driverutil;

import com.cognifide.qa.bb.provider.selenium.webdriver.creators.WebDriverCreator;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

public class CustomChromeCreator implements WebDriverCreator {

  @Override
  public WebDriver create(Capabilities capabilities) {
    return new DriverFactory().newDriver();
  }

  @Override
  public String getId() {
    return "customChrome";
  }
}