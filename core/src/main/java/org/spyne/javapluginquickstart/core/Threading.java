package org.spyne.javapluginquickstart.core;

import org.spyne.javapluginquickstart.spi.task.PluginFactory;
import org.spyne.javapluginquickstart.spi.task.Task;

import java.io.File;

public class Threading implements Runnable{

    private Task plugin;

    public Threading(Task plugin){
        this.plugin = plugin;
    }

    @Override
    public void run() {
        System.out.println("Running from inside Threading.run() << %s >> ");
        plugin.doTask();

    }

    public static void main(String[] args) {

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

        System.out.println("This is running from the plugin");
        final Task foo = f.build();
        Threading thread1 = new Threading(foo);
        thread1.run();

        System.out.println("Running from bar plugin");
        final Task bar = b.build();
        Threading thread2 = new Threading(bar);
        thread2.run();
    }
}
