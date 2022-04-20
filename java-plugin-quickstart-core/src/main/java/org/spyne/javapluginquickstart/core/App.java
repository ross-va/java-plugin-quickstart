package org.spyne.javapluginquickstart.core;

import java.io.File;

import org.spyne.javapluginquickstart.spi.task.Task;
import org.spyne.javapluginquickstart.spi.task.PluginFactory;

/**
 * Launcher for javapluginquickstart
 */
public class App {

  public static void main(String[] args) {
    new App().run(args);
  }

  public void run(final String[] args) {
    System.out.println("Hello World!");

    String pluginsPath = "plugins";
    if (args.length > 0) {
      pluginsPath = args[0];
    }

    PluginLoader pluginLoader = new PluginLoader(new File(pluginsPath));
    pluginLoader.loadPlugins();


    // get plugin based on class/event type


    PluginFactory f = pluginLoader.getFooFactory("foo");
    PluginFactory b = pluginLoader.getFooFactory("bar");
    System.out.println(b);

    if (f == null) {
      System.err.println("No factories loaded!");
      return;
    }

    System.out.println("This is running from the plugin");
    final Task foo = f.build();
    foo.doTask();

    System.out.println("Running from bar plugin");
    final Task bar = b.build();
    bar.doTask();
  }
}
