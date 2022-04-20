package org.spyne.javapluginquickstart.barplugin;

import org.spyne.javapluginquickstart.spi.task.Task;
import org.spyne.javapluginquickstart.spi.task.PluginFactory;

public class BarFactoryImpl implements PluginFactory {
    @Override
    public String name() {
        return "bar";
    }

    @Override
    public Task build() {
        return new BarImpl();
    }
}
