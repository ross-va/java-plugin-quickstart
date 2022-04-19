package org.spyne.javapluginquickstart.barplugin;

import org.spyne.javapluginquickstart.spi.foo.Foo;
import org.spyne.javapluginquickstart.spi.foo.FooFactory;

public class BarFactoryImpl implements FooFactory {
    @Override
    public String name() {
        return "bar";
    }

    @Override
    public Foo build() {
        return new BarImpl();
    }
}
