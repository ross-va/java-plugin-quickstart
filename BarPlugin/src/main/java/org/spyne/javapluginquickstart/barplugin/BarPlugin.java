package org.spyne.javapluginquickstart.barplugin;

import org.spyne.javapluginquickstart.spi.Plugin;
import org.spyne.javapluginquickstart.spi.foo.FooFactory;

import java.util.Arrays;
import java.util.List;

public class BarPlugin implements Plugin {
    @Override
    public List<FooFactory> getFooFactories() {
        return Arrays.asList(
                new BarFactoryImpl()
        );
    }
}
