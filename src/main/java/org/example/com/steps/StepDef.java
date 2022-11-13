package org.example.com.steps;

import com.cognifide.qa.bb.aem.core.api.AemActions;
import com.cognifide.qa.bb.aem.core.component.actions.ConfigureComponentData;
import com.cognifide.qa.bb.aem.core.component.configuration.ResourceFileLocation;
import com.cognifide.qa.bb.aem.core.modules.AemSitesAdminModule;
import com.cognifide.qa.bb.aem.core.pages.sling.DeletePage;
import com.cognifide.qa.bb.aem.core.pages.sling.SlingPageData;
import com.cognifide.qa.bb.aem.core.siteadmin.actions.CreatePageActionData;
import com.cognifide.qa.bb.api.actions.ActionException;
import com.cognifide.qa.bb.api.actions.ActionsController;
import com.cognifide.qa.bb.page.BobcatPageFactory;
import com.google.inject.Inject;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.guice.ScenarioScoped;
import org.example.com.page.TPage;

import static org.hamcrest.MatcherAssert.assertThat;

@ScenarioScoped
public class StepDef {
    private TPage tPage;

    @Inject
    private BobcatPageFactory bobcatPageFactory;

    @Inject
    private ActionsController controller;

    @Given("^Author opens (.*) '(.*)'$")
    public void opens(final String type , final String param) {
        tPage = bobcatPageFactory.create(param, TPage.class);
        if (type.equalsIgnoreCase("editor")){
            tPage.openInEditor();
        }else if (type.equalsIgnoreCase("site")){
            tPage.openSite();
        }else {
            tPage.open();
        }
    }

    @Then("^Author can see the current page title is (.*)$")
    public void displayTitle(final String title) {
        tPage.setTitle(title);
        assertThat("Should be true", tPage.isDisplayed());
    }

    @Then("^Author could see Bobcat Test page is created$")
    public void authorCouldSeePage(final String title) {
        tPage.setTitle(title);
        assertThat("Should be true", tPage.isDisplayed());
    }

    @Then("^Author configure (.*) component present at (.*) having order (\\d+) with data (.*)$")
    public void createComponent(final String componentName,final String layout, final int order,final String yamlFile) throws ActionException {
        controller.execute(AemActions.CONFIGURE_COMPONENT,new ConfigureComponentData(layout,componentName,order,new ResourceFileLocation("input/"+yamlFile)));
    }

    @When("Author create new (.*) page using the (.*) template")
    public void createPageUsingTemplate(final String pageName, final String template) throws ActionException {
        String title = pageName.toLowerCase().trim();
        if (title.contains(" ")) {
            title = title.replaceAll(" ", "_");
        }
        controller.execute(AemActions.CREATE_PAGE_VIA_SITEADMIN,new CreatePageActionData(template,pageName,title));
    }

    @When("Author delete (.*)")
    public void deletePageUsingTemplate(final String contentPath) throws ActionException {
        controller.execute(AemActions.DELETE_PAGE_VIA_SLING,new SlingPageData(contentPath));
    }
}