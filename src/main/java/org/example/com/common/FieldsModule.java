package org.example.com.common;

import com.cognifide.qa.bb.aem.core.component.dialog.dialogfields.DialogField;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import org.example.com.common.fields.InputGroup;

public class FieldsModule extends AbstractModule {

  public FieldsModule() {
  }

  @Override
  protected void configure() {
    MapBinder<String, DialogField> fieldsBinder = MapBinder.newMapBinder(this.binder(), String.class, DialogField.class);
    this.registerField(fieldsBinder, "INPUT_GROUP", InputGroup.class);
  }
  private void registerField(MapBinder<String, DialogField> binder, String name, Class<? extends DialogField> type) {
    binder.addBinding(name).to(type);
  }
}