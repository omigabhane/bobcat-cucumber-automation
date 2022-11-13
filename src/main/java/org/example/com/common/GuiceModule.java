package org.example.com.common;

import com.cognifide.qa.bb.utils.YamlReader;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.inject.AbstractModule;
import com.google.inject.Module;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Slf4j
public class GuiceModule extends AbstractModule {
    public GuiceModule() {

    }

    @Override
    protected void configure() {
        String runmodes = System.getProperty("runmode", "default");
        String[] separateRunModes = StringUtils.split(runmodes, ",");
        List<String> modules = new ArrayList();
        TypeReference typeReference = new TypeReference<List<String>>() {
        };
        Arrays.stream(separateRunModes).forEach((runmode) -> {
            modules.addAll((Collection) YamlReader.readFromTestResources("runmodes/" + runmode, typeReference));
        });
        modules.stream().forEach(this::installFromName);
    /*
    Multibinder<WebDriverCreator> creators = Multibinder.newSetBinder(binder(), WebDriverCreator.class);
    creators.addBinding().to(CustomChromeCreator.class);*/
    }

    private void installFromName(String moduleName) {
        try {
            this.install((Module) Class.forName(moduleName).newInstance());
        } catch (IllegalAccessException | ClassNotFoundException | InstantiationException var3) {
            log.error("Error when binding module: " + moduleName, var3);
        }

    }
}