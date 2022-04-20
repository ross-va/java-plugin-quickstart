package org.spyne.javapluginquickstart.spi.task;

public interface PluginFactory {

  String name();

  Task build();
}
