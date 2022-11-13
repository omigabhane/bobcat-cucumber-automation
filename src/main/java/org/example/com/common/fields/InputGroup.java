package org.example.com.common.fields;

import com.cognifide.qa.bb.aem.core.component.dialog.dialogfields.DialogField;
import com.cognifide.qa.bb.qualifier.CurrentScope;
import com.cognifide.qa.bb.qualifier.PageObject;
import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@PageObject(
        css = ".coral-PathBrowser:not([type='hidden'])"
)
public class InputGroup implements DialogField {

  @Inject
  @CurrentScope
  private WebElement elm;

  @FindBy(css = ".coral-InputGroup-input")
  private WebElement input;

  @FindBy(
          xpath = "../label"
  )
  private List<WebElement> label;

  @Override
  public void setValue(Object value) {
    input.clear();
    input.sendKeys(String.valueOf(value));
  }

  public String getLabel() {
    return label.isEmpty() ? "" : ((WebElement)label.get(0)).getText();
  }
  public String getMessage(){
    return elm.findElement(By.xpath("/following-sibling::coral-tooltip")).getText();
  }
  public WebElement hoverElement(){
    return elm.findElement(By.xpath("/following-sibling::coral-icon"));
  }
}