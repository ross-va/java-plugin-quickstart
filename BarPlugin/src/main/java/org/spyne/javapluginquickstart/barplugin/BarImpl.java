package org.spyne.javapluginquickstart.barplugin;

import org.spyne.javapluginquickstart.spi.task.Task;

public class BarImpl implements Task {
    @Override
    public void doTask() {
        System.out.println("I'm a BAR dooer");
    }
}
