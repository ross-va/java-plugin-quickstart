package org.spyne.javapluginquickstart.core;

import org.spyne.javapluginquickstart.spi.task.PluginFactory;

import java.io.File;

public class Util {

    public static PluginFactory getThePluginFactory(String pluginsPath, String pluginName){
        PluginLoader pluginLoader = new PluginLoader(new File(pluginsPath));
        pluginLoader.loadPlugins();
        return pluginLoader.getFooFactory(pluginName);
    }


}
