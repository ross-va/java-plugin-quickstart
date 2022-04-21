package org.spyne.javapluginquickstart.fooplugin;

import org.spyne.javapluginquickstart.spi.task.Task;

public class FooImpl implements Task {

    @Override
    public void doTask() {
        System.out.println("I'm a FOO dooer");
    }
}
