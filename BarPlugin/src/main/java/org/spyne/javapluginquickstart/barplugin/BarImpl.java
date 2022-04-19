package org.spyne.javapluginquickstart.barplugin;

import org.spyne.javapluginquickstart.spi.foo.Foo;

public class BarImpl implements Foo {
    @Override
    public void doFoo() {
        System.out.println("I'm a Bar dooer.");
    }
}
