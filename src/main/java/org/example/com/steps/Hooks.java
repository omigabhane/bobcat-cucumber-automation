package org.example.com.steps;

import com.cognifide.qa.bb.aem.core.api.AemActions;
import com.cognifide.qa.bb.aem.core.modules.AemSitesAdminModule;
import com.cognifide.qa.bb.api.actions.ActionException;
import com.cognifide.qa.bb.api.actions.ActionsController;
import com.google.inject.Inject;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.runtime.java.guice.ScenarioScoped;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * A helper class that can create a screenshot file and gather additional data.
 */
@ScenarioScoped
public class Hooks {

  @Inject
  private WebDriver webDriver;

  @Inject
  private ActionsController controller;

  @Before
  public void login(Scenario scenario) throws ActionException {
    controller.execute(AemActions.LOG_IN);
  }

  @After
  public void addDataAndClose(Scenario scenario) {
    if (scenario.isFailed()) {
      if(webDriver instanceof TakesScreenshot){
        addScreenshot(scenario);
      }
      addPageLink(scenario);
    }
    webDriver.quit();
  }

  private void addPageLink(Scenario scenario) {
    scenario.write(scenario.getName() + "<a href=" + webDriver.getCurrentUrl() + ">link</a>");
  }

  private void addScreenshot(Scenario scenario) {
    byte[] screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
    scenario.embed(screenshot, "image/png");
  }

}
