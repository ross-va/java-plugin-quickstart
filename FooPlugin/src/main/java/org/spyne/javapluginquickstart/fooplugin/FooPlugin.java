package org.spyne.javapluginquickstart.fooplugin;

import java.util.Arrays;
import java.util.List;
import org.spyne.javapluginquickstart.spi.Plugin;
import org.spyne.javapluginquickstart.spi.task.PluginFactory;

public class FooPlugin implements Plugin {

  @Override
  public List<PluginFactory> getPluginFactories() {
    return Arrays.asList(
        new FooFactoryImpl()
    );
  }
}
