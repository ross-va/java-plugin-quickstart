package org.spyne.javapluginquickstart.spi;

import java.util.Collections;
import java.util.List;
import org.spyne.javapluginquickstart.spi.task.PluginFactory;

public interface Plugin {

  default List<PluginFactory> getPluginFactories() {
    return Collections.emptyList();
  }
}
