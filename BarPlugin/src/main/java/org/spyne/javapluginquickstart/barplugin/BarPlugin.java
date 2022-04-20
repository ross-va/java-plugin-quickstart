package org.spyne.javapluginquickstart.barplugin;

import org.spyne.javapluginquickstart.spi.Plugin;
import org.spyne.javapluginquickstart.spi.task.PluginFactory;

import java.util.Arrays;
import java.util.List;

public class BarPlugin implements Plugin {
    @Override
    public List<PluginFactory> getPluginFactories() {
        return Arrays.asList(
                new BarFactoryImpl()
        );
    }
}
