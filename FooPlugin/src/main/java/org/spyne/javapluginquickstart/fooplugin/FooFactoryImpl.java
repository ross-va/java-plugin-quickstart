package org.spyne.javapluginquickstart.fooplugin;

import org.spyne.javapluginquickstart.spi.task.Task;
import org.spyne.javapluginquickstart.spi.task.PluginFactory;

public class FooFactoryImpl implements PluginFactory {

  @Override
  public String name() {
    return "foo";
  }

  @Override
  public Task build() {
    return new FooImpl();
  }
}
