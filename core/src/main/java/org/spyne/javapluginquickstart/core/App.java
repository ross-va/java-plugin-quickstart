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

    PluginFactory f = Util.loadPlugin(pluginsPath, "foo");
    PluginFactory b = Util.loadPlugin(pluginsPath, "bar");


    // get plugin based on class/event type


    if (f == null || b == null) {
      System.err.println("Some factories loaded!");
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
