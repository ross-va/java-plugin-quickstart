package org.spyne.javapluginquickstart.core;

import org.spyne.javapluginquickstart.spi.task.PluginFactory;
import org.spyne.javapluginquickstart.spi.task.Task;

import java.io.File;

public class PluginThread<T> implements Runnable{

    private T thingThatRuns;

    public PluginThread(T instance){
        this.thingThatRuns = instance;
    }

    @Override
    public void run() {
        if (thingThatRuns.getClass().getSimpleName().equals("BarImpl")
                || thingThatRuns.getClass().getSimpleName().equals("FooImpl")) {
            Task task = (Task) thingThatRuns;
            task.doTask();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        String pluginsPath = "plugins";
        if (args.length > 0) {
            pluginsPath = args[0];
        }

        PluginLoader pluginLoader = new PluginLoader(new File(pluginsPath));
        pluginLoader.loadPlugins();

        PluginFactory f = pluginLoader.getFooFactory("foo");
        PluginFactory b = pluginLoader.getFooFactory("bar");

        if (f == null || b == null) {
            System.err.println(f == null ? "No factory for 'f' loaded!" : "");
            System.err.println(b == null ? "No factory for 'b' loaded!" : "");
            return;
        }

        Thread threadFoo = new Thread(new PluginThread<>(f.build()));
        Thread.sleep(5000);
        threadFoo.start();

        Thread threadBar = new Thread(new PluginThread<>(b.build()));
        Thread.sleep(1000);
        threadBar.start();
    }
}
